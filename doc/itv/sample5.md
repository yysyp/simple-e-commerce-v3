#####1	Could you please give a brief introduction about yourself? Can you tell me a little bit about yourself?
#####2	Why are you leaving your job?
#####3	Where do you see yourself in five years?
#####4	What is your least favorite thing about architecture?
#####5	What Is Spring Cloud?	"Probably, the first microservices interview question that you may encounter. Spring Cloud in Microservices, is a system that provides integration with external systems. It is a short-lived framework that builds an application in a fast manner. Being associated with the finite amount of data processing, it plays a very important role in Microservice architecture.

For typical use cases, Spring cloud provides some out of the box experience and a set of extensive features mentioned-below:

Versioned and distributed configuration
Discovery of service registration
Service to service calls
Routing
Circuit breakers and load balancing
Cluster state and leadership election
Global locks and distributed messaging"
#####6	What are the components of the microservices architecture?	"What are the components of the microservices architecture?
The following are the main components of the microservices architecture:

Clients
Identity provider
API gateway
Static content
Management
Service discovery
Content delivery networks
Remote services"
#####7	How do you hand distributed Transactions in the Microservice?  how to handle a transaction across multiple services?	"One of the problems to solve in a microservices architecture is how to handle a transaction across multiple services. A microservice, from its core principles and in its true context, is a distributed system. A transaction is distributed to multiple services that are called sequentially or parallelly to complete the entire transaction. With a microservices architecture, the most common pattern is database per microservice, so transactions also need to span across different databases.
Below are the two approaches to address these problems:
1, Two-phase commit (2PC)
2, Saga"
#####8	What Is Spring Boot?	"Spring boot is a major topic under the umbrella of microservices interview questions.
With the new functionalities that have been added, Spring got more complex. Whenever you are starting a new project it is mandatory to add a new build path or Maven dependencies. In short, you will need to do everything from scratch. Spring Boot is the solution that will help you to avoid all the code configurations. Explaining by a real time example is better while answering this common microservices interview question. If you are cooking a dish, the ingredients can be considered to be Spring. While Spring Boot is the completely cooked recipe."
#####9	What is Docker?	"Answer
Docker is a containerization platform which packages your application and all its dependencies together in the form of containers so as to ensure that your application works seamlessly in any environment be it development or test or production.
Docker containers, wrap a piece of software in a complete filesystem that contains everything needed to run: code, runtime, system tools, system libraries etc. anything that can be installed on a server.
This guarantees that the software will always run the same, regardless of its environment."
#####10	Which Embedded Containers Are Supported By Spring Boot?	"Whenever you are creating a Java Application, deployment can be done by 2 methods:

By using an application container that is external
You can also embed the container inside your jar file.
Spring Boot contains Jetty, Tomcat and undertow servers, all of which are embedded.

Jetty – Used in a wide number of projects, Eclipse Jetty can be embedded in frameworks and application servers, tools as well as clusters.

Tomcat – Apache Tomcat is an open source JavaServer pages implementation which works well with embedded systems.

Undertow – A flexible and prominent web server that uses small single handlers to develop a web server."
#####11	What is Dependency Injection?	In software engineering, dependency injection is a technique whereby one object supplies the dependencies of another object. A dependency is an object that can be used. An injection is the passing of a dependency to a dependent object that would use it. The service is made part of the client’s state. Dependency injection is also a creational design pattern.
#####12	Describe AOP/What are the different AOP implementations?	"Describe AOP.
Aspect-oriented programming or AOP is a programming technique which allows programmers to modularize crosscutting concerns or behavior that cuts across the typical divisions of responsibility. Examples of cross-cutting concerns can be logging and transaction management. The core of AOP is an aspect. It encapsulates behaviors that can affect multiple classes into reusable modules."
#####13	what is DDD domain driven development?	"Domain-driven design (DDD) is an approach to software development for complex needs by connecting the implementation to an evolving model.

In order to create good software, you have to know what that software is all about. You cannot create a banking software system unless you have a good understanding of what banking is all about, one must understand the domain of banking."
#####14	What kind of design patterns do you know?	"There are three basic types of design patterns:

structural
creational
behavioural
Structural patterns generally deal with relationships between entities, making it easier for these entities to work together.

Creational patterns provide instantiation mechanisms, making it easier to create objects in a way that suits the situation.

Behavioural patterns are used in communications between entities and make it easier and more flexible for these entities to communicate.

Some examples of design patterns

Layered pattern
Client-server pattern
Master-slave pattern
Pipe-filter pattern
Broker pattern
Peer-to-peer pattern
Event-bus pattern
Model-view-controller pattern
Blackboard pattern
Interpreter pattern"
#####15	Could you explain the Singleton pattern? / Describe in how many ways can you create a singleton pattern? / Can you write Thread-safe Singleton in Java?	"There are two ways of creating a Singleton pattern.

