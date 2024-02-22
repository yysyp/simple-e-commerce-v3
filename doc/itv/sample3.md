===###	Could you please give a brief introduction about yourself?
===###	What's your responsibility on last project？
===###	"What is classloader?
Classloader is a subsystem of JVM which is used to load class files. Whenever we run the java program, it is loaded first by the classloader. There are three built-in classloaders in Java.

1，Bootstrap ClassLoader: This is the first classloader which is the superclass of Extension classloader. It loads the rt.jar file which contains all class files of Java Standard Edition like java.lang package classes, java.net package classes, java.util package classes, java.io package classes, java.sql package classes, etc.
2，Extension ClassLoader: This is the child classloader of Bootstrap and parent classloader of System classloader. It loads the jar files located inside $JAVA_HOME/jre/lib/ext directory.
3，System/Application ClassLoader: This is the child classloader of Extension classloader. It loads the class files from the classpath. By default, the classpath is set to the current directory. You can change the classpath using ""-cp"" or ""-classpath"" switch. It is also known as Application classloader.

What is threads-context-class-loader?"
===###	How are Runtime exceptions different from Checked exceptions?
===###	You want to synchronize 5 threads to start at the same time. Describe a solution.
===###	"What is ThreadLocal class and when should we use it?
A: Instance of this class store data for each Thread independently, which means that other Threads can not access specific Thread's data. ThreadLocal instance lasts as associated thread - as soon as the thread is done, ThreadLocal instance is ready to be collected by Garbage Collector."
===###	"When should we use the volatile keyword?
A: The volatile keyword in Java is used to mark a Java variable as being stored in memory accessed by multiple threads. This means, that every read of a volatile variable will be read from the computer's main memory, and not from the CPU cache and that every time volatile variable is changed the change will be written to main memory, and not just to the CPU cache.

Since Java 5 the features around volatile variables are extended comparing to previous versions where they could be only written to and read from shared memory.

What is the volatile keyword in Java?
Answer: Volatile keyword in Java is used with variables and all the threads read its value directly from the memory location and don’t cache it. Volatile keyword makes sure that the read value is exactly the same as in the memory location."
===###	"What is SQL injection and what are common approaches to protect your code from it?
A: SQL Injection is a kind of attack where the malicious sender sends a value to your API that, when concatenated with the fixed part of the SQL query, change its intended behavior. JPA does not protect us from this kind of attack. Common approaches that exploit-safe are using Parametrized Queries, JPA Criteria API, and filtering data provided by the user which is called data sanitization. Data sanitization filters could be whitelist and blacklist. Whitelist filters define exactly what a user can provide as input data, while blacklist filters define what user should not provide as input."
===###	"How to pass an object from one thread to another?
A: One approach would be to use java.utils.concurrent.BlockingQueue."
===###	"What is a deadlock in Java?
A: Deadlock is a situation where two or more threads are blocked forever, waiting for each other. In this situation, program will hang forever because neither of the threads in position to proceed and waiting for each other to release the lock."
===###	"When is class garbage collected?
- Java uses the garbage collector to free memory which is occupied by those objects which is no more reference by any of the program.
- An object becomes eligible for Garbage Collection when no live thread can access it.
- There are many ways to make a class reachable and thus prevent it from being eligible for Garbage Collection:
1. Objects of that class are still reachable.
2. Class object representing the class is still reachable.
3. ClassLoader that loaded the class is still reachable.
4. Other classes loaded by the ClassLoader are still reachable.
- When all of the above are false, then the ClassLoader together with all classes it loaded are eligible for Garbage Collection."
  ===###	What is the difference between a Set and a List and map?
  ===###	"What are the ways to create child threads?
- There are two ways to create java threads:

1. Implementing the Runnable Interface :
   This overcomes the limitation of inheriting from only one parent class Thread. Using Runnable interface, lays a path to ground work of a class that utilizes threads.
2. Extending Thread Class :
   It inherits the methods and data members, fields from the class thread. In this process only one class can be inherited from the parent class Thread.

- The advantage is a class can extend Thread class and also implements the Runnable interface, if required. The Runnable interface is set for implementing a thread and the class that implements the interface performs all the work."
  ===###	"What is Reflection API in Java?
