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