1. Early Instantiation

It is responsible for the creation of instance at load time.

2. Lazy Instantiation

It is responsible for the creation of instance when required.


There are many ways to write a Thread-safe singleton in Java.

Thread-safe Singleton can be written by writing singleton using double-checked locking.
Another way is, by using static Singleton instance initialized during class loading.
By using Java enum to create a thread-safe singleton, this is the most straightforward way."
#####16	What is Spring Security? How spring security works? The architecture, the sequence flow	"To perform dependency injection on servlet filter component it can’t be possible because its class must be maintained by IOC container as spring bean having configuration in spring bean configuration file. Due to this it can’t take request from client as filter is not configured as web component in web.xml file. To overcome this problem we configure a special proxy filter in web.xml file to take the request from the client to pass the request to filter component that is there in IOC container as spring bean. The special filter is “org.springframework.web.filter.DelegatingProxyFilter”. But we need to match logical name  of DelegatingProxyFilter with bean name of filter component.

What is the Spring Security internal Arcitecture flow?
Answer: First DelegatingFilterProxy will get the request and it will pass that request to Authentication manager to ask that URL is restricted or not if that is not restricted url that request will come back to DelegatingFilterProxy and it will forward to DispatcherServlet. Authentication Manager will search in the AuthenticationCOnfiguration file the URL is restricted or not. If the URL ir Restricted That will ask the details to AuthenticationProvider to get the details of the user and it will do the Authentication. If the User is Authorised User it will forward the URL to DispatcherServlet."
#####17	How many modules are there in Spring Framework and what are they?	"Core module
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
#####18	Question: Please explain the various annotations supported by Spring.	"`@Autowired – Used for autowiring bean on the setter methods, a property, constructor or methods with arbitrary names or several arguments. It provides precise control over how and where the autowiring needs to be done.
@Component – A generic stereotype for a Spring-managed component, it marks a Java class as a bean that can be picked up by a component-scanning mechanism and pull it into the application context.
@Controller – Marks a class as a Spring Web MVC controller. Beans marked with this annotation are automatically imported into the Dependency Injection container.
@Qualifier – Used along with @Autowired annotation for specifying that only one of the several yet alike beans, needs to be wired.
@Repository – A specialization of the component annotation with almost identical use and functionality. Specifically, it provides additional benefits for DAOs (Data Access Objects).
@RequestMapping – Maps a particular HTTP request method to a specific class or method in controller responsible for handling the respective request.
@Required – Applied to bean property setter methods, it indicates that the bean property needs to be populated at the configuration time with the use of an explicit property value present in a bean definition or via autowiring. In case the bean property is not populated, the container throws the BeanInitializationException message.
@Service – Another specialization of the component annotation. Although it doesn’t offer any additional behavior over the component annotation, it can be used over the @component annotation in service-layer classes for specifying the intent in a better way."
#####19	"What is classloader?/What is threads-context-class-loader?
Classloader is a subsystem of JVM which is used to load class files. Whenever we run the java program, it is loaded first by the classloader. There are three built-in classloaders in Java.

1，Bootstrap ClassLoader: This is the first classloader which is the superclass of Extension classloader. It loads the rt.jar file which contains all class files of Java Standard Edition like java.lang package classes, java.net package classes, java.util package classes, java.io package classes, java.sql package classes, etc.
2，Extension ClassLoader: This is the child classloader of Bootstrap and parent classloader of System classloader. It loads the jar files located inside $JAVA_HOME/jre/lib/ext directory.
3，System/Application ClassLoader: This is the child classloader of Extension classloader. It loads the class files from the classpath. By default, the classpath is set to the current directory. You can change the classpath using ""-cp"" or ""-classpath"" switch. It is also known as Application classloader.

