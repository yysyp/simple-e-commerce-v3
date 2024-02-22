#####1	Could you please give a brief introduction about yourself? Can you tell me a little bit about yourself?
#####2	Why are you leaving your job?
#####3	Where do you see yourself in five years?
#####4	What is your least favorite thing about architecture?
#####5	What Is Spring Cloud?
#####6	What are the components of the microservices architecture?
#####7	How do you hand distributed Transactions in the Microservice?  how to handle a transaction across multiple services?
#####8	What Is Spring Boot?
#####9	What is Docker?
#####10	Which Embedded Containers Are Supported By Spring Boot?
#####11	What is Dependency Injection?
#####12	Describe AOP/What are the different AOP implementations?
#####13	what is DDD domain driven development?
#####14	What kind of design patterns do you know?
#####15	Could you explain the Singleton pattern? / Describe in how many ways can you create a singleton pattern? / Can you write Thread-safe Singleton in Java?
#####16	What is Spring Security? How spring security works? The architecture, the sequence flow
#####17	How many modules are there in Spring Framework and what are they?
#####18	Question: Please explain the various annotations supported by Spring.
#####19	"What is classloader?/What is threads-context-class-loader?
Classloader is a subsystem of JVM which is used to load class files. Whenever we run the java program, it is loaded first by the classloader. There are three built-in classloaders in Java.

1，Bootstrap ClassLoader: This is the first classloader which is the superclass of Extension classloader. It loads the rt.jar file which contains all class files of Java Standard Edition like java.lang package classes, java.net package classes, java.util package classes, java.io package classes, java.sql package classes, etc.
2，Extension ClassLoader: This is the child classloader of Bootstrap and parent classloader of System classloader. It loads the jar files located inside $JAVA_HOME/jre/lib/ext directory.
3，System/Application ClassLoader: This is the child classloader of Extension classloader. It loads the class files from the classpath. By default, the classpath is set to the current directory. You can change the classpath using ""-cp"" or ""-classpath"" switch. It is also known as Application classloader.

"
#####20	What is Reflection API in Java?
#####21	Describe synchronization with respect to multi-threading. / What is the difference between synchronized key word and Lock(like reentrylock etc.)?
#####22	TCP协议中有一种状态叫做：time_wait, 你了解为什么又这个状态吗？，这种状态对系统有什么不利影响？那要如何解决time_wait过多的问题？
#####23	"Nginx 的负载均衡策略，简单按照下面的规则选择就可以了。
Load balancing strategies nginx has a couple of strategies to pick a server to send requests to. By default, it uses a round robin algorithm to decide the server to which a request should be sent."
#####24	What Does Eventually Consistent Mean?
#####25	What is the difference between List and Set?
#####26	Describe Various Implementations of the Map Interface and Their Use Case Differences.
#####27	What is the difference between HashMap and TreeMap?
#####28	What Is the Difference Between Hashset and Treeset?
#####29	How to remove duplicates from ArrayList?
#####30	How Is Hashmap Implemented in Java? How Does Its Implementation Use Hashcode and Equals Methods of Objects?
#####31	What Is the Purpose of the Initial Capacity and Load Factor Parameters of a Hashmap? What Are Their Default Values?
#####32	What Is the Difference Between Fail-Fast and Fail-Safe Iterators?
#####33	What is a finally block? Is there a case when finally will not execute?
#####34	What is the the process of Class,Object Initialization and Instantiation in the JVM?
#####35	What is Interprocess Communication?/what are the ways to do that?
#####36	"How is the safety of a thread achieved? / When should we use the volatile keyword?
What is the volatile keyword in Java?"
#####37	What are the main components of concurrency API?
#####38	What is the difference between Java Callable interface and Runnable interface?
#####39	How to Troubleshoot Java CPU Usage 100% Issues
#####40	What is the difference between wait() and sleep() method?
#####41	What is a deadlock in Java? / How to detect a deadlock condition? How can it be avoided?
#####42	What is JWT token?/How to use it?
#####43	Enlist the several components in Kafka
#####44	Explain the role of the offset.
#####45	What is the difference between Http GET and Http POST method?
#####46	What are ACID properties in a transaction?
#####47	What is ThreadLocal class and when should we use it?
#####48	What are the different types of garbage collectors in Java? / When is class garbage collected?
#####49	How to bind a DNS name to a EC2 in AWS?
#####50	What is the difference between Cname and Alias in AWS?
#####51	How to use cloudwatch in AWS?
#####52	How to design a REST API? / How to Secure API Endpoint?
#####53	How to design authentication and authorization system for REST backend / Ajax front End Application?
#####54	Can you list some key software design principles? / Could you explain the Singleton pattern?
#####55	Can you describe the high-level architecture of a recent application you had worked on? / Can you discuss some of the high level architectures you are experienced with? / What causes performance issues in Java?
#####56	In your experience, what are some of the key security considerations in an enterprise Java application?
#####57	How Can You Set Up Service Discovery?
#####58	Why Would You Opt For Microservices Architecture? / What are the characteristics of Microservices? / What are the key differences between SOA and Microservices Architecture? / What are the pros and cons of Microservice Architecture? / Monolithic Vs. Micro Services
#####59	What is Idempotence and How is it Used? / What is an Idempotence and where it is used?
#####60	What is OAuth?
#####61	How to generate unique id in distributed system?
#####62	What are the pros and cons of each? / DataBase Master-master vs master-slave database architecture?
#####63	What Is Hystrix For?
