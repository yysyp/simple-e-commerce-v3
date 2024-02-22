#####1	Could you please give a brief introduction about yourself? Can you tell me a little bit about yourself?
#####2	Why are you leaving your job?
#####3	Where do you see yourself in 2-3 years?
#####4	What is your least favorite thing about architecture?
--------
#####   How do you validate Input parameters in Spring Controller? What's the "groups" property used for in say NotEmpty validation annotation. / What are the differences between @Valid and @Validated?
#####   How can we roll back a declarative transaction?
#####   What are @Transactional Propagation Levels used for? (REQUIRED/SUPPORTS/MANDATORY/REQUIRES_NEW/NOT_SUPPORTED/NEVER/NESTED)
#####   Is it possible to invoke transactional method from non transactional method within the same instance? This will not work. Why?
#####   How to use multiple transaction managers in the one application? (@Transactional specify the transaction manager to use)
#####   What Is Spring Security? / What is the mechanisms of spring security? / What's the difference between @Secured and @PreAuthorize in spring security?
#####1	What Is Spring AOP? /What are the types of advice in Spring AOP? 4
#####2	What is the Proxying mechanisms? Spring AOP uses either JDK dynamic proxies or CGLIB to create the proxy for a given target object./JDK dynamic proxy? How to crate a proxy dynamically through JDK reflection?
#####3	What do you understand by Collection Framework in Java?/Could you describe Various Implementations of the Map Interface and Their Use Case Differences?
#####   What is ConcurrentHashMap in JDK? / What is difference between ConcurrentHashMap and SynchronizedMap(Collections.synchronizedMap(map);)?
#####4	What is the process of Class & Object Initialization and Instantiation in the JVM?
#####5	How many ways can you create a singleton pattern? / Can you write Thread-safe Singleton in Java? / earger, lazy,
#####   You have thread T1, T2, and T3. How will you ensure that thread T2 is run after T1 and thread T3 after T2? (join)
#####   What are some common problems you have faced in multi-threading environment? How did you resolve it? (Memory-interference, race conditions, deadlock, livelock, and starvation)
#####6	How is the safety of a thread achieved? / When should we use the volatile keyword?
#####7	Describe synchronization with respect to multi-threading. / What is the difference between synchronized key word and Lock(ReentrantLock)?
#####8  How to avoid deadlock? / Deadlock and how to prevent it? /How will you fix deadlock in Java?
#####   What is the difference between CyclicBarriar and CountdownLatch in Java?
#####9	What is classloader? / what are the different types of classloader? / when should we use threads-context-class-loader?
#####10	What are the different types of garbage collectors in Java? / When is class garbage collected?
#####11	What is a finally block? Is there a case when finally will not execute?
#####12 How to implement Transactions Management in Spring Boot ? What are ACID properties in a transaction? (Atomicity/Consistency/Isolation/Durability)
#####14 How to implment concurrency Control In REST API? / How to avoid concurrent access to an Restful API endpoint? pessimistic/optimistic locking.
#####15 How do we rate limit our APIs? API limiting, which is also known as rate limiting? / requests throttling / Stemming the flood
#####16 How to deal with transactions in a distributed microservice environment?  / 2-Phase (XA) Commit.
#####17	How to generate unique id in distributed environment?
#####18 What is CAP theorem? / Consistency / Availability / Partition tolerance
#####19 What is eventual consistency?
#####20	How to troubleshhot 100% CPU usage problem in Java?
#####21 What are the Load balancing strategies in nginx？
#####22 What Are Messaging Queues, when should we use message queue? and How Do You Use Them?
#####   How to make sure a message is never lost? / preventing message loss ? (message acknowledgments, retry)
#####   How to deal with duplication in a message queue? ()
#####23 What are the components in Kafka? Explain the role of the offset?
#####24 How to avoid duplicated messages on Message queue?
#####25	What are the components of the microservices architecture? / Monolithic Architecture vs Microservices
#####26 Which are the different data types used in Redis? Strings / Hashes / Lists / Sets / Sorted Sets
#####27	How to use cloudwatch in AWS? / AWS message queue SQS / S3 / RDS / EIP ?
#####28	What is Interprocess Communication?/what are the ways to do that?
#####29	How to design authentication and authorization functions/module for REST backend / Ajax frontend Application?
#####30 How do you design a SSO (Single Sign On) function? / What is Single Sign-On (SSO) and How Does It Work?
#####13 What are the SQL Join types? Left JOIN /Right JOIN/Inner JOIN / Natural JOIN: This is a type of Inner JOIN/Cross JOIN
#####   What's the difference between And clause along with on and Where clause when used with left join in SQL script?
#####   what is databases indexes? how do they work? / MySQL (Primary[Dense/Sparse], Secondary, Clustering, Bitmap...)
#####   What are the differences between InnoDB and MyISAM in MySQL?
#####31 What is dynamic programming?/Dynamic programming is a paradigm that’s used to solve problems by way of solving every intermediate subproblem once and saving the answer in a table or array.
Efficient Approach: To optimize the above approach, the idea is to use Dynamic Programming as the above problem has overlapping subproblems that need to be calculated more than once, and to reduce that calculation use tabulation or memoization.

