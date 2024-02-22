--spring   
100道Spring和Spring Boot相关的面试题,由易到难,并包含答案:

1. 什么是Spring框架?Spring是轻量级的开源Java EE框架,可以解决企业应用开发的复杂性。

2. Spring有哪些主要模块?核心容器、AOP、数据访问/集成、Web、测试等模块。

3. Spring的核心容器模块包含哪些? Beans、Core、Context、Expression Language等。

4. Spring支持哪些容器?BeanFactory和ApplicationContext,后者增加了更多企业功能。

5. 什么是Spring IOC容器?Spring IOC容器负责实例化、定位和管理Bean。

6. 什么是依赖注入及其方式?依赖注入(DI)是IOC的一个实现,包括构造器注入、Setter方法注入、接口注入等。

7. Spring Bean的生命周期?包含定义、初始化、使用和销毁四个阶段。

8. 如何定义Spring Bean?可以用XML或注解@Bean、@Component等定义Bean。

9. 什么是Spring配置文件?Spring配置文件包含定义Bean、配置信息等内容,通常为XML文件。

10. 解释Spring Bean自动装配?Spring可以根据属性名或Bean名称自动装配Bean之间的依赖关系。

11. Spring AOP实现的方式?使用动态代理,主要有JDK动态代理和CGLIB字节码生成技术。

12. 什么是切面(Aspect)?切面包含切点、通知(增强处理)、引入(声明添加功能)。

13. Spring AOP支持哪些通知类型? before、after returning、after throwing、after、around等。

14. 什么是织入(Weaving)?织入是把切面应用到目标对象并创建新的代理对象的过程。

15. 如何实现基于XML的AOP切面配置?使用<aop:aspect>定义切面,<aop:before>等定义通知。

16. Spring事务的特点?将业务操作封装为服务,通过声明式事务管理使其具有事务语义。

17. Spring的事务隔离级别?默认值是 ISOLATION_DEFAULT,可设置为READ_UNCOMMITTED等其他隔离级别。

18. Spring事务的传播行为有哪些?REQUIRED、 REQUIRES_NEW、NESTED等级别。

19. Spring事务的回滚规则?发生 RuntimeException 及其子类会回滚,Checked Exception不会。

20. @Transactional的常用参数有哪些?propagation、isolation、timeout、rollbackFor、noRollbackFor等。

21. Spring有几种事务管理器?JDBC、Hibernate、JPA、JTA等。

22. 什么是Spring MVC?Spring MVC是Spring的Web框架,使用MVC设计模式。

23. DispatcherServlet的作用?作为Spring MVC的核心分发器,负责路由并调度请求。

24. @Controller和@RestController的区别?@RestController将返回值直接作为响应,@Controller还需要写@ResponseBody。

25. @RequestMapping的常用属性?value映射请求路径,method指定请求方法类型。

26. Springboot starters有哪些?web、data-jpa、security、actuator等。

27. Spring Boot自动配置的原理?SpringBoot启动时加载自动配置类,根据jar依赖 Configuration条件装配。

28. Spring Boot有哪些方式定制配置?使用application.properties、编写配置类加@Configuration等。

29. 如何修改Spring Boot的默认端口号和上下文路径?通过 server.port和server.servlet.context-path属性。

30. Spring Boot支持哪些内嵌容器?Tomcat、Jetty、Undertow等,默认Tomcat。

31. 如何解决Spring Boot应用404无法映射资源?使用Spring MVC配置addResourceHandlers实现。

32. Spring Boot的多环境配置是怎样实现的?通过使用application-prod.properties指定活动环境。

33. 什么是actuator?Spring Boot Actuator提供了对生产环境应用程序的监控和管理能力。

34. JdbcTemplate和JdbcDaoSupport的区别? JdbcTemplate更加简洁,可以不依赖于spring的框架类。

35. 如何监控Spring Boot应用的运行状态?可以使用Actuator暴露应用运行时的监控接口数据。

36. Spring Boot Starter的工作原理?Starter会依据classpath里的jar包导入自动配置,减少手动配置。

37. 如何在Spring Boot中进行集成测试?使用@SpringBootTest进行启动类测试,使用@MockBean创建mock对象。

38. Spring Boot的条件化配置有哪两种方式?使用@Profile和@Conditional注解。

39. 如何实现Spring Boot的热更新?使用spring-boot-devtools,实现classloader层面热部署。

40. Spring Boot Scheduler的常用注解有哪些?@Scheduled、@EnableScheduling等。

41. 如何在Spring Boot中使用Filter?通过@Bean注册Filter,或者添加@ServletComponentScan启用扫描。

