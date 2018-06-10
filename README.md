# micro service 微服务架构实践

* SimpleRPC 使用java BIO的通信方式实现一个RPC的例子
	
	* rpc_interface是公共接口
	
		* HelloService
	* rpc_server是服务端内容
	
		* 有接口的实现方式 :HelloServiceImpl
		* 有监听服务类负责绑定特定的端口，并且根据请求来寻找服务get方法:Server
		* 启动类App:注册服务，启动服务器	
	* rpc_client是客户端内容
	
		* 远程调用类:Client<T> :使用动态代理的方式，根据服务接口类把接口序列化向目标服务端发起Socket远程调用的请求
		* 启动类App:发起请求，发起调用

	缺点：
	
	* BIO方式消耗资源过多，让服务器变慢甚至崩溃
	* 序列化：序列化方式效率不高，影响性能
	* 服务注册中心：调用的时候每次手动访问地址，比较麻烦
	* 负载均衡:可以让系统横向扩展的时候能够拥有更多的计算资源
	* 服务监控：客户端调用的时候吧无法使用的服务排除掉
	* 异常处理：服务端异常返回结果有误的时候，如何处理

* Dubbo简单框架

	* 首先配置zookeeper,开启zookeeper服务
	* dubbo-api 定义dubbo接口
	* dubbo-server 实现接口的服务，使用@Service 注解暴露服务,配置文件配置dubbo
	* dubbo-client 使用@Reference注解生成远程服务代理，来调用相应的方法
	* dubbo-getway 模块之间互相调用的时候，为了降低由网络波动带来的不确定性因素并提升安全性，所有模块运行在内网中，并单独提供一个工程作为网关服务，开放固定端口代理所有模块提供的服务，并且通过拦截器验证所有外部请求以达到权限管理的目的，有这个在，dubbo-client调用的时候通过url获取数据而不是直接通过注册的服务进行通信
	* dubbo-admin 主要用于路由规则、动态配置、服务降级、访问控制、权重调整、负载均衡等管理。dubbo的管理界面，下载打包编译dubbo官方项目 https://github.com/apache/incubator-dubbo-ops ,修改/WEB-INF/dubbo.properties文件配置，使用mvn package打包成war,创建tomcat服务器里面运行tomcat服务器就可以