"
#####20	What is Reflection API in Java?	"What is Reflection API in Java?
- The Reflection API allows Java code to examine classes and objects at run time.
- The new reflection classes allow you to call another class's methods dynamically at run time.
- With the reflection classes, you can also examine an instance's fields and change the fields' contents.
- It is also possible to instantiate new objects, invoke methods and get/set field values using reflection.
- The Reflection API consists of the java.lang.Class class and the java.lang.reflect classes: Field, Method, Constructor, Array, and Modifier."
#####21	Describe synchronization with respect to multi-threading. / What is the difference between synchronized key word and Lock(like reentrylock etc.)?	"Describe synchronization with respect to multi-threading.
Synchronization is the method to control the access of multiple threads to shared resources, with respect to multi-threading. One thread can modify a shared variable when not in synchronization even when another thread is in the process of using or updating the same shared variable. This can lead to significant errors."
#####22	TCP协议中有一种状态叫做：time_wait, 你了解为什么又这个状态吗？，这种状态对系统有什么不利影响？那要如何解决time_wait过多的问题？	这个状态发生在主动发起关闭的一方，当它发出最后一个ack的时候需要等待2MSL（Maximum segment Lifetime 报文最大生存时间），在这个等待时间里它是处于time_wait状态中的，这个状态存在的原因有两个，一个是可以可靠的关闭TCP连接，一个是防止上一次的连接包影响本次连接。这种状态的连接会占用端口造成资源浪费。可以修改一些内核参数：tcp_max_tw_buckets.
#####23	"Nginx 的负载均衡策略，简单按照下面的规则选择就可以了。
Load balancing strategies nginx has a couple of strategies to pick a server to send requests to. By default, it uses a round robin algorithm to decide the server to which a request should be sent."	"例如，Nginx 的负载均衡策略，简单按照下面的规则选择就可以了。
轮询（默认）
每个请求按时间顺序逐一分配到不同的后端服务器，后端服务器分配的请求数基本一致，如
果后端服务器“down 掉”，能自动剔除。

加权轮询
根据权重来进行轮询，权重高的服务器分配的请求更多，主要适应于后端服务器性能不均的
情况，如新老服务器混用。

ip_hash
每个请求按访问 IP 的 hash 结果分配，这样每个访客固定访问一个后端服务器，主要用于
解决 session 的问题，如购物车类的应用。

fair
按后端服务器的响应时间来分配请求，响应时间短的优先分配，能够最大化地平衡各后端服
务器的压力，可以适用于后端服务器性能不均衡的情况，也可以防止某台后端服务器性能不
足的情况下还继续接收同样多的请求从而造成雪崩效应。

url_hash
按访问 URL 的 hash 结果来分配请求，每个 URL 定向到同一个后端服务器，适用于后端服
务器能够将 URL 的响应结果缓存的情况。"
#####24	What Does Eventually Consistent Mean?	"Unlike relational database property of Strict consistency, eventual consistency property of a system ensures that any transaction will eventually (not immediately) bring the database from one valid state to another. This means there can be intermediate states that are not consistent between multiple nodes.

Eventually consistent systems are useful at scenarios where absolute consistency is not critical. For example in case of Twitter status update, if some users of the system do not see the latest status from a particular user its may not be very devastating for system.

Eventually consistent systems can not be used for use cases where absolute/strict consistency is required. For example a banking transactions system can not be using eventual consistency since it must consistently have the state of a transaction at any point of time. Your account balance should not show different amount if accessed from different ATM machines."
#####25	What is the difference between List and Set?	"The List and Set both extend the collection interface. However, there are some differences between the both which are listed below.

The List can contain duplicate elements whereas Set includes unique items.
The List is an ordered collection which maintains the insertion order whereas Set is an unordered collection which does not preserve the insertion order.
The List interface contains a single legacy class which is Vector class whereas Set interface does not have any legacy class.
The List interface can allow n number of null values whereas Set interface only allows a single null value."
#####26	Describe Various Implementations of the Map Interface and Their Use Case Differences.	"One of the most often used implementations of the Map interface is the HashMap. It is a typical hash map data structure that allows accessing elements in constant time, or O(1), but does not preserve order and is not thread-safe.

To preserve insertion order of elements, you can use the LinkedHashMap class which extends the HashMap and additionally ties the elements into a linked list, with foreseeable overhead.

The TreeMap class stores its elements in a red-black tree structure, which allows accessing elements in logarithmic time, or O(log(n)). It is slower than the HashMap for most cases, but it allows keeping the elements in order according to some Comparator.

The ConcurrentHashMap is a thread-safe implementation of a hash map. It provides full concurrency of retrievals (as the get operation does not entail locking) and high expected concurrency of updates.


The Hashtable class has been in Java since version 1.0. It is not deprecated but is mostly considered obsolete. It is a thread-safe hash map, but unlike ConcurrentHashMap, all its methods are simply synchronized, which means that all operations on this map block, even retrieval of independent values."
#####27	What is the difference between HashMap and TreeMap?	"The differences between the HashMap and TreeMap are given below.

HashMap maintains no order, but TreeMap maintains ascending order.
HashMap is implemented by hash table whereas TreeMap is implemented by a Tree structure.
HashMap can be sorted by Key or value whereas TreeMap can be sorted by Key.
HashMap may contain a null key with multiple null values whereas TreeMap cannot hold a null key but can have multiple null values."
#####28	What Is the Difference Between Hashset and Treeset?	"Both HashSet and TreeSet classes implement the Set interface and represent sets of distinct elements. Additionally, TreeSet implements the NavigableSet interface. This interface defines methods that take advantage of the ordering of elements.