#Rotate the array to the right by k steps:
Given an array, rotate the array to the right by k steps, where k is non-negative.

Follow up:

Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
Could you do it in-place with O(1) extra space?


Example 1:

Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]
Example 2:

Input: nums = [-1,-100,3,99], k = 2
Output: [3,99,-1,-100]
Explanation:
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]

--->>
class Solution {
public void rotate(int[] nums, int k) {
int length = nums.length;
int[] copy = (int[])Arrays.copyOf(nums,length);
for (int i = 0; i < length; i++) {
nums[(i+k)%length] = copy[i];
}
}
}

#How to find the longest increasing subsequence in a given sequence?
2,5,4,3,7 --> 2,5,7 or 2,4,7 or 2,3,7
#Solution:
public int longestIncreasingSubsequence(int[] nums) {
if (nums == null || nums.length == 0) {
return 0;
}
int n = nums.length;
int[] dp = new int[n];
int max = 0;
for (int i = 0; i < n; i++) {

        dp[i] = 1;
        for (int j = 0; j < i; j++) {
            if (nums[j] <= nums[i]) {
                dp[i] = dp[i] > dp[j] + 1 ? dp[i] : dp[j] + 1;
            }
        }
        if (dp[i] > max) {
            max = dp[i];
        }
		
    }
    return max;
}

# Time:  O(n^2)
# Space: O(n)
# Traditional DP solution.
贪心+二分 Greedy Algorithm+binary search: O(nlogn)
树状数组维护 Binary Indexed Tree: O(nlogn)
#####32 Java SPI: Service Provider Interface
asynchronized / asynchronous / synchronous / simultaneously / parallelly
needs to be processed asynchronously
Message Queue:
> Persistence: Means that when failure occurs during message processing, the message will still be there.
> Durability: When a durable subscription is set up between a queue and a topic, the queue can be offline when the message hits the topic. Once the queue comes back online, the message can be received.
---------------------------------------------------------------------------------------------------
----------
Qns-2: What is the role of TransactionDefinition interface?
Ans: a. Isolation b. Propagation c. Timeout d. Read-only status
----------
Qns-4: How many types of isolation are there?
Ans: a. ISOLATION_DEFAULT: default isolation.
b. ISOLATION_READ_COMMITTED: dirty reads are prevented, non-repeatable and phantom reads are allowed.
c. ISOLATION_READ_UNCOMMITTED : dirty reads are allowed, no-repeatable and phantom reads are allowed.
d. ISOLATION_REPEATABLE_READ: dirty reads and non-repeatable reads are prevented but phantom reads are allowed.
e. ISOLATION_SERIALIZABLE : dirty , non- repeatable reads and phantom reads are prevented.
----------
Qns-5: How many types of Propagation are there?
Ans: Find the Propagation type. a. PROPAGATION_MANDATORY : supports current transaction and throws exception if no transaction available.
b. PROPAGATION_NESTED : runs with nested transaction
c. PROPAGATION_NEVER : does not run with current transaction and throws exception if current transaction exits.
d. PROPAGATION_NOT_SUPPORTED : runs non -transactionaly and does not support current transaction.
e. PROPAGATION_REQUIRED : runs with current transaction and create one if does not exist.
f. PROPAGATION_REQUIRES_NEW : creates new transaction and suspends if exits any.
g. PROPAGATION_SUPPORTS: runs current transaction and runs non -transactionaly
----------
How to deal with transactions Across Microservices?
What is Global(distributed transaction)/Local transactions? (multiple transactional resources database and message queue.)JTA.
1, Eventual Consistency and Compensation.
2,Two-phase commit protocol (or 2PC) is a mechanism for implementing a transaction across different software components (multiple databases, message queues etc.)
The XA standard is a specification for conducting the 2PC distributed transactions across the supporting resources. Any JTA-compliant application server (JBoss, GlassFish etc.) supports it out-of-the-box.

递归：Recursion/Recursive/Recursively
----------
kafka: What are the benefits of Kafka? / What are the advantages of Apache Kafka?
