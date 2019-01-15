# [Spring Boot Actuator](https://docs.spring.io/spring-boot/docs/2.1.2.RELEASE/reference/htmlsingle/#production-ready-endpoints)

Spring Boot includes a number of additional features to help you monitor and manage your application when you push it to production.  
You can choose to manage and monitor your application by using HTTP endpoints or with JMX. 
Auditing, health, and metrics gathering can also be automatically applied to your application.


##Definition of Actuator
An actuator is a manufacturing term that refers to a mechanical device for moving or controlling something. 
Actuators can generate a large amount of motion from a small change.

To add the actuator to a Maven based project, add the following ‘Starter’ dependency:
```
<dependencies>
	<dependency>
		<groupId>org.springframework.boot</groupId>
		<artifactId>spring-boot-starter-actuator</artifactId>
	</dependency>
</dependencies>
```

##Endpoints
Actuator endpoints allow us to monitor and interact with our Spring Boot application.  
Spring Boot includes number of built-in endpoints and lets you add your own .

Spring Boot-actuator，提供了许多有用的EndPoint，对Spring Boot应用提供各种监控，下面说一下我常用的EndPoint：

    1. http://localhost/health 查看应用启动状态。health 端点现在默认是被暴露的（只展示status）。如果你希望展示更多的细节信息，可以通过修改属性management.endpoints.health.show-details来实现：
       management.endpoints.health.show-details=true
    2. http://localhost/env 环境变量
    3. http://localhost/info 应用信息
    4. http://localhost/metrics 内存等应用基本指标
    5. http://localhost/dump 线程栈
    6. http://localhost/configprops  获取应用的配置信息，因为Spring Boot 可能发布时是单独的Jar包，配置文件可能包含其中， 当我们需要检查配置文件时可以使用 ConfigpropsEndPoint 进行查看一些配置是否正确。
    7. http://localhost/trace  最近几次的http请求信息
    Spring Session 用现在可以通过/application/sessions Actuator Endpoint进行查找和删除session。

Each individual endpoint can be enabled or disabled.  This controls whether or not the endpoint is created and its bean exists in the application context.  
To be remotely accessible an endpoint also has to be exposed via JMX or HTTP.  
Most applications choose HTTP, where the ID of the endpoint along with a prefix of /actuator is mapped to a URL.  
For example, by default, the `health` endpoint is mapped to `/actuator/health`.

The following technology-agnostic endpoints are available:

Id	| Description	| Enabled by default
--------|--------|--------:
auditevents|Exposes audit events information for the current application.|Yes
beans|Displays a complete list of all the Spring beans in your application.|Yes
caches|Exposes available caches.|Yes
conditions|Shows the conditions that were evaluated on configuration and auto-configuration classes and the reasons why they did or did not match.|Yes
configprops|Displays a collated list of all @ConfigurationProperties.|Yes
env|Exposes properties from Spring’s ConfigurableEnvironment.|Yes
flyway|Shows any Flyway database migrations that have been applied.|Yes
health|Shows application health information.|Yes
httptrace|Displays HTTP trace information (by default, the last 100 HTTP request-response exchanges).|Yes
info|Displays arbitrary application info.|Yes
integrationgraph|Shows the Spring Integration graph.|Yes
loggers|Shows and modifies the configuration of loggers in the application.|Yes
liquibase|Shows any Liquibase database migrations that have been applied.|Yes
metrics|Shows ‘metrics’ information for the current application.|Yes
mappings|Displays a collated list of all @RequestMapping paths.|Yes
scheduledtasks|Displays the scheduled tasks in your application.|Yes
sessions|Allows retrieval and deletion of user sessions from a Spring Session-backed session store. Not available when using Spring Session’s support for reactive web applications.|Yes
shutdown|Lets the application be gracefully shutdown.|No
threaddump|Performs a thread dump.|Yes

If your application is a web application (Spring MVC, Spring WebFlux, or Jersey), you can use the following additional endpoints:

Id|	Description	Sensitive |default
--------|--------|--------:
heapdump|Returns an hprof heap dump file.|Yes
jolokia|Exposes JMX beans over HTTP (when Jolokia is on the classpath, not available for WebFlux).|Yes
logfile|Returns the contents of the logfile (if logging.file or logging.path properties have been set). Supports the use of the HTTP Range header to retrieve part of the log file’s content.|Yes
prometheus|Exposes metrics in a format that can be scraped by a Prometheus server.|Yes