HashSet is internally based on a HashMap, and TreeSet is backed by a TreeMap instance, which defines their properties: HashSet does not keep elements in any particular order. Iteration over the elements in a HashSet produces them in a shuffled order. TreeSet, on the other hand, produces elements in order according to some predefined Comparator."
#####29	How to remove duplicates from ArrayList?	"There are two ways to remove duplicates from the ArrayList.

Using HashSet: By using HashSet we can remove the duplicate element from the ArrayList, but it will not then preserve the insertion order.
Using LinkedHashSet: We can also maintain the insertion order by using LinkedHashSet instead of HashSet.
The Process to remove duplicate elements from ArrayList using the LinkedHashSet:

Copy all the elements of ArrayList to LinkedHashSet.
Empty the ArrayList using clear() method, which will remove all the elements from the list.
Now copy all the elements of LinkedHashset to ArrayList."
#####30	How Is Hashmap Implemented in Java? How Does Its Implementation Use Hashcode and Equals Methods of Objects?	"The HashMap class represents a typical hash map data structure with certain design choices.

The HashMap is backed by a resizable array that has a size of power-of-two. When the element is added to a HashMap, first its hashCode is calculated (an int value). Then a certain number of lower bits of this value are used as an array index. This index directly points to the cell of the array (called a bucket) where this key-value pair should be placed. Accessing an element by its index in an array is a very fast O(1) operation, which is the main feature of a hash map structure.A hashCode is not unique, however, and even for different hashCodes, we may receive the same array position. This is called a collision. There is more than one way of resolving collisions in the hash map data structures. In Java's HashMap, each bucket actually refers not to a single object, but to a red-black tree of all objects that landed in this bucket (prior to Java 8, this was a linked list).

So when the HashMap has determined the bucket for a key, it has to traverse this tree to put the key-value pair in its place. If a pair with such key already exists in the bucket, it is replaced with a new one.

To retrieve the object by its key, the HashMap again has to calculate the hashCode for the key, find the corresponding bucket, traverse the tree, call equals on keys in the tree and find the matching one.

HashMap has O(1) complexity, or constant-time complexity, of putting and getting the elements. Of course, lots of collisions could degrade the performance to O(log(n)) time complexity in the worst case, when all elements land in a single bucket. This is usually solved by providing a good hash function with a uniform distribution.

When the HashMap internal array is filled (more on that in the next question), it is automatically resized to be twice as large. This operation infers rehashing (rebuilding of internal data structures), which is costly, so you should plan the size of your HashMap beforehand."
#####31	What Is the Purpose of the Initial Capacity and Load Factor Parameters of a Hashmap? What Are Their Default Values?	"The initialCapacity argument of the HashMap constructor affects the size of the internal data structure of the HashMap, but reasoning about the actual size of a map is a bit tricky. The HashMap‘s internal data structure is an array with the power-of-two size. So the initialCapacity argument value is increased to the next power-of-two (for instance, if you set it to 10, the actual size of the internal array will be 16).

The load factor of a HashMap is the ratio of the element count divided by the bucket count (i.e. internal array size). For instance, if a 16-bucket HashMap contains 12 elements, its load factor is 12/16 = 0.75. A high load factor means a lot of collisions, which in turn means that the map should be resized to the next power of two. So the loadFactor argument is a maximum value of the load factor of a map. When the map achieves this load factor, it resizes its internal array to the next power-of-two value.

The initialCapacity is 16 by default, and the loadFactor is 0.75 by default, so you could put 12 elements in a HashMap that was instantiated with the default constructor, and it would not resize. The same goes for the HashSet, which is backed by a HashMap instance internally.

Consequently, it is not trivial to come up with initialCapacity that satisfies your needs. This is why the Guava library has Maps.newHashMapWithExpectedSize() and Sets.newHashSetWithExpectedSize() methods that allow you to build a HashMap or a HashSet that can hold the expected number of elements without resizing."
#####32	What Is the Difference Between Fail-Fast and Fail-Safe Iterators?	"Iterators for different collections are either fail-fast or fail-safe, depending on how they react to concurrent modifications. The concurrent modification is not only a modification of collection from another thread but also modification from the same thread but using another iterator or modifying the collection directly.

Fail-fast iterators (those returned by HashMap, ArrayList, and other non-thread-safe collections) iterate over the collection's internal data structure, and they throw ConcurrentModificationException as soon as they detect a concurrent modification.