- The Reflection API allows Java code to examine classes and objects at run time.
- The new reflection classes allow you to call another class's methods dynamically at run time.
- With the reflection classes, you can also examine an instance's fields and change the fields' contents.
- It is also possible to instantiate new objects, invoke methods and get/set field values using reflection.
- The Reflection API consists of the java.lang.Class class and the java.lang.reflect classes: Field, Method, Constructor, Array, and Modifier."
  ===###	"Q11). Describe synchronization with respect to multi-threading.
  Synchronization is the method to control the access of multiple threads to shared resources, with respect to multi-threading. One thread can modify a shared variable when not in synchronization even when another thread is in the process of using or updating the same shared variable. This can lead to significant errors."
  ===###	"Difference between the methods sleep() and wait().
  Sleep():
  It does not release the lock or hold its monitor.
  In this method, it goes into 'TIMED_WAITING' state without releasing the lock.
  It is a static method.
  It is called within or outside the synchronized block.

wait():
It releases the lock or hold its monitor.
In this method, after releasing the lock, it goes into 'WAITING' state.
It is a non-static method.
It is called within the synchronized block."
===###	"What is the difference between the user thread and daemon thread?
Answer: When a thread is created in a java program, it is known as user thread. A daemon thread is a thread which runs on background and doesn’t prevent JVM from terminating. When there is no user thread running then JVM shutdowns the program and quits. A child thread created from daemon thread is known as a daemon thread."
===###	"What is CountDownLatch in Java?
Answer: CountDownLatch in Java is synchronizer which allows one Thread to wait for one or more Threads before start processing. This is a very crucial requirement and often used in a server-side core java application and having this function simplifies the process of development. This is one of the most important questions and often asked in big java interviews."
===###	"What is Compare and Swap (CAS) algorithm?
Answer: Compare and Swap (CAS) is an atomic instruction used for achieving synchronization in multithreading. It compares the content of a given memory location and if matches then it modifies the value of the content of that memory location to a new given value. This is a single atomic operation and the new value is calculated which depends upon the up-to-date information and if it is used by another thread then the write operation would fail."
===###	"What is “dirty read” in JDBC? Which isolation level prevents dirty read?
Answer: When transactions taking place and there is some that a row is updated at the same time some query can read the updated value. This dirty read happens because that the updated value is not permanent yet, the transaction that has been updated could rollback to a previous value which will result in invalid data.

Isolation levels are used for preventing the dirt read:

TRANSACTION_READ_COMMITED, TRANSACTION_REPEATABLE_READ, and TRANSACTIO_SERIALIZABLE."
===###	"What is 2 phase commit?
Answer: In a distributed systems environment where multiple databases are used, it required to use 2 phase commit protocol. It is an atomic commitment protocol used in distributed systems. In the first phase of this protocol, the transaction manager sends commits requests to all the available transaction resources. If it gets an ok signal from all the transaction resources then all the transaction changes to the resources are committed by the transaction manager. If a transaction resource gets an abort signal then all the changes are rollback by the transaction manager."
===###
===###
===###
===###	https://www.gangboard.com/blog/spring-interview-questions-and-answers/
===###	"Describe AOP.
Aspect-oriented programming or AOP is a programming technique which allows programmers to modularize crosscutting concerns or behavior that cuts across the typical divisions of responsibility. Examples of cross-cutting concerns can be logging and transaction management. The core of AOP is an aspect. It encapsulates behaviors that can affect multiple classes into reusable modules."
===###	"How many modules are there in Spring Framework and what are they?
Core module  
Bean module
SpEL module
Context module
JDBC (Java DataBase Connectivity)
ORM (Object Relational Mapping)
OXM (Object XML Mappers)
JMS (Java Messaging Service)
Transaction
Web
Web MVC
Web Socket
Web Portlet
Aspect Oriented Programming (AOP)
Instrumentation
Test
Messaging
Aspects"
===###
===###	"What are the annotations in Spring?
What are all the StereoType Annotation Spring provided and Which scenario we need to use Which Annotation?
Answer: Spring provided 5 StereoType annotations
All the above annotation will create a beans for the classes only. But we will use Specific Annotation in Specific Scenario only
@Component : this annotation we will this annotation just creating Bean but that class is not Related to any layer in Application.
@Controller : This Annotation we will use to Specifying that class as controller and it will create a bean for that class
@RestController : This Annotation we will use to Specifying that Controller class as RestController and it also creating Bean for that class
@Service : This Annotation we will use for the Specifying that class has Business Operation logic And that layer is Service Layer
@Repository : This Annotation we will use for DAO layer classes"
===###	"List out some important spring annotations
Answer: There are many important annotations which are being used. Some of them are:
@controller
@RequestMapping
@ResponceBody
@PathVariable
@Autowired
@qualifier"
===###	What are the different components of a Spring application?
===###	"What are the different types of Advices?
Different types of Advices in Spring AOP are:

