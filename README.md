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