Fail-safe iterators (returned by thread-safe collections such as ConcurrentHashMap, CopyOnWriteArrayList) create a copy of the structure they iterate upon. They guarantee safety from concurrent modifications. Their drawbacks include excessive memory consumption and iteration over possibly out-of-date data in case the collection was modified."
#####33	What is a finally block? Is there a case when finally will not execute?	"Finally block is a block which always executes a set of statements. It is always associated with a try block regardless of any exception that occurs or not.
Yes, finally will not be executed if the program exits either by calling System.exit() or by causing a fatal error that causes the process to abort."
#####34	What is the the process of Class,Object Initialization and Instantiation in the JVM?	"It is important to distinguish between the initialization of a class, and initialization of an object.

Class Initialization

A class or interface is initialized upon first access, by assigning the compile time constant fields, then recursively initializing the superclass (if not already initialized), then processing the static initializers (which include the initializers for for the static fields that are not compile time constants).

As you have noticed, initialization of a class does not, by itself, trigger initialization of the interfaces it implements. Interfaces are therefore initialized when they are first accessed, typically by reading a field that is not a compile time constant. This access may occur during evaluation of an initializer, causing a recursive initialization.

Object Initialization

An object is initialized whenever a new object is created, typically by evaluation of a class instance creation expression. This proceeds as follows:

1, Assign the arguments for the constructor to newly created parameter variables for this constructor invocation.

2, If this constructor begins with an explicit constructor invocation (§8.8.7.1) of another constructor in the same class (using this), then evaluate the arguments and process that constructor invocation recursively using these same five steps. If that constructor invocation completes abruptly, then this procedure completes abruptly for the same reason; otherwise, continue with step 5.

3, This constructor does not begin with an explicit constructor invocation of another constructor in the same class (using this). If this constructor is for a class other than Object, then this constructor will begin with an explicit or implicit invocation of a superclass constructor (using super). Evaluate the arguments and process that superclass constructor invocation recursively using these same five steps. If that constructor invocation completes abruptly, then this procedure completes abruptly for the same reason. Otherwise, continue with step

4, Execute the instance initializers and instance variable initializers for this class, assigning the values of instance variable initializers to the corresponding instance variables, in the left-to-right order in which they appear textually in the source code for the class. If execution of any of these initializers results in an exception, then no further initializers are processed and this procedure completes abruptly with that same exception. Otherwise, continue with step 5.

5, Execute the rest of the body of this constructor. If that execution completes abruptly, then this procedure completes abruptly for the same reason. Otherwise, this procedure completes normally.

As we can see in step 3, the presence of an explicit call to the super constructor simply changes which super class constructor is invoked."
#####35	What is Interprocess Communication?/what are the ways to do that?	"Pipe
A pipe is a data channel that is unidirectional. Two pipes can be used to create a two-way data channel between two processes. This uses standard input and output methods. Pipes are used in all POSIX systems as well as Windows operating systems.

Socket
The socket is the endpoint for sending or receiving data in a network. This is true for data sent between processes on the same computer or data sent between different computers on the same network. Most of the operating systems use sockets for interprocess communication.

File
A file is a data record that may be stored on a disk or acquired on demand by a file server. Multiple processes can access a file as required. All operating systems use files for data storage.

Signal
Signals are useful in interprocess communication in a limited way. They are system messages that are sent from one process to another. Normally, signals are not used to transfer data but are used for remote commands between processes.

Shared Memory
Shared memory is the memory that can be simultaneously accessed by multiple processes. This is done so that the processes can communicate with each other. All POSIX systems, as well as Windows operating systems use shared memory.

Message Queue
Multiple processes can read and write data to the message queue without being connected to each other. Messages are stored in the queue until their recipient retrieves them. Message queues are quite useful for interprocess communication and are used by most operating systems."
#####36	"How is the safety of a thread achieved? / When should we use the volatile keyword?
What is the volatile keyword in Java?"	"If a method or class object can be used by multiple threads at a time without any race condition, then the class is thread-safe. Thread safety is used to make a program safe to use in multithreaded programming. It can be achieved by the following ways:

#Synchronization
#Using Volatile keyword
#Using a lock based mechanism
#Use of atomic wrapper classes


Volatile keyword is used in multithreaded programming to achieve the thread safety, as a change in one volatile variable is visible to all other threads so one variable can be used by one thread at a time.

A: The volatile keyword in Java is used to mark a Java variable as being stored in memory accessed by multiple threads. This means, that every read of a volatile variable will be read from the computer's main memory, and not from the CPU cache and that every time volatile variable is changed the change will be written to main memory, and not just to the CPU cache.

Since Java 5 the features around volatile variables are extended comparing to previous versions where they could be only written to and read from shared memory.