42. 如何在Spring Boot中添加拦截器?实现WebMvcConfigurer接口添加拦截器并设置addInterceptors。

43. Spring Boot支持哪些日志框架?JUL、Log4j2、Logback等,默认Logback。

44. 如何切换Spring Boot日志框架?去除默认日志依赖,添加对应日志依赖,配置loggers为对应框架即可。

45. Spring Boot日志文件配置在哪个配置文件中?在application.properties中使用logging.file或logging.path配置。

46. Spring Boot中如何配置日志级别?使用logging.level.<logger-name>=<level>设置级别。

47. 如何跟踪Spring Boot应用的日志?使用logging.level.root=trace,或通过--debug启动。

48. @SpringBootApplication注解包含哪些功能?包含了@Configuration、@EnableAutoConfiguration、@ComponentScan。

49. 如何在Spring Boot中配置数据源?在application.properties中配置spring.datasource.*属性。

50. Spring Boot访问数据库有哪些方式?JdbcTemplate、JPA、JdbcDaoSupport、MyBatis等。

51. 如何实现Spring Boot中的缓存?可以使用@EnableCaching启用缓存,并使用@Cacheable等注解缓存方法。

52. Spring Boot中的监听器有哪些?主要有两种:Spring ApplicationListener和Servlet监听器。

53. Spring Boot中的事件有哪些?主要有Spring ApplicationEvent和Servlet事件。

54. 如何在Spring Boot中使用WebSocket?通过实现WebSocketConfigurer接口注册端点Url。

55. 如何在Spring Boot中做单元测试?使用@SpringBootTest注解加载完整上下文。

56. 如何在Spring Boot中做集成测试?使用@SpringBootTest+MockMVC进行集成测试。

57. 如何在Spring Boot中启动定时任务?使用@EnableScheduling注解开启计划任务功能。

58. 如何在Spring Boot中使用拦截器?实现WebMvcConfigurer接口添加拦截器并设置addInterceptors方法。

59. 在Spring Boot中使用过滤器的步骤?通过@Bean方式注册过滤器,或使用@ServletComponentScan。

60. 如何在Spring Boot中做异步处理?注入AsyncTaskExecutor执行器,使用@Async注解表明需要异步处理。

61. Spring Boot打包应用的方式?可以使用maven或gradle插件生成jar包直接运行,或生成war部署到服务器。

62. Spring Boot Actuator的作用?主要用于生产环境下监控和管理Spring Boot应用。

63. Spring Boot Actuator的端点都有哪些?health、beans、caches、conditions等。

64. 如何在Spring Boot中获取属性配置的值?使用@Value("${property}")注解,或Environment.getProperty方法。

65. Spring Boot中配置文件的加载顺序?bootstrap.properties、application.properties和applicaiton.yml。

66. Spring Boot中如何实现整合Swagger?使用springfox-swagger依赖,并使用@EnableSwagger2注解。

67. Spring Boot中如何实现异常处理?通过@ControllerAdvice和@ExceptionHandler注解实现全局异常处理。

68. Spring Boot中的启动流程?引导上下文加载配置、创建environment、打印banner、刷新context、启动Tomcat等。

69. Spring Boot中如何解决跨域问题?使用@CrossOrigin或WebMvcConfigurer处理跨域问题。

70. 如何实现Spring Boot应用的热部署?引入spring-boot-devtools依赖,实现自动重启应用。

71. Spring Boot中使用过哪些 starters?web、data-jpa、security、actuator等。

72. 如何在Spring Boot中连接池?默认使用HikariCP连接池,可以在配置中指定使用DBCP2或Tomcat连接池。

73. Spring Boot 自动配置原理?根据引入的jar依赖,匹配@\ConditionalOnClass和@\ConditionalOnMissingBean条件。

74. Spring Boot中如何覆盖默认配置?使用@Bean替换默认Bean,或使用@ConditionalOnMissingBean预置条件。

75. 如何在Spring Boot中使用拦截器?实现WebMvcConfigurer接口,重载addInterceptors方法。

76. 如何排除Spring Boot的自动配置类?使用@EnableAutoConfiguration(exclude=类名.class)

77. Spring Boot 中如何实现定时任务?使用@EnableScheduling开启定时任务,用@Scheduled声明需要定时执行的方法。

78. Spring Boot 如何实现异步调用?注入AsyncTaskExecutor执行器,使用@Async注解表明需要异步处理的方法。

79. Spring Boot 如何自定义Banner?通过spring.banner.location或通过Banner接口实现。

80. Spring Boot中如何实现网络分页?继承PagedResourcesAssemblerWrapper对分页结果集进行包装。