a. Before: These types of advices execute before the joinpoint methods and are configured using @Before annotation mark.
b. After returning: These types of advices execute after the joinpoint methods completes executing normally and are configured using @AfterReturning annotation mark.
c. After throwing:  These types of advices execute only if joinpoint method exits by throwing an exception and are configured using @AfterThrowing annotation mark.
d. After (finally): These types of advices execute after a joinpoint method, regardless of the method’s exit whether normally or exceptional return and are configured using @After annotation mark.
e. Around: These types of advices execute before and after a joinpoint and are configured using @Around annotation mark."
===###	What do you mean by Proxy in Spring Framework?
===###	What are the different AOP implementations?
===###	"What is Spring Security?
To perform dependency injection on servlet filter component it can’t be possible because its class must be maintained by IOC container as spring bean having configuration in spring bean configuration file. Due to this it can’t take request from client as filter is not configured as web component in web.xml file. To overcome this problem we configure a special proxy filter in web.xml file to take the request from the client to pass the request to filter component that is there in IOC container as spring bean. The special filter is “org.springframework.web.filter.DelegatingProxyFilter”. But we need to match logical name  of DelegatingProxyFilter with bean name of filter component.

What is the Spring Security internal Arcitecture flow?
Answer: First DelegatingFilterProxy will get the request and it will pass that request to Authentication manager to ask that URL is restricted or not if that is not restricted url that request will come back to DelegatingFilterProxy and it will forward to DispatcherServlet. Authentication Manager will search in the AuthenticationCOnfiguration file the URL is restricted or not. If the URL ir Restricted That will ask the details to AuthenticationProvider to get the details of the user and it will do the Authentication. If the User is Authorised User it will forward the URL to DispatcherServlet."
===###	"
What are the Spring scopes?
Answer: Sigleton, prototype, request, session, global session.

what is the default scope?
Answer: Signleton."
===###	"Define @RequestMapping annotation
Answer: To map the HTTP request to any other class or method in the controller which handles requests, @RequestMapping is being used. The annotations can be appealed at two levels:

Class level
Method level"
===###	"What is the difference between ApplicationContext and BeanFactory in Spring?
A: The BeanFactory comes with only basic features while ApplicationContext has advanced features that support enterprise application development. ApplicationContext included all functionalities of the BeanFactory container.

BeanFactory provides the basic support for DI (dependency injection) and is defined by the org.springframework.beans.factory.BeanFactory interface. BeanFactory should only be used when memory consumption is critical (e.g. applet-based applications and light applications for mobile devices).

Spring ApplicationContext adds more enterprise-specific functionality such as the ability to publish application events to interested event listeners. This container is defined by the org.springframework.context.ApplicationContext interface."
===###
===###
===###
===###	How do you use spring-resttemplate?
===###	How do you deal with log?
===###	"
Which Logging Frameworks do you use? how to configure that?:
Logging in Java requires using one or more logging frameworks. These frameworks provide the objects, methods, and configuration necessary to create and send log messages. Java provides a built-in framework in the java.util.logging package. There are also many third-party frameworks including Log4j, Logback, and tinylog. You can also use an abstraction layer, such as SLF4J and Apache Commons Logging, which decouples your code from the underlying logging framework so you can switch between logging frameworks on the fly.

Choosing a logging solution depends on a number of factors including the available features, the complexity of your logging needs, ease of use, and personal choice. Another factor to consider is compatibility with other projects. For example, Apache Tomcat is hard-coded to use java.util.logging, although you can redirect logs to an alternative framework. You will need to account for your environment and dependencies when choosing a framework.

For most developers, Log4j is a good choice as it provides good performance, is extremely configurable, and has a very active development community. If you plan on integrating other Java libraries or applications into your own, consider using SLF4J with the Log4j binding for the greatest compatibility."