Answer: Volatile keyword in Java is used with variables and all the threads read its value directly from the memory location and don’t cache it. Volatile keyword makes sure that the read value is exactly the same as in the memory location."
#####37	What are the main components of concurrency API?	"Concurrency API can be developed using the class and interfaces of java.util.Concurrent package. There are the following classes and interfaces in java.util.Concurrent package.

Executor
FarkJoinPool
ExecutorService
ScheduledExecutorService
Future
TimeUnit(Enum)
CountDownLatch
CyclicBarrier
Semaphore
ThreadFactory
BlockingQueue
DelayQueue
Locks
Phaser"
#####38	What is the difference between Java Callable interface and Runnable interface?	"The Callable interface and Runnable interface both are used by the classes which wanted to execute with multiple threads. However, there are two main differences between the both :

A Callable <V> interface can return a result, whereas the Runnable interface cannot return any result.
A Callable <V> interface can throw a checked exception, whereas the Runnable interface cannot throw checked exception.
A Callable <V> interface cannot be used before the Java 5 whereas the Runnable interface can be used."
#####39	How to Troubleshoot Java CPU Usage 100% Issues
#####40	What is the difference between wait() and sleep() method?	"wait()	sleep()
1) The wait() method is defined in Object class.	The sleep() method is defined in Thread class.
2) The wait() method releases the lock.	The sleep() method doesn't release the lock."
#####41	What is a deadlock in Java? / How to detect a deadlock condition? How can it be avoided?	"A: Deadlock is a situation where two or more threads are blocked forever, waiting for each other. In this situation, program will hang forever because neither of the threads in position to proceed and waiting for each other to release the lock.

We can detect the deadlock condition by running the code on cmd and collecting the Thread Dump, and if any deadlock is present in the code, then a message will appear on cmd.
Ways to avoid the deadlock condition in Java:
Avoid Nested lock: Nested lock is the common reason for deadlock as deadlock occurs when we provide locks to various threads so we should give one lock to only one thread at some particular time.
Avoid unnecessary locks: we must avoid the locks which are not required.
Using thread join: Thread join helps to wait for a thread until another thread doesn't finish its execution so we can avoid deadlock by maximum use of join method."
#####42	What is JWT token?/How to use it?
#####43	Enlist the several components in Kafka	"The most important elements of Kafka are:
Topic –
Kafka Topic is the bunch or a collection of messages.

Producer –
In Kafka, Producers issue communications as well as publishes messages to a Kafka topic.

Consumer –
Kafka Consumers subscribes to a topic(s) and also reads and processes messages from the topic(s).

Brokers –
While it comes to manage storage of messages in the topic(s) we use Kafka Brokers.
For detailed understanding of Kafka components, go through, Kafka – Architecture

#####What are the elements of Kafka?
The most important elements of Kafka are as follows:

Topic: It is a bunch of similar kinds of messages.
Producer: Using this, one can issue communications to the topic.
Consumer: It endures to a variety of topics and takes data from brokers.
Broker: This is the place where the issued messages are stored.
Get a detailed understanding of Kafka from this comprehensive Kafka Tutorial!"
#####44	Explain the role of the offset.	There is a sequential ID number given to the messages in the partitions what we call, an offset. So, to identify each message in the partition uniquely, we use these offsets.
#####45	What is the difference between Http GET and Http POST method?	"Both GET and POST method is used to transfer data from client to server in HTTP protocol but Main difference between POST and GET method is that GET carries request parameter appended in URL string while POST carries request parameter in message body which makes it more secure way of transferring data from client to server in http protocol.

In general, POST should be used for requests that potentially modify state on the server, and GET should be used for read-only operations.
GET is idempotent: it is for obtaining a resource, without changing anything on the server. As a consequence it should be perfectly safe to resubmit a GET request.

POST is not: it is for updating information on the server. It can therefore not be assumed that it is safe to re-submit the request which is why most browsers ask for confirmation when you hit refresh on a POST request.

In terms of security, no difference. POST is more obscure, perhaps, but that's a very different thing. Security needs to be added at another layer, for example SSL.

#####Some notes on GET requests:
GET requests can be cached
GET requests remain in the browser history
GET requests can be bookmarked
GET requests should never be used when dealing with sensitive data
GET requests have length restrictions
GET requests should be used only to retrieve data

#####Some notes on POST requests:
POST requests are never cached
POST requests do not remain in the browser history
POST requests cannot be bookmarked
POST requests have no restrictions on data length"
#####46	What are ACID properties in a transaction?	"Answer: In order to maintain consistency in a database ‘before and after’ transactions, certain properties are followed. They are