81. Spring Boot Admin的功能?管理和监控SpringBoot应用程序。

82. Spring Boot Admin UI界面主要包含什么信息?应用概览、详细信息、日志追踪、JMX管理、指标监控等。

83. Spring Boot Admin 的自动化原理?客户端注册到服务端后发送心跳,服务端维护并检查心跳状态。

84. Spring Boot开发分布式应用的注意事项?配置服务发现、断路器实现容错、使用消息队列、接口幂等设计。

85. Spring Boot Actuator提供哪些监控接口?主要是health, info, metrics等。

86. 如何在Spring Boot中做全局异常处理?使用@ControllerAdvice+@ExceptionHandler捕获全局异常。

87. Spring Boot的starter原理?starter将相关依赖打包,并处理版本兼容问题。@ConditionalOnClass自动配置。

88. 如何在Spring Boot中使用Redis?引入spring-boot-starter-data-redis,通过RedisTemplate使用Redis。

89. Spring Boot 监控接口调用链跟踪?使用spring-cloud-sleuth-zipkin集成zipkin实现调用链跟踪。

90. Spring Boot部署服务器的方式?可以发布成jar包,然后使用java -jar运行;或者发布成war放到servlet容器中。

91. 为什么要使用Spring Boot?Spring Boot简化Spring应用开发,内嵌web服务器,自动配置组件,无代码生成和XML配置。

92. Spring Boot 的核心注解?@SpringBootApplication、@Configuration、@EnableAutoConfiguration、 etc.

93. Spring Boot 端点监控?主要是Actuator提供不同的端点,对应监控应用的不同方面。

94. Spring Boot 核心配置文件?主要是application.properties或application.yml文件。

95. Spring Boot 支持的日志框架?Logback、Log4J2、 Java Util Logging等。

96. 如何修改Spring Boot的默认端口号?在application.properties中配置server.port属性。

97. Spring Boot Web开发常用注解?@Controller、@RestController、@RequestMapping等。

98. Spring Boot 配置数据源的方式?在application.properties配置spring.datasource.*属性。

99. Spring Boot 配置应用上下文路径?使用server.servlet.context-path属性。

100. Spring Boot 常用的启动参数?–debug、–trace、–jmx等参数,用来调试和跟踪应用。

=================================================================================

--spring
100 Spring and Spring Boot related interview questions, from easy to difficult, including answers:

1. What is the Spring framework? Spring is a lightweight open source Java EE framework that can solve the complexity of enterprise application development.

2. What are the main modules of Spring? Core container, AOP, data access/integration, Web, testing and other modules.

3. What does Spring’s core container module include? Beans, Core, Context, Expression Language, etc.

4. What containers does Spring support? BeanFactory and ApplicationContext, the latter adds more enterprise features.

5. What is Spring IOC container? Spring IOC container is responsible for instantiating, locating and managing Beans.

6. What is dependency injection and its method? Dependency injection (DI) is an implementation of IOC, including constructor injection, Setter method injection, interface injection, etc.

7. The life cycle of Spring Bean includes four stages: definition, initialization, use and destruction.

8. How to define Spring Bean? Beans can be defined using XML or annotations such as @Bean and @Component.

9. What is a Spring configuration file? A Spring configuration file contains definition beans, configuration information, etc., and is usually an XML file.

10. Explain Spring Bean autowiring? Spring can automatically wire dependencies between beans based on property names or bean names.

11. How to implement Spring AOP? Use dynamic proxy, mainly JDK dynamic proxy and CGLIB bytecode generation technology.

12. What is aspect? Aspect includes pointcuts, notifications (enhanced processing), and introductions (declaration of added functions).

13. What advice types does Spring AOP support? before, after returning, after throwing, after, around, etc.

14. What is weaving? Weaving is the process of applying aspects to target objects and creating new proxy objects.

15. How to implement XML-based AOP aspect configuration? Use <aop:aspect> to define aspects, <aop:before> and so on to define notifications.

16. The characteristics of Spring transactions? Encapsulate business operations as services and make them have transaction semantics through declarative transaction management.

17. Spring's transaction isolation level? The default value is ISOLATION_DEFAULT, which can be set to other isolation levels such as READ_UNCOMMITTED.

18. What are the propagation behaviors of Spring transactions? REQUIRED, REQUIRES_NEW, NESTED and other levels.

19. Spring transaction rollback rules? RuntimeException and its subclasses will be rolled back, but Checked Exception will not.

20. What are the common parameters of @Transactional? propagation, isolation, timeout, rollbackFor, noRollbackFor, etc.

21. How many transaction managers does Spring have? JDBC, Hibernate, JPA, JTA, etc.