* SpringCloud简单框架:使用的是Dalston版本
	
	Spring Cloud Netflix 是 Spring Cloud 的子项目之一， 主要内容是对 Netflix 公司一系列开源产品的包装， 它为 Spring Boot 应用提供了自配置的 Netflix Oss 整合。 通过一些简单的注解， 开发者就可以快速地 在应用中配置一些常用模块井构建庞大的分布式系统。 它主要提供的模块包括：服务发现（ Eureka ）、断路器（ Hystrix ） 、智能路由（ Zuul ） 、客户端负载均衡（ Ribbon ） 等。
	
	* cloud_eureka 注册中心:添加依赖，在SpringBoot入口添加EnableEurekaServer将本应用变为Eureka服务器
	
	* cloud_server 注册服务:添加依赖，在SpringBoot入口添加EnableDiscoveryClient注解开启DiscoveryClient的实例，与 Eureka Server 进行交互， 负责注册服务、租约续期、检索服务、取消 租约等功能。
	
	* cloud_client 调用服务:模块之间是通过HTTP协议暴露服务的，调用服务通过类似JDK的 URLConnection或Spring的RestTemplate(HTTP客户端)便可
		
		* 使用Ribbon:
		
			1. 添加依赖，SpringBoot 入口添加＠EnableDiscoveryClient注解添加发现服务能力，配置eureka地址、应用名称、访问端口等基本信息 
			2. 引入ribbon依赖
			3. 在 Spring Boot 入口处注人 RestTemplate 实例。
			4. BusinessService类调用服务。
			5. application.properties文件配置Ribbon
		
		* 使用Feign:这是一个声明式的Web服务客户端，通过简单的注解可以调用本地方法一样调用远程服务
			
			1. 添加依赖，SpringBoot 入口添加＠EnableDiscoveryClient注解添加发现服务能力，配置eureka地址、应用名称、访问端口等基本信息 
			2. 添加Feign依赖
			3. Spring Boot入口处开启Fegin支持
			4. 编写调用接口
			5. 配置Fegin服务
			6. 调用服务
	* cloud_getway：spring cloud提供了Zuul作为服务网关，Zuul与Dubbo服务网关不同的是，Zuul不是直接调用程序俄日是通过动态路由提供代理服务
		
		1. 新建cloud_getway模块,添加依赖
		2. SpringBoot入口处开启Zuul支持
		3. 在application.properties配置Zuul，设置拦截请求的路径和分组标识
		4. 继承ZuulFilter实现过滤功能，进行过滤器权限验证
		5. 在程序入口处注入该过滤器让他生效
	
	* Hystrix断路器
		
		Dubbo 通过 mock 实现服务降级与容错，而 Spring Cloud 则提供 Hystrix 达到同样 的目的。与之不同的是 Hystrix 是以框架级别角度解决该问题，而mock则是以功能角度出发。

		Hystrix 通过线程油来隔离资源，在使用时会根据调用的远程服务划分出多个线程池。 例如调用产品 服务的 Command 放入 A 线程池，调用账户服务的 Command 放入 B 线程池。 当调用服务的代码存在 bug 或者由于其他原因导致自己所在线程池被耗尽时，不会对系统的其他服务造成影响。
			
		* 使用Ribbon：
			
			1. 添加依赖
			2. SpringBoot入口处开启hystrix支持 @EnableCircuitBreaker
			3. 调用服务设置@HystrixCommand
			4. 在application.properties配置熔断器
		* 使用Feign:
			1. 添加依赖
			2. SpringBoot入口处开启hystrix支持 @EnableCircuitBreaker
			3. Feign是使用接口确定调用信息，回调类只需实现接口就可以，与Dubbo的mock类似，编写回调类
			4. 在FeignClient的配置中设置fallback：当前接口的实现类
			5. 在application.properties设置熔断器
	* cloud_admin：服务监控

		1. 添加依赖
		2. 开启admin支持@EnableAdminServer
		3. 配置文件配置admin
		4. 配置客户端，所有eureka应用在pom.xml文件里面build设置暴露应用基本信息
	
	* 应用监控:对单个应用进行监控
		
		1. 添加依赖Jolokia
		2. 在application.properties文件配置权限management.security.enabled=false，我只在cloud_client_feign里面配置了，可以通过url查看应用信息
	
	* 熔断器监控
		
		1. 单应用的熔断数据

			1. 添加依赖
			2. 开启Hystrix面板@EnableHystrix、@EnableHystrixDashboard
			3. 浏览器输入localhost:xxx/hystrix localhost:xxx/hystrix.stream 查看
		2. 	使用Turbine聚合数据

			cloud_turbine: 负责集合所有的hystrix.stream数据，为了分担压力将Turbine独立成一个新的应用。
			
			1. 添加依赖
			2. 在SpringBoot入口处开启Turbine @EnableTurbine
			3. 配置Turbine参数
			4. 浏览器输入http://127.0.0.1:***/turbine.stream查看全部的数据流
			
		3. CloudAdmin整合Turbine

			1. 添加依赖
			2. 设置

			
* RabbitMQ实现消息队列

	安装rabbitMQ 启动rabbitmq-server 启动的时候端口默认是5672，用户名密码默认是guest
	可以在浏览器中输入http://localhost:15672查看RabbitMQ客户端
	
	配置使用
	
	1. 添加spring-boot-starter-amqp依赖
	2. 在application.properties中配置RabbitMQ 设置host port
	3. 在SpringBoot中开启RabbitMQ支持 @EnableRabbit
	4. 编写服务消费者,设置@RabbliLisener标签，指定queues
	5. 添加队列的配置RabbitConfig 声明@Configuration 注入Bean，将消息队列的名字添加进去
	6. 调用通过RabbitTemplate 调用convertAndSend发送消息	
	