To learn more about the Actuator’s endpoints and their request and response formats, please refer to the separate API documentation ([HTML](https://docs.spring.io/spring-boot/docs/2.1.2.RELEASE/actuator-api//html) or [PDF](https://docs.spring.io/spring-boot/docs/2.1.2.RELEASE/actuator-api//pdf/spring-boot-actuator-web-api.pdf)).

###Enabling Endpoints
By default, all endpoints except for shutdown are enabled. 
To configure the enablement of an endpoint, use its `management.endpoint.<id>.enabled` property. 
The following example enables the shutdown endpoint:
```
management.endpoint.shutdown.enabled=true
```
###Exposing Endpoints
Since Endpoints may contain sensitive information, careful consideration should be given about when to expose them. 
The following table shows the default exposure for the built-in endpoints: 

ID|	JMX	|Web
--------|--------|--------:
auditevents|Yes|No
beans|Yes|No
caches|Yes|No
conditions|Yes|No
configprops|Yes|No
env|Yes|No
flyway|Yes|No
health|Yes|Yes
heapdump|N/A|No
httptrace|Yes|No
info|Yes|Yes
integrationgraph|Yes|No
jolokia|N/A|No
logfile|N/A|No
loggers|Yes|No
liquibase|Yes|No
metrics|Yes|No
mappings|Yes|No
prometheus|N/A|No
scheduledtasks|Yes|No
sessions|Yes|No
shutdown|Yes|No
threaddump|Yes|No

To change which endpoints are exposed, use the following technology-specific include and exclude properties:

Property|	Default
--------|--------:
management.endpoints.jmx.exposure.exclude |
management.endpoints.jmx.exposure.include|*
management.endpoints.web.exposure.exclude|
management.endpoints.web.exposure.include|info, health

The `include` property lists the IDs of the endpoints that are exposed. 
The `exclude` property lists the IDs of the endpoints that should not be exposed. 
The `exclude` property takes precedence over the `include` property. 
Both `include` and `exclude` properties can be configured with a list of endpoint IDs.

For example, to stop exposing all endpoints over JMX and only expose the health and info endpoints, use the following property:
```
management.endpoints.jmx.exposure.include=health,info
```
`* `can be used to select all endpoints. 
For example, to expose everything over HTTP except the env and beans endpoints, use the following properties:
```
management.endpoints.web.exposure.include=*
management.endpoints.web.exposure.exclude=env,beans
```
###Securing HTTP Endpoints
You should take care to secure HTTP endpoints in the same way that you would any other sensitive URL. 
If Spring Security is present, endpoints are secured by default using Spring Security’s content-negotiation strategy. 
If you wish to configure custom security for HTTP endpoints, for example, only allow users with a certain role to access them, 
Spring Boot provides some convenient `RequestMatcher` objects that can be used in combination with Spring Security.

A typical Spring Security configuration might look something like the following example:
```
@Configuration
public class ActuatorSecurity extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.requestMatcher(EndpointRequest.toAnyEndpoint()).authorizeRequests()
				.anyRequest().hasRole("ENDPOINT_ADMIN")
				.and()
			.httpBasic();
	}

}
```
The preceding example uses `EndpointRequest.toAnyEndpoint()` to match a request to any endpoint and then ensures that all have the `ENDPOINT_ADMIN` role. 
Several other matcher methods are also available on EndpointRequest. See the API documentation (HTML or PDF) for details.

If you deploy applications behind a firewall, you may prefer that all your actuator endpoints can be accessed without requiring authentication. 
You can do so by changing the management.endpoints.web.exposure.include property, as follows:
`application.properties`. 
```
management.endpoints.web.exposure.include=*
```
Additionally, if Spring Security is present, you would need to add custom security configuration that allows unauthenticated access to the endpoints as shown in the following example:
```
@Configuration
public class ActuatorSecurity extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.requestMatcher(EndpointRequest.toAnyEndpoint()).authorizeRequests()
			.anyRequest().permitAll();
	}

}
```
###Configuring Endpoints
Endpoints automatically cache responses to read operations that do not take any parameters. 
To configure the amount of time for which an endpoint will cache a response, use its `cache.time-to-live` property. 
The following example sets the time-to-live of the `beans` endpoint’s cache to 10 seconds:
`application.properties`. 

    management.endpoint.beans.cache.time-to-live=10s

The prefix `management.endpoint.<name>` is used to uniquely identify the endpoint that is being configured.

###Hypermedia for Actuator Web Endpoints
A “discovery page” is added with links to all the endpoints. The “discovery page” is available on `/actuator` by default.

When a custom management context path is configured, the “discovery page” automatically moves from `/actuator` to the root of the management context.  
For example, if the management context path is `/management`, then the discovery page is available from `/management`. 
hen the management context path is set to `/`, the discovery page is disabled to prevent the possibility of a clash with other mappings.

###CORS Support
Cross-origin resource sharing (CORS) is a W3C specification that lets you specify in a flexible way what kind of cross-domain requests are authorized. 
If you use Spring MVC or Spring WebFlux, Actuator’s web endpoints can be configured to support such scenarios.

CORS support is disabled by default and is only enabled once the `management.endpoints.web.cors.allowed-origins` property has been set. 
The following configuration permits GET and POST calls from the example.com domain:
```
management.endpoints.web.cors.allowed-origins=http://example.com
management.endpoints.web.cors.allowed-methods=GET,POST
```