22. What is Spring MVC? Spring MVC is Spring's Web framework, using the MVC design pattern.

23. The role of DispatcherServlet? As the core dispatcher of Spring MVC, it is responsible for routing and scheduling requests.

24. The difference between @Controller and @RestController? @RestController uses the return value directly as a response, and @Controller also needs to write @ResponseBody.

25. Commonly used attributes of @RequestMapping? value maps the request path, and method specifies the request method type.

26. What are the Springboot starters? web, data-jpa, security, actuator, etc.

27. The principle of Spring Boot automatic configuration? Spring Boot loads the automatic configuration class when it starts, and assembles it according to the jar dependency Configuration conditions.

28. What are the ways to customize the configuration of Spring Boot? Use application.properties, write configuration classes and add @Configuration, etc.

29. How to modify Spring Boot’s default port number and context path? Through the server.port and server.servlet.context-path properties.

30. What embedded containers does Spring Boot support? Tomcat, Jetty, Undertow, etc. Tomcat is the default.

31. How to solve the problem of 404 Unable to map resources in Spring Boot application? Use Spring MVC to configure addResourceHandlers.

32. How is Spring Boot's multi-environment configuration implemented? By using application-prod.properties to specify the active environment.

33. What is an actuator? Spring Boot Actuator provides monitoring and management capabilities for production environment applications.

34. What is the difference between JdbcTemplate and JdbcDaoSupport? JdbcTemplate is more concise and does not depend on the spring framework class.

35. How to monitor the running status of Spring Boot application? You can use Actuator to expose the monitoring interface data when the application is running.

36. How does Spring Boot Starter work? Starter will import automatic configuration based on the jar package in the classpath, reducing manual configuration.

37. How to perform integration testing in Spring Boot? Use @SpringBootTest to perform startup class testing, and use @MockBean to create mock objects.

38. What are the two methods of conditional configuration of Spring Boot? Use @Profile and @Conditional annotations.

39. How to implement hot update of Spring Boot? Use spring-boot-devtools to achieve hot deployment at the classloader level.

40. What are the common annotations of Spring Boot Scheduler? @Scheduled, @EnableScheduling, etc.

41. How to use Filter in Spring Boot? Register Filter through @Bean, or add @ServletComponentScan to enable scanning.

42. How to add an interceptor in Spring Boot? Implement the WebMvcConfigurer interface to add an interceptor and set addInterceptors.

43. What log frameworks does Spring Boot support? JUL, Log4j2, Logback, etc. Logback is the default.

44. How to switch the Spring Boot logging framework? Remove the default log dependency, add the corresponding log dependency, and configure loggers as the corresponding framework.

45. In which configuration file is the Spring Boot log file configured? Use logging.file or logging.path configuration in application.properties.

46. How to configure the log level in Spring Boot? Use logging.level.<logger-name>=<level> to set the level.

47. How to track the logs of Spring Boot application? Use logging.level.root=trace, or start with --debug.

48. What functions does the @SpringBootApplication annotation include? It includes @Configuration, @EnableAutoConfiguration, and @ComponentScan.

49. How to configure the data source in Spring Boot? Configure the spring.datasource.* properties in application.properties.

50. What are the ways for Spring Boot to access the database? JdbcTemplate, JPA, JdbcDaoSupport, MyBatis, etc.

51. How to implement caching in Spring Boot? You can use @EnableCaching to enable caching, and use @Cacheable and other annotation caching methods.

52. What are the listeners in Spring Boot? There are two main types: Spring Application Listener and Servlet listener.

53. What are the events in Spring Boot? Mainly include Spring ApplicationEvent and Servlet events.

54. How to use WebSocket in Spring Boot? Register the endpoint Url by implementing the WebSocketConfigurer interface.

55. How to do unit testing in Spring Boot? Use the @SpringBootTest annotation to load the complete context.

56. How to do integration testing in Spring Boot? Use @SpringBootTest+MockMVC for integration testing.

57. How to start a scheduled task in Spring Boot? Use the @EnableScheduling annotation to enable the scheduled task function.

58. How to use interceptors in Spring Boot? Implement the WebMvcConfigurer interface to add interceptors and set the addInterceptors method.

59. Steps to use filters in Spring Boot? Register the filter through @Bean method, or use @ServletComponentScan.

60. How to do asynchronous processing in Spring Boot? Inject the AsyncTaskExecutor executor and use the @Async annotation to indicate that asynchronous processing is required.

61. How does Spring Boot package applications? You can use the maven or gradle plug-in to generate a jar package and run it directly, or generate a war and deploy it to the server.

62. The role of Spring Boot Actuator? It is mainly used to monitor and manage Spring Boot applications in production environments.