Atomicity: This means the transaction must happen fully and cannot be left midway.
Consistency: To maintain integrity constraints hence valid data enters the database
Isolation: Controls Concurrency
Durability: Once a transaction is committed it remains committed"
#####47	What is ThreadLocal class and when should we use it? 	A: Instance of this class store data for each Thread independently, which means that other Threads can not access specific Thread's data. ThreadLocal instance lasts as associated thread - as soon as the thread is done, ThreadLocal instance is ready to be collected by Garbage Collector.
#####48	What are the different types of garbage collectors in Java? / When is class garbage collected?	"Garbage collection in Java a program which helps in implicit memory management. Since in Java, using the new keyword you can create objects dynamically, which once created will consume some memory. Once the job is done and there are no more references left to the object, Java using garbage collection destroys the object and relieves the memory occupied by it. Java provides four types of garbage collectors:

#Serial Garbage Collector
#Parallel Garbage Collector
#CMS Garbage Collector
#G1 Garbage Collector

`- Java uses the garbage collector to free memory which is occupied by those objects which is no more reference by any of the program.
- An object becomes eligible for Garbage Collection when no live thread can access it.
- There are many ways to make a class reachable and thus prevent it from being eligible for Garbage Collection:
1. Objects of that class are still reachable.
2. Class object representing the class is still reachable.
3. ClassLoader that loaded the class is still reachable.
4. Other classes loaded by the ClassLoader are still reachable.
- When all of the above are false, then the ClassLoader together with all classes it loaded are eligible for Garbage Collection."
#####49	How to bind a DNS name to a EC2 in AWS?
#####50	What is the difference between Cname and Alias in AWS?
#####51	How to use cloudwatch in AWS?
#####52	How to design a REST API? / How to Secure API Endpoint?	"#HTTPS always
#One-way password hashing: The only suggested option is asymmetric (or “one-way”) encryption algorithms for storing passwords.
#Strong authentication: Now, almost every API has a form of authentication, but in my opinion, the OAuth2 system works the best. As opposed to other authentication methods, it divides your account into resources and allows only limited access to the auth token bearer.At the same time, another very good practice to set tokens to expire
#Apply rate limiting
#Validate input
#Enforce IP address filtering, if applicable"
#####53	How to design authentication and authorization system for REST backend / Ajax front End Application?	"You might also want to take a look at Jasig CAS - Single Sign-On for the Web. It has a REST API and a protocol (Proxy Tickets) that allows services to proxy user AuthN to backend services like you described in option 6. http://www.jasig.org/cas

Briefly...the application that serves up the AJAX client is protected with Spring Security (supports CAS out of the box) and gets a Proxy Granting Ticket that you embed in the AJAX client. The AJAX client uses the PGT to get Proxy Tickets for your REST services...protected with Spring Security too. The REST services get an authenticated userId without every touching primary credentials.

Alternative, you could keep the PGT on the server and use AJAX calls to retrieve Proxy Tickets that are then used by the AJAX client to call you REST services."
#####54	Can you list some key software design principles? / Could you explain the Singleton pattern?
#####55	Can you describe the high-level architecture of a recent application you had worked on? / Can you discuss some of the high level architectures you are experienced with? / What causes performance issues in Java? 	"1) to draw an architectural diagram on a white board and
2) to provide a brief description of the architecture, and
3) to elaborate on decisions, pros, cons, challenges, risks, etc …."
#####56	In your experience, what are some of the key security considerations in an enterprise Java application?
#####57	How Can You Set Up Service Discovery?	"There are multiple ways to set up service discovery. I’ll choose the one that I think to be most efficient, Eureka by Netflix. It is a hassle-free procedure that does not weigh much on the application. Plus, it supports numerous types of web applications.

Eureka configuration involves 2 steps – Client configuration and server configuration.
Client configuration can be done easily by using the property files. In the classpath, Eureka searches for eureka-client.properties. It also searches for overrides caused by the environment in property files that are environment-specific.

For server configuration, you have to configure the client first. Once that is done, the server fires up a client which is used to find other servers. The Eureka server, by default, uses the Client configuration to find the peer server."
#####58	Why Would You Opt For Microservices Architecture? / What are the characteristics of Microservices? / What are the key differences between SOA and Microservices Architecture? / What are the pros and cons of Microservice Architecture? / Monolithic Vs. Micro Services	" A very common microservices interview question which you should be ready for! There are plenty of pros that are offered by Microservices architecture. Here are a few of them:

Microservices can adapt easily to other frameworks or technologies.
Failure of a single process does not affect the entire system.
Provides support to big enterprises as well as small teams.
Can be deployed independently and in relatively less time.

Micro services architecture where each business capability will be an independent service having its own database, infrastructure & programming language. This has the advantages of being able to independently scale an individual service, independently deploy a service more frequently, looser coupling of services, separate teams can manage & specialise in different services, parallel development, independent testing of services and if a service is down the site can still function with the other services."
#####59	What is Idempotence and How is it Used? / What is an Idempotence and where it is used?	"Idempotence refers to a scenario where you perform a task repetitively but the end result remains constant or similar.

Idempotence is mostly used as a data source or a remote service in a way that when it receives more than one instructions, it processes only one instruction.

That’s all from our side. However, if you are seriously thinking about a technology switch, don’t depend on these microservices interview questions alone. Since experienced interviewers will also ask cross questions to check how much in-depth knowledge you have. Go for courses or online tutorials and study about Microservices in details so that you can answer the questions stated above along with connected questions as well.

Idempotence is the property of being able to do something twice in such a way that the end result will remain the same i.e. as if it had been done once only.

Usage: Idempotence is used at the remote service, or data source so that, when it receives the instruction more than once, it only processes the instruction once.
"
#####60	What is OAuth?	OAuth stands for open authorization protocol. This allows accessing the resources of the resource owner by enabling the client applications on HTTP services such as third-party providers Facebook, GitHub, etc. So with this, you can share resources stored on one site with another site without using their credentials.
#####61	How to generate unique id in distributed system?	Twitter's Snowflake id generator
#####62	What are the pros and cons of each? / DataBase Master-master vs master-slave database architecture? 	"Master-slave architectures are used to help stabilize a system. Master is the true data keeper while a slave is a replication of master. Cache/caching is an option but using it as complementary to the master-slave system would be better. Replication is the process of synchronizing data from the master to slave. Techniques to get realtime data can involve inserting in both master and slave to even realtime data replication, but it all depends on your needs.

We're trading off availability, consistency and complexity. To address the last question first: Does this matter? Yes very much! The choices concerning how your data is to be managed is absolutely fundamental, and there's no ""Best Practice"" dodging the decisions. You need to understand your particular requirements.

There's a fundamental tension:

One copy: consistency is easy, but if it happens to be down everybody is out of the water, and if people are remote then may pay horrid communication costs. Bring portable devices, which may need to operate disconnected, into the picture and one copy won't cut it.

Master Slave: consistency is not too difficult because each piece of data has exactly one owning master. But then what do you do if you can't see that master, some kind of postponed work is needed.

Master-Master: well if you can make it work then it seems to offer everything, no single point of failure, everyone can work all the time. The trouble with this is that it is very hard to preserve absolute consistency.

Wikipedia seems to have a nice summary of the advantages and disadvantages

>>Advantages

#If one master fails, other masters will continue to update the database.

#Masters can be located in several physical sites i.e. distributed across the network.

>>Disadvantages

#Most multi-master replication systems are only loosely consistent, i.e. lazy and asynchronous, violating ACID properties.

#Eager replication systems are complex and introduce some communication latency.

#Issues such as conflict resolution can become intractable as the number of nodes involved rises and the required latency decreases.

============================================================================================================
While researching the various database architectures as well. I have compiled a good bit of information that might be relevant to someone else researching in the future. I came across

Master-Slave Replication
Master-Master Replication
MySQL Cluster
I have decided to settle for using MySQL Cluster for my use case. However please see below for the various pros and cons that I have compiled

1. Master-Slave Replication

Pros

#Analytic applications can read from the slave(s) without impacting the master
#Backups of the entire database of relatively no impact on the master
#Slaves can be taken offline and sync back to the master without any downtime
Cons

#In the instance of a failure, a slave has to be promoted to master to take over its place. No automatic failover
#Downtime and possibly loss of data when a master fails
#All writes also have to be made to the master in a master-slave design
#Each additional slave add some load to the master since the binary log have to be read and data copied to each slave
#Application might have to be restarted
2. Master-Master Replication

Pros

#Applications can read from both masters
#Distributes write load across both master nodes
#Simple, automatic and quick failover
Cons

#Loosely consistent
#Not as simple as master-slave to configure and deploy
3. MySQL Cluster

The new kid in town based on MySQL cluster design. MySQL cluster was developed with high availability and scalability in mind and is the ideal solution to be used for environments that require no downtime, high avalability and horizontal scalability.

See MySQL Cluster 101 for more information

Pros

#(High Avalability) No single point of failure
#Very high throughput
#99.99% uptime
#Auto-Sharding
#Real-Time Responsiveness
#On-Line Operations (Schema changes etc)
#Distributed writes
Cons

#See known limitations
You can visit for my Blog full breakdown including architecture diagrams that goes into further details about the 3 mentioned architectures."