63. What are the endpoints of Spring Boot Actuator? health, beans, caches, conditions, etc.

64. How to get the value of property configuration in Spring Boot? Use the @Value("${property}") annotation, or the Environment.getProperty method.

65. The loading order of configuration files in Spring Boot? bootstrap.properties, application.properties and application.yml.

66. How to integrate Swagger in Spring Boot? Use springfox-swagger dependency and use @EnableSwagger2 annotation.

67. How to implement exception handling in Spring Boot? Global exception handling is implemented through @ControllerAdvice and @ExceptionHandler annotations.

68. The startup process in Spring Boot? Boot context loads configuration, creates environment, prints banner, refreshes context, starts Tomcat, etc.

69. How to solve cross-domain problems in Spring Boot? Use @CrossOrigin or WebMvcConfigurer to handle cross-domain problems.

70. How to implement hot deployment of Spring Boot applications? Introduce the spring-boot-devtools dependency to automatically restart the application.

71. What starters have been used in Spring Boot? web, data-jpa, security, actuator, etc.

72. How to use connection pooling in Spring Boot? HikariCP connection pool is used by default, and DBCP2 or Tomcat connection pool can be specified in the configuration.

73. Spring Boot automatic configuration principle? Match the @\ConditionalOnClass and @\ConditionalOnMissingBean conditions based on the introduced jar dependency.

74. How to override the default configuration in Spring Boot? Use @Bean to replace the default Bean, or use @ConditionalOnMissingBean to preset conditions.

75. How to use interceptors in Spring Boot? Implement the WebMvcConfigurer interface and overload the addInterceptors method.

76. How to exclude Spring Boot’s automatic configuration class? Use @EnableAutoConfiguration(exclude=classname.class)

77. How to implement scheduled tasks in Spring Boot? Use @EnableScheduling to enable scheduled tasks, and use @Scheduled to declare methods that need to be executed regularly.

78. How does Spring Boot implement asynchronous calls? Inject the AsyncTaskExecutor executor and use the @Async annotation to indicate the method that requires asynchronous processing.

79. How does Spring Boot customize Banner? Through spring.banner.location or through the Banner interface.

80. How to implement network paging in Spring Boot? Inherit PagedResourcesAssemblerWrapper to wrap the paging result set.

81. The function of Spring Boot Admin? Manage and monitor SpringBoot applications.

82. What information does the Spring Boot Admin UI interface mainly contain? Application overview, detailed information, log tracking, JMX management, indicator monitoring, etc.

83. The automation principle of Spring Boot Admin? After the client registers with the server, it sends a heartbeat, and the server maintains and checks the heartbeat status.

84. Things to note when developing distributed applications with Spring Boot? Configure service discovery, circuit breakers to implement fault tolerance, use message queues, and interface idempotent design.

85. What monitoring interfaces does Spring Boot Actuator provide? Mainly health, info, metrics, etc.

86. How to handle global exceptions in Spring Boot? Use @ControllerAdvice+@ExceptionHandler to catch global exceptions.

87. Spring Boot starter principle? The starter packages relevant dependencies and handles version compatibility issues. @ConditionalOnClass automatically configured.

88. How to use Redis in Spring Boot? Introduce spring-boot-starter-data-redis and use Redis through RedisTemplate.

89. Spring Boot monitoring interface call chain tracking? Use spring-cloud-sleuth-zipkin to integrate zipkin to implement call chain tracking.

90. How does Spring Boot deploy the server? You can publish it as a jar package and then run it using java -jar; or you can publish it as a war and put it in a servlet container.

91. Why use Spring Boot? Spring Boot simplifies Spring application development, with embedded web servers, automatic configuration components, no code generation and XML configuration.

92. The core annotations of Spring Boot? @SpringBootApplication, @Configuration, @EnableAutoConfiguration, etc.

93. Spring Boot endpoint monitoring? Mainly Actuator provides different endpoints, corresponding to different aspects of monitoring applications.

94. Spring Boot core configuration file? Mainly application.properties or application.yml file.

95. Logging frameworks supported by Spring Boot? Logback, Log4J2, Java Util Logging, etc.

96. How to modify the default port number of Spring Boot? Configure the server.port property in application.properties.

97. Common annotations for Spring Boot web development? @Controller, @RestController, @RequestMapping, etc.

98. How to configure the data source in Spring Boot? Configure the spring.datasource.* properties in application.properties.

99. How to configure the application context path in Spring Boot? Use the server.servlet.context-path property.

100. Spring Boot commonly used startup parameters? –debug, –trace, –jmx and other parameters, used to debug and trace applications.

