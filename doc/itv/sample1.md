============================================================================================
--------------------------------------------------------------------------------------------
##Java 并发编程，Java内存模型

为了平衡CPU，内存，I/O设备这三者的速度差异，计算机体系结构、操作系统、编译程序都做出了贡献，主要体现为：
CPU 增加了缓存，以均衡与内存的速度差异；
操作系统增加了进程、线程，以分时复用 CPU，进而均衡 CPU 与 I/O 设备的速度差异；
编译程序优化指令执行次序，使得缓存能够得到更加合理地利用。

现在我们几乎所有的程序都默默地享受着这些成果，但是天下没有免费的午餐，并发程
序很多诡异问题的根源也在这里。

归结为：可见性、原子性和有序性问题；

源头之一：缓存导致的可见性问题。
解决办法：
1，volatile关键字，volatile 关键字并不是 Java 语言的特产，古老的 C 语言里也有，它最原始的意义就是禁用 CPU 缓存。
2，Happens-Before 规则（很多网文也都爱按字面意思翻译成“先行发生”，那就南辕北辙了）。
Happens-Before 规则应该是 Java 内存模型里面最晦涩的内容了，和程序员相关的规则一共有如下六项，都是关于可见性的。
a. 程序的顺序性规则, 前面的操作 Happens-Before 于后续的任意操作。
b. volatile 变量规则, 对一个 volatile 变量的写操作， Happens-Before 于后续对这个 volatile 变量的读操作。
c. 传递性, 如果：A Happens-Before B，且 B Happens-Before C，那么 A Happens-Before C。
d. 管程中锁的规则: 对一个锁的解锁 Happens-Before 于后续对这个锁的加锁。
e. 线程 start() 规则: 是指主线程 A 启动子线程 B 后，子线程 B 能够看到主线程在启动子线程 B 前的操作。
f. 线程 join() 规则: 它是指主线程 A 等待子线程 B 完成（主线程 A 通过调用子线程B 的 join() 方法实现），当子线程 B 完成后（主线程 A 中 join() 方法返回），主线程能
够看到子线程的操作。当然所谓的“看到”，指的是对共享变量的操作。换句话说就是，如果在线程 A 中，调用线程 B 的 join() 并成功返回，那么线程 B 中的任意操作 Happens-Before
于该 join() 操作的返回。


源头之二：线程切换带来的原子性问题，一个或者多个操作在 CPU 执行的过程中不被中断的特性，称为“原子性”。
“原子性”的本质是什么？其实不是不可分割，不可分割只是外在表现，其本质是多个资源间有一致性的要求，操作的中间状态对外不可见。
例如，在 32 位的机器上写 long 型变量有中间状态（只写了 64 位中的 32 位），在银行转账的操作中也有中间状态（账户A 减少了 100，
账户 B 还没来得及发生变化）。所以解决原子性问题，是要保证中间状态对外不可见。

解决办法：“同一时刻只有一个线程执行”这个条件非常重要，我们称之为互斥。
synchronized 加锁保证互斥。


源头之三：编译优化带来的有序性问题。
Happens-Before 规则。


----
# count += 1，至少需要三条 CPU 指令。
指令 1：首先，需要把变量 count 从内存加载到 CPU 的寄存器；
指令 2：之后，在寄存器中执行 +1 操作；
指令 3：最后，将结果写入内存（缓存机制导致可能写入的是 CPU 缓存而不是内存）。


----
##在 32 位的机器上对 long 型变量进行加减操作存在并发隐患，到底是不是这
样呢？
##对long型变量进行加减操作需要注意什么？

long类型是64位，所以在32位的机器上，对long类型的数据操作通常需要多条指令组合出来，无法保证原子性。
可以用atomic系列。


----
有一个共享变量 abc，在一个线程里设置了 abc 的值 abc=3，你思考一下，有哪些办法可
以让其他线程能够看到abc==3？
1, 使用volatile修饰abc, 禁止CPU缓存，直接从内存获取和volatile写happen before volatile读。
2，synchronized 代码块中操作abc，解锁happen before 后续的加锁。
3，线程A操作共享变量abc，然后start方法启动B线程，B线程中可见abc操作。
4，线程A操作共享变量abc, B join A, 对于B线程可见。



#get方法加锁不是为了解决原子性问题，这个操作本身就是原子性的，是为了实现不能线程间addone方法的操作结果对get方法可见。
那么value变量加volitile也可以实现同样的效果，并发包的原子类就是靠它实现的。
get方法不用synchronized，value加volitile也能保证可见性，这是对的。但是如果真这么做了，原子性可能被打破。synchronized并
不保证线程不被中断。如果在写高低两个字节的中间写线程被中断，而读线程被调用执行，因为读没尝试加锁，所以可以读到写了一半的结果。


----
那如何避免死锁呢？要避免死锁就需要分析死锁发生的条件，有个叫 Coffman 的牛人早
就总结过了，只有以下这四个条件都发生时才会出现死锁：
互斥，共享资源 X 和 Y 只能被一个线程占用；
占有且等待，线程 T1 已经取得共享资源 X，在等待共享资源 Y 的时候，不释放共享资源X；
不可抢占，其他线程不能强行抢占线程 T1 占有的资源；
循环等待，线程 T1 等待线程 T2 占有的资源，线程 T2 等待线程 T1 占有的资源，就是循环等待。
反过来分析，也就是说只要我们破坏其中一个，就可以成功避免死锁的发生。

解决“活锁”的方案很简单，谦让时，尝试等待一个随机的时间就可以了。


----
String，Integer等基本类型，Java的实现里面都优化为不可变对象对象池，一但对他们进行赋值就会变成新的对象，所以如果用这些对象作为锁，
那么有会有锁失效的问题（因为对象是另外一个了，加锁会加在不同的对象上）。


----
Java 语言内置的 synchronized 配合 wait()、notify()、notifyAll() 这三个方法可以实现等待通知机制。


管程，对应的英文是 Monitor，很多 Java 领域的同学都喜欢将其翻译成“监视器”，这是
直译。操作系统领域一般都翻译成“管程”
在管程的发展史上，先后出现过三种不同的管程模型，分别是：Hasen 模型、Hoare 模
型和 MESA 模型。其中，现在广泛应用的是 MESA 模型，并且 Java 管程的实现参考的
也是 MESA 模型
在并发编程领域，有两大核心问题：一个是互斥，即同一时刻只允许一个线程访问共享
资源；另一个是同步，即线程之间如何通信、协作。这两大问题，管程都是能够解决
的。

Java 参考了 MESA 模型，语言内置的管程（synchronized）对 MESA 模型进行了精简。
MESA 模型中，条件变量可以有多个，Java 语言内置的管程里只有一个条件变量。具体
如下图所示。：管程-监视器-monitor-synchronized-java-示意图.PNG

synchronized问题：
synchronized 申请资源的时候，如果申请不到，线程直接进入阻塞状态了，而线程进入阻塞状态，啥都干不了，也释放不了线程已经占有的资源。

Java SDK 并发包通过 Lock 和 Condition 两个接口也可以用来实现管程并且支持多个条件变量，其中 Lock 用于解决互斥问题，Condition 用于解决同步问题。
Lock接口提供了破坏不可抢占条件的方法：
1，阻塞状态的线程能够响应中断信号，也就是说当我们给阻塞的线程发送中断信号的时候，能够唤醒它。
void lockInterruptibly() throws InterruptedException;
2，支持超时。如果线程在一段时间之内没有获取到锁，不是进入阻塞状态，而是返回一个错误。
boolean tryLock(long time, TimeUnit unit) throws InterruptedException;
3，非阻塞地获取锁。如果尝试获取锁失败，并不进入阻塞状态，而是直接返回，那这个线程也有机会释放曾经持有的锁。
boolean tryLock();


----
最佳线程数 =CPU 核数 * [ 1 +（I/O 耗时 / CPU 耗时）]
=cpu核数 * (cpu+io耗时)/cpu耗时


----
尾递归是指在方法返回时候自调用自己本身，且不包含表达式。编译器会把尾递归做优化，使递归方法无论调用多少次都只占用一个栈帧。Java没有尾递归优化。


推荐使用并发包，迫不得已时才使用低级的同步原语：低级的同步原语主要指的是 synchronized、Lock、Semaphore 等。


----
Semaphore 信号量：
信号量模型还是很简单的，可以简单概括为：一个计数器，一个等待队列，三个方法。
在信号量模型里，计数器和等待队列对外是透明的，所以只能通过信号量模型提供的三
个方法来访问它们，这三个方法分别是：init()、down() 和 up()。
如何快速实现一个限流器：


----
乐观锁如何避免ABA问题。


----
CountDownLatch 和 CyclicBarrier 是 Java 并发包提供的两个非常易用的线程同步工具
类，这两个工具类用法的区别在这里还是有必要再强调一下：CountDownLatch 主要用
来解决一个线程等待多个线程的场景，可以类比旅游团团长要等待所有的游客到齐才能
去下一个景点；而 CyclicBarrier 是一组线程之间互相等待，更像是几个驴友之间不离不
弃。除此之外 CountDownLatch 的计数器是不能循环利用的，也就是说一旦计数器减到
0，再有线程调用 await()，该线程会直接通过。但 CyclicBarrier 的计数器是可以循环利
用的，而且具备自动重置的功能，一旦计数器减到 0 会自动重置到你设置的初始值。除
此之外，CyclicBarrier 还可以设置回调函数


----
其实原子类性能高的秘密很简单，硬件支持而已。CPU 为了解决并发问题，提供了 CAS
指令（CAS，全称是 Compare And Swap，即“比较并交换”）。CAS 指令包含 3 个参
数：共享变量的内存地址 A、用于比较的值 B 和共享变量的新值 C；并且只有当内存中
地址 A 处的值等于 B 时，才能将内存中地址 A 处的值更新为新值 C。作为一条 CPU 指
令，CAS 指令本身是能够保证原子性的。


----
当需要批量提交异步任务的时候建议你使用 CompletionService。CompletionService 将
线程池 Executor 和阻塞队列 BlockingQueue 的功能融合在了一起，能够让批量异步任务
的管理更简单。除此之外，CompletionService 能够让异步任务的执行结果有序化，先执
行完的先进入阻塞队列，利用这个特性，你可以轻松实现后续处理的有序性，避免无谓
的等待，同时还可以快速实现诸如 Forking Cluster 这样的需求。
CompletionService 的实现类 ExecutorCompletionService，需要你自己创建线程池，虽
看上去有些啰嗦，但好处是你可以让多个 ExecutorCompletionService 的线程池隔离，这
种隔离性能避免几个特别耗时的任务拖垮整个应用的风险。


----
class Singleton {
private static volatile Singleton singleton;

	//构造方法私有化
	private Singleton() {}
	
	//获取实例（单例）
	public static Singleton getInstance() {
		//第一次检查
		if(singleton==null) {
			synchronize(Singleton.class) {
				//获取锁后二次检查
				12/30
				if(singleton==null) {
					singleton=new Singleton();
				}
			}
		}
		return singleton;
	}
}


----
优雅地终止线程,两阶段终止模式:
其中第一个阶段主要是线程 T1 向线程 T2发送终止指令，而第二阶段则是线程 T2响应终止指令。
阶段一：Java Thread 类提供的 interrupt() 方法，它可以将休眠状态的线程转换到 RUNNABLE 状态。
（Thread.currentThread().isInterrupted() ，需要注意的是，我们在捕获 Thread.sleep() 的中断异常
之后，通过Thread.currentThread().interrupt() 重新设置了线程的中断状态，因为 JVM 的异常处理会
清除线程的中断状态。通常设置自己的线程终止标志位而不用Thread.currentThread().isInterrupted()，
原因在于我们很可能在线程的 run() 方法中调用第三方类库提供的方法，而我们没有办法保证第三方类库
正确处理了线程的中断异常，例如第三方类库在捕获到 Thread.sleep() 方法抛出的中断异常后，没有重
新设置线程的中断状态，那么就会导致线程不能够正常终止。）
阶段二：响应终止指令。


----
异步转同步：
// 创建锁与条件变量
private final Lock lock = new ReentrantLock();
private final Condition done = lock.newCondition();
// 调用方通过该方法等待结果
Object get(int timeout) {
long start = System.nanoTime();
lock.lock();
try {
while (!isDone()) {
done.await(timeout);
long cur=System.nanoTime();
if (isDone() || cur-start > timeout) {
break;
}
}
} finally {
lock.unlock();
}
if (!isDone()) {
throw new TimeoutException();
}
return returnFromResponse();
}
// RPC结果是否已经返回
boolean isDone() {
return response != null;
}
// RPC结果返回时调用该方法
private void doReceived(Response res) {
lock.lock();
try {
response = res;
if (done != null) {
done.signal();
}
} finally {
lock.unlock();
}
}







--------------------------------------------------------------------------------------------

----
2. Hashtable、HashMap、TreeMap 有什么不同？（9）

Hashtable 是早期 Java 类库提供的一个哈希表实现，本身是同步的，不支持 null 键和值，由于同步导致的性能开销，所以已经很少被推荐使用。
HashMap 是应用更加广泛的哈希表实现，行为上大致上与 HashTable 一致，主要区别在于 HashMap 不是同步的，支持 null 键和值等。通常情况下，HashMap 进行 put 或者 get 操作，可以达到常数时间的性能，所以它是绝大部分利用键值对存取场景的首选。
TreeMap 则是基于红黑树的一种提供顺序访问的 Map，和 HashMap 不同，它的 get、put、remove 之类操作都是 O（log(n)）的时间复杂度，具体顺序可以由指定的 Comparator 来决定，或者根据键的自然顺序来判断。



----
4. Java 并发类库提供的线程池有哪几种？
   通常开发者都是利用 Executors 提供的通用线程池创建方法，去创建不同配置的线程池，主要区别在于不同的 ExecutorService 类型或者不同的初始参数。Executors 目前提供了 5 种不同的线程池创建配置。


----
Exception 和 Error 的区别：
Exception 和 Error 都是继承了 Throwable 类，在 Java 中只有 Throwable 类型的实例才可以被抛出（throw）或者捕获（catch），它是异常处理机制的基本组成类型。
Exception 和 Error 体现了 Java 平台设计者对不同异常情况的分类。Exception 是程序正常运行中，可以预料的意外情况，可能并且应该被捕获，进行相应处理。
Error 是指在正常情况下，不大可能出现的情况，绝大部分的 Error 都会导致程序（比如 JVM 自身）处于非正常的、不可恢复状态。既然是非正常情况，所以不便于也不需要捕获，常见的比如 OutOfMemoryError 之类，都是 Error 的子类。
Exception 又分为可检查（checked）异常和不检查（unchecked）异常，可检查异常在源代码里必须显式地进行捕获处理，这是编译期检查的一部分。前面我介绍的不可查的 Error，是 Throwable 不是 Exception。不检查异常就是所谓的运行时异常，类似 NullPointerException、ArrayIndexOutOfBoundsException之类，通常是可以编码避免的逻辑错误，具体根据需要来判断是否需要捕获，并不会在编译期强制要求。


----
谈谈 final、finally、 finalize 有什么不同？
final 可以用来修饰类、方法、变量，分别有不同的意义，final 修饰的 class 代表不可以继承扩展，final 的变量是不可以修改的，而 final 的方法也是不可以重写的（override）。finally 则是 Java 保证重点代码一定要被执行的一种机制。我们可以使用 try-finally 或者 try-catch-finally 来进行类似关闭 JDBC 连接、保证 unlock 锁等动作。finalize 是基础类 java.lang.Object 的一个方法，它的设计目的是保证对象在被垃圾收集前完成特定资源的回收。finalize 机制现在已经不推荐使用，并且在 JDK 9 开始被标记为 deprecated。

----
强引用、软引用、弱引用、幻象引用有什么区别？
不同的引用类型，主要体现的是对象不同的可达性（reachable）状态和对垃圾收集的影响。所谓强引用（“Strong” Reference），就是我们最常见的普通对象引用，只要还有强引用指向一个对象，就能表明对象还“活着”，垃圾收集器不会碰这种对象。对于一个普通的对象，如果没有其他的引用关系，只要超过了引用的作用域或者显式地将相应（强）引用赋值为 null，就是可以被垃圾收集的了，当然具体回收时机还是要看垃圾收集策略。软引用（SoftReference），是一种相对强引用弱化一些的引用，可以让对象豁免一些垃圾收集，只有当 JVM 认为内存不足时，才会去试图回收软引用指向的对象。JVM 会确保在抛出 OutOfMemoryError 之前，清理软引用指向的对象。软引用通常用来实现内存敏感的缓存，如果还有空闲内存，就可以暂时保留缓存，当内存不足时清理掉，这样就保证了使用缓存的同时，不会耗尽内存。弱引用（WeakReference）并不能使对象豁免垃圾收集，仅仅是提供一种访问在弱引用状态下对象的途径。这就可以用来构建一种没有特定约束的关系，比如，维护一种非强制性的映射关系，如果试图获取时对象还在，就使用它，否则重现实例化。它同样是很多缓存实现的选择。对于幻象引用，有时候也翻译成虚引用，你不能通过它访问对象。幻象引用仅仅是提供了一种确保对象被 finalize 以后，做某些事情的机制，比如，通常用来做所谓的 Post-Mortem 清理机制，我在专栏上一讲中介绍的 Java 平台自身 Cleaner 机制等，也有人利用幻象引用监控对象的创建和销毁。


----
Java 的字符串，String、StringBuffer、StringBuilder 有什么区别？
String 是 Java 语言非常基础和重要的类，提供了构造和管理字符串的各种基本逻辑。它是典型的 Immutable 类，被声明成为 final class，所有属性也都是 final 的。也由于它的不可变性，类似拼接、裁剪字符串等动作，都会产生新的 String 对象。由于字符串操作的普遍性，所以相关操作的效率往往对应用性能有明显影响。StringBuffer 是为解决上面提到拼接产生太多中间对象的问题而提供的一个类，我们可以用 append 或者 add 方法，把字符串添加到已有序列的末尾或者指定位置。StringBuffer 本质是一个线程安全的可修改字符序列，它保证了线程安全，也随之带来了额外的性能开销，所以除非有线程安全的需要，不然还是推荐使用它的后继者，也就是 StringBuilder。StringBuilder 是 Java 1.5 中新增的，在能力上和 StringBuffer 没有本质区别，但是它去掉了线程安全的部分，有效减小了开销，是绝大部分情况下进行字符串拼接的首选。


----
动态代理是基于什么原理？
反射机制是 Java 语言提供的一种基础功能，赋予程序在运行时自省（introspect，官方用语）的能力。通过反射我们可以直接操作类或者对象，比如获取某个对象的类定义，获取类声明的属性和方法，调用方法或者构造对象，甚至可以运行时修改类定义。动态代理是一种方便运行时动态构建代理、动态处理代理方法调用的机制，很多场景都是利用类似机制做到的，比如用来包装 RPC 调用、面向切面的编程（AOP）。实现动态代理的方式很多，比如 JDK 自身提供的动态代理，就是主要利用了上面提到的反射机制。还有其他的实现方式，比如利用传说中更高性能的字节码操作机制，类似 ASM、cglib（基于 ASM）、Javassist 等。


----
int和Integer有什么区别？
int 是我们常说的整形数字，是 Java 的 8 个原始数据类型（Primitive Types，boolean、byte 、short、char、int、float、double、long）之一。Java 语言虽然号称一切都是对象，但原始数据类型是例外。Integer 是 int 对应的包装类，它有一个 int 类型的字段存储数据，并且提供了基本操作，比如数学运算、int 和字符串之间转换等。在 Java 5 中，引入了自动装箱和自动拆箱功能（boxing/unboxing），Java 可以根据上下文，自动进行转换，极大地简化了相关编程。关于 Integer 的值缓存，这涉及 Java 5 中另一个改进。构建 Integer 对象的传统方式是直接调用构造器，直接 new 一个对象。但是根据实践，我们发现大部分数据操作都是集中在有限的、较小的数值范围，因而，在 Java 5 中新增了静态工厂方法 valueOf，在调用它的时候会利用一个缓存机制，带来了明显的性能改进。按照 Javadoc，这个值默认缓存是 -128 到 127 之间。


----
对比Vector、ArrayList、LinkedList有何区别？
这三者都是实现集合框架中的 List，也就是所谓的有序集合，因此具体功能也比较近似，比如都提供按照位置进行定位、添加或者删除的操作，都提供迭代器以遍历其内容等。但因为具体的设计区别，在行为、性能、线程安全等方面，表现又有很大不同。Vector 是 Java 早期提供的线程安全的动态数组，如果不需要线程安全，并不建议选择，毕竟同步是有额外开销的。Vector 内部是使用对象数组来保存数据，可以根据需要自动的增加容量，当数组已满时，会创建新的数组，并拷贝原有数组数据。ArrayList 是应用更加广泛的动态数组实现，它本身不是线程安全的，所以性能要好很多。与 Vector 近似，ArrayList 也是可以根据需要调整容量，不过两者的调整逻辑有所区别，Vector 在扩容时会提高 1 倍，而 ArrayList 则是增加 50%。LinkedList 顾名思义是 Java 提供的双向链表，所以它不需要像上面两种那样调整容量，它也不是线程安全的。


----
对比Hashtable、HashMap、TreeMap有什么不同？
Hashtable、HashMap、TreeMap 都是最常见的一些 Map 实现，是以键值对的形式存储和操作数据的容器类型。Hashtable 是早期 Java 类库提供的一个哈希表实现，本身是同步的，不支持 null 键和值，由于同步导致的性能开销，所以已经很少被推荐使用。HashMap 是应用更加广泛的哈希表实现，行为上大致上与 HashTable 一致，主要区别在于 HashMap 不是同步的，支持 null 键和值等。通常情况下，HashMap 进行 put 或者 get 操作，可以达到常数时间的性能，所以它是绝大部分利用键值对存取场景的首选，比如，实现一个用户 ID 和用户信息对应的运行时存储结构。TreeMap 则是基于红黑树的一种提供顺序访问的 Map，和 HashMap 不同，它的 get、put、remove 之类操作都是 O（log(n)）的时间复杂度，具体顺序可以由指定的 Comparator 来决定，或者根据键的自然顺序来判断。


----
Java提供了哪些IO方式？ NIO如何实现多路复用？
Java IO 方式有很多种，基于不同的 IO 抽象模型和交互方式，可以进行简单区分。首先，传统的 java.io 包，它基于流模型实现，提供了我们最熟知的一些 IO 功能，比如 File 抽象、输入输出流等。交互方式是同步、阻塞的方式，也就是说，在读取输入流或者写入输出流时，在读、写动作完成之前，线程会一直阻塞在那里，它们之间的调用是可靠的线性顺序。
java.io 包的好处是代码比较简单、直观，缺点则是 IO 效率和扩展性存在局限性，容易成为应用性能的瓶颈。很多时候，人们也把 java.net 下面提供的部分网络 API，比如 Socket、ServerSocket、HttpURLConnection 也归类到同步阻塞 IO 类库，因为网络通信同样是 IO 行为。
第二，在 Java 1.4 中引入了 NIO 框架（java.nio 包），提供了 Channel、Selector、Buffer 等新的抽象，可以构建多路复用的、同步非阻塞 IO 程序，同时提供了更接近操作系统底层的高性能数据操作方式。
第三，在 Java 7 中，NIO 有了进一步的改进，也就是 NIO 2，引入了异步非阻塞 IO 方式，也有很多人叫它 AIO（Asynchronous IO）。异步 IO 操作基于事件和回调机制，可以简单理解为，应用操作直接返回，而不会阻塞在那里，当后台处理完成，操作系统会通知相应线程进行后续工作。


----
Java有几种文件拷贝方式？哪一种最高效？
Java 有多种比较典型的文件拷贝实现方式，比如：利用 java.io 类库，直接为源文件构建一个 FileInputStream 读取，然后再为目标文件构建一个 FileOutputStream，完成写入工作。

public static void copyFileByStream(File source, File dest) throws
IOException {
try (InputStream is = new FileInputStream(source);
OutputStream os = new FileOutputStream(dest);){
byte[] buffer = new byte[1024];
int length;
while ((length = is.read(buffer)) > 0) {
os.write(buffer, 0, length);
}
}
}

或者，利用 java.nio 类库提供的 transferTo 或 transferFrom 方法实现。

public static void copyFileByChannel(File source, File dest) throws
IOException {
try (FileChannel sourceChannel = new FileInputStream(source)
.getChannel();
FileChannel targetChannel = new FileOutputStream(dest).getChannel
();){
for (long count = sourceChannel.size() ;count>0 ;) {
long transferred = sourceChannel.transferTo(
sourceChannel.position(), count, targetChannel);            
sourceChannel.position(sourceChannel.position() + transferred);
count -= transferred;
}
}
}

当然，Java 标准类库本身已经提供了几种 Files.copy 的实现。对于 Copy 的效率，这个其实与操作系统和配置等情况相关，总体上来说，NIO transferTo/From 的方式可能更快，因为它更能利用现代操作系统底层机制，避免不必要拷贝和上下文切换。


----
谈谈接口和抽象类有什么区别？
接口和抽象类是 Java 面向对象设计的两个基础机制。接口是对行为的抽象，它是抽象方法的集合，利用接口可以达到 API 定义和实现分离的目的。接口，不能实例化；不能包含任何非常量成员，任何 field 都是隐含着 public static final 的意义；同时，没有非静态方法实现，也就是说要么是抽象方法，要么是静态方法。Java 标准类库中，定义了非常多的接口，比如 java.util.List。抽象类是不能实例化的类，用 abstract 关键字修饰 class，其目的主要是代码重用。除了不能实例化，形式上和一般的 Java 类并没有太大区别，可以有一个或者多个抽象方法，也可以没有抽象方法。抽象类大多用于抽取相关 Java 类的共用方法实现或者是共同成员变量，然后通过继承的方式达到代码复用的目的。Java 标准库中，比如 collection 框架，很多通用部分就被抽取成为抽象类，例如 java.util.AbstractList。Java 类实现 interface 使用 implements 关键词，继承 abstract class 则是使用 extends 关键词，我们可以参考 Java 标准库中的 ArrayList。


----
synchronized和ReentrantLock有什么区别呢？
synchronized 是 Java 内建的同步机制，所以也有人称其为 Intrinsic Locking，它提供了互斥的语义和可见性，当一个线程已经获取当前锁时，其他试图获取的线程只能等待或者阻塞在那里。在 Java 5 以前，synchronized 是仅有的同步手段，在代码中， synchronized 可以用来修饰方法，也可以使用在特定的代码块儿上，本质上 synchronized 方法等同于把方法全部语句用 synchronized 块包起来。ReentrantLock，通常翻译为再入锁，是 Java 5 提供的锁实现，它的语义和 synchronized 基本相同。再入锁通过代码直接调用 lock() 方法获取，代码书写也更加灵活。与此同时，ReentrantLock 提供了很多实用的方法，能够实现很多 synchronized 无法做到的细节控制，比如可以控制 fairness，也就是公平性，或者利用定义条件等。但是，编码中也需要注意，必须要明确调用 unlock() 方法释放，不然就会一直持有该锁。synchronized 和 ReentrantLock 的性能不能一概而论，早期版本 synchronized 在很多场景下性能相差较大，在后续版本进行了较多改进，在低竞争场景中表现可能优于 ReentrantLock。


----
synchronized底层如何实现？什么是锁的升级、降级？
在回答这个问题前，先简单复习一下上一讲的知识点。synchronized 代码块是由一对儿 monitorenter/monitorexit 指令实现的，Monitor 对象是同步的基本实现单元。在 Java 6 之前，Monitor 的实现完全是依靠操作系统内部的互斥锁，因为需要进行用户态到内核态的切换，所以同步操作是一个无差别的重量级操作。现代的（Oracle）JDK 中，JVM 对此进行了大刀阔斧地改进，提供了三种不同的 Monitor 实现，也就是常说的三种不同的锁：偏斜锁（Biased Locking）、轻量级锁和重量级锁，大大改进了其性能。所谓锁的升级、降级，就是 JVM 优化 synchronized 运行的机制，当 JVM 检测到不同的竞争状况时，会自动切换到适合的锁实现，这种切换就是锁的升级、降级。当没有竞争出现时，默认会使用偏斜锁。JVM 会利用 CAS 操作（compare and swap），在对象头上的 Mark Word 部分设置线程 ID，以表示这个对象偏向于当前线程，所以并不涉及真正的互斥锁。这样做的假设是基于在很多应用场景中，大部分对象生命周期中最多会被一个线程锁定，使用偏斜锁可以降低无竞争开销。如果有另外的线程试图锁定某个已经被偏斜过的对象，JVM 就需要撤销（revoke）偏斜锁，并切换到轻量级锁实现。轻量级锁依赖 CAS 操作 Mark Word 来试图获取锁，如果重试成功，就使用普通的轻量级锁；否则，进一步升级为重量级锁。我注意到有的观点认为 Java 不会进行锁降级。实际上据我所知，锁降级确实是会发生的，当 JVM 进入安全点（SafePoint）的时候，会检查是否有闲置的 Monitor，然后试图进行降级。


----
一个线程两次调用start()方法会出现什么情况？
Java 的线程是不允许启动两次的，第二次调用必然会抛出 IllegalThreadStateException，这是一种运行时异常，多次调用 start 被认为是编程错误。关于线程生命周期的不同状态，在 Java 5 以后，线程状态被明确定义在其公共内部枚举类型 java.lang.Thread.State 中，分别是：新建（NEW），表示线程被创建出来还没真正启动的状态，可以认为它是个 Java 内部状态。就绪（RUNNABLE），表示该线程已经在 JVM 中执行，当然由于执行需要计算资源，它可能是正在运行，也可能还在等待系统分配给它 CPU 片段，在就绪队列里面排队。在其他一些分析中，会额外区分一种状态 RUNNING，但是从 Java API 的角度，并不能表示出来。阻塞（BLOCKED），这个状态和我们前面两讲介绍的同步非常相关，阻塞表示线程在等待 Monitor lock。比如，线程试图通过 synchronized 去获取某个锁，但是其他线程已经独占了，那么当前线程就会处于阻塞状态。等待（WAITING），表示正在等待其他线程采取某些操作。一个常见的场景是类似生产者消费者模式，发现任务条件尚未满足，就让当前消费者线程等待（wait），另外的生产者线程去准备任务数据，然后通过类似 notify 等动作，通知消费线程可以继续工作了。Thread.join() 也会令线程进入等待状态。计时等待（TIMED_WAIT），其进入条件和等待状态类似，但是调用的是存在超时条件的方法，比如 wait 或 join 等方法的指定超时版本，如下面示例：public final native void wait(long timeout) throws InterruptedException;终止（TERMINATED），不管是意外退出还是正常执行结束，线程已经完成使命，终止运行，也有人把这个状态叫作死亡。在第二次调用 start() 方法的时候，线程可能处于终止或者其他（非 NEW）状态，但是不论如何，都是不可以再次启动的。


----
Java并发包提供了哪些并发工具类？
我们通常所说的并发包也就是 java.util.concurrent 及其子包，集中了 Java 并发的各种基础工具类，具体主要包括几个方面：提供了比 synchronized 更加高级的各种同步结构，包括 CountDownLatch、CyclicBarrier、Semaphore 等，可以实现更加丰富的多线程操作，比如利用 Semaphore 作为资源控制器，限制同时进行工作的线程数量。各种线程安全的容器，比如最常见的 ConcurrentHashMap、有序的 ConcurrentSkipListMap，或者通过类似快照机制，实现线程安全的动态数组 CopyOnWriteArrayList 等。各种并发队列实现，如各种 BlockingQueue 实现，比较典型的 ArrayBlockingQueue、 SynchronousQueue 或针对特定场景的 PriorityBlockingQueue 等。强大的 Executor 框架，可以创建各种不同类型的线程池，调度任务运行等，绝大部分情况下，不再需要自己从头实现线程池和任务调度器。


----
并发包中的ConcurrentLinkedQueue和LinkedBlockingQueue有什么区别？
有时候我们把并发包下面的所有容器都习惯叫作并发容器，但是严格来讲，类似 ConcurrentLinkedQueue 这种“Concurrent*”容器，才是真正代表并发。关于问题中它们的区别：Concurrent 类型基于 lock-free，在常见的多线程访问场景，一般可以提供较高吞吐量。而 LinkedBlockingQueue 内部则是基于锁，并提供了 BlockingQueue 的等待性方法。不知道你有没有注意到，java.util.concurrent 包提供的容器（Queue、List、Set）、Map，从命名上可以大概区分为 Concurrent*、CopyOnWrite和 Blocking等三类，同样是线程安全容器，可以简单认为：
Concurrent 类型没有类似 CopyOnWrite 之类容器相对较重的修改开销。
但是，凡事都是有代价的，Concurrent往往提供了较低的遍历一致性。你可以这样理解所谓的弱一致性，例如，当利用迭代器遍历时，如果容器发生修改，迭代器仍然可以继续进行遍历。
与弱一致性对应的，就是我介绍过的同步容器常见的行为“fail-fast”，也就是检测到容器在遍历过程中发生了修改，则抛出 ConcurrentModificationException，不再继续遍历。
弱一致性的另外一个体现是，size 等操作准确性是有限的，未必是 100% 准确。
与此同时，读取的性能具有一定的不确定性。


----
Java并发类库提供的线程池有哪几种？ 分别有什么特点？
通常开发者都是利用 Executors 提供的通用线程池创建方法，去创建不同配置的线程池，主要区别在于不同的 ExecutorService 类型或者不同的初始参数。
Executors 目前提供了 5 种不同的线程池创建配置：
* newCachedThreadPool()，它是一种用来处理大量短时间工作任务的线程池，具有几个鲜明特点：它会试图缓存线程并重用，当无缓存线程可用时，就会创建新的工作线程；如果线程闲置的时间超过 60 秒，则被终止并移出缓存；长时间闲置时，这种线程池，不会消耗什么资源。其内部使用 SynchronousQueue 作为工作队列。
* newFixedThreadPool(int nThreads)，重用指定数目（nThreads）的线程，其背后使用的是无界的工作队列，任何时候最多有 nThreads 个工作线程是活动的。这意味着，如果任务数量超过了活动队列数目，将在工作队列中等待空闲线程出现；如果有工作线程退出，将会有新的工作线程被创建，以补足指定的数目 nThreads。
* newSingleThreadExecutor()，它的特点在于工作线程数目被限制为 1，操作一个无界的工作队列，所以它保证了所有任务的都是被顺序执行，最多会有一个任务处于活动状态，并且不允许使用者改动线程池实例，因此可以避免其改变线程数目。
* newSingleThreadScheduledExecutor() 和 newScheduledThreadPool(int corePoolSize)，创建的是个 ScheduledExecutorService，可以进行定时或周期性的工作调度，区别在于单一工作线程还是多个工作线程。
* newWorkStealingPool(int parallelism)，这是一个经常被人忽略的线程池，Java 8 才加入这个创建方法，其内部会构建ForkJoinPool，利用Work-Stealing算法，并行地处理任务，不保证处理顺序。


----
请介绍类加载过程，什么是双亲委派模型？
一般来说，我们把 Java 的类加载过程分为三个主要步骤：加载、链接、初始化，具体行为在Java 虚拟机规范里有非常详细的定义。首先是加载阶段（Loading），它是 Java 将字节码数据从不同的数据源读取到 JVM 中，并映射为 JVM 认可的数据结构（Class 对象），这里的数据源可能是各种各样的形态，如 jar 文件、class 文件，甚至是网络数据源等；如果输入数据不是 ClassFile 的结构，则会抛出 ClassFormatError。加载阶段是用户参与的阶段，我们可以自定义类加载器，去实现自己的类加载过程。第二阶段是链接（Linking），这是核心的步骤，简单说是把原始的类定义信息平滑地转化入 JVM 运行的过程中。这里可进一步细分为三个步骤：
* 验证（Verification），这是虚拟机安全的重要保障，JVM 需要核验字节信息是符合 Java 虚拟机规范的，否则就被认为是 VerifyError，这样就防止了恶意信息或者不合规的信息危害 JVM 的运行，验证阶段有可能触发更多 class 的加载。
* 准备（Preparation），创建类或接口中的静态变量，并初始化静态变量的初始值。但这里的“初始化”和下面的显式初始化阶段是有区别的，侧重点在于分配所需要的内存空间，不会去执行更进一步的 JVM 指令。
* 解析（Resolution），在这一步会将常量池中的符号引用（symbolic reference）替换为直接引用。在Java 虚拟机规范中，详细介绍了类、接口、方法和字段等各个方面的解析。
  *最后是初始化阶段（initialization），这一步真正去执行类初始化的代码逻辑，包括静态字段赋值的动作，以及执行类定义中的静态初始化块内的逻辑，编译器在编译阶段就会把这部分逻辑整理好，父类型的初始化逻辑优先于当前类型的逻辑。再来谈谈双亲委派模型，简单说就是当类加载器（Class-Loader）试图加载某个类型的时候，除非父加载器找不到相应类型，否则尽量将这个任务代理给当前加载器的父加载器去做。使用委派模型的目的是避免重复加载 Java 类型。


----
有哪些方法可以在运行时动态生成一个Java类？
我们可以从常见的 Java 类来源分析，通常的开发过程是，开发者编写 Java 代码，调用 javac 编译成 class 文件，然后通过类加载机制载入 JVM，就成为应用运行时可以使用的 Java 类了。从上面过程得到启发，其中一个直接的方式是从源码入手，可以利用 Java 程序生成一段源码，然后保存到文件等，下面就只需要解决编译问题了。
* 有一种笨办法，直接用 ProcessBuilder 之类启动 javac 进程，并指定上面生成的文件作为输入，进行编译。最后，再利用类加载器，在运行时加载即可。前面的方法，本质上还是在当前程序进程之外编译的，那么还有没有不这么 low 的办法呢？
* 你可以考虑使用 Java Compiler API，这是 JDK 提供的标准 API，里面提供了与 javac 对等的编译器功能，具体请参考java.compiler相关文档。
* 进一步思考，我们一直围绕 Java 源码编译成为 JVM 可以理解的字节码，换句话说，只要是符合 JVM 规范的字节码，不管它是如何生成的，是不是都可以被 JVM 加载呢？我们能不能直接生成相应的字节码，然后交给类加载器去加载呢？
* 当然也可以，不过直接去写字节码难度太大，通常我们可以利用 Java 字节码操纵工具和类库来实现，比如在专栏第 6 讲中提到的ASM、Javassist、cglib 等。


----
谈谈JVM内存区域的划分，哪些区域可能发生OutOfMemoryError?
通常可以把 JVM 内存区域分为下面几个方面，其中，有的区域是以线程为单位，而有的区域则是整个 JVM 进程唯一的。
* 首先，程序计数器（PC，Program Counter Register）。在 JVM 规范中，每个线程都有它自己的程序计数器，并且任何时间一个线程都只有一个方法在执行，也就是所谓的当前方法。程序计数器会存储当前线程正在执行的 Java 方法的 JVM 指令地址；或者，如果是在执行本地方法，则是未指定值（undefined）。
* 第二，Java 虚拟机栈（Java Virtual Machine Stack），早期也叫 Java 栈。每个线程在创建时都会创建一个虚拟机栈，其内部保存一个个的栈帧（Stack Frame），对应着一次次的 Java 方法调用。前面谈程序计数器时，提到了当前方法；同理，在一个时间点，对应的只会有一个活动的栈帧，通常叫作当前帧，方法所在的类叫作当前类。如果在该方法中调用了其他方法，对应的新的栈帧会被创建出来，成为新的当前帧，一直到它返回结果或者执行结束。JVM 直接对 Java 栈的操作只有两个，就是对栈帧的压栈和出栈。栈帧中存储着局部变量表、操作数（operand）栈、动态链接、方法正常退出或者异常退出的定义等。
* 第三，堆（Heap），它是 Java 内存管理的核心区域，用来放置 Java 对象实例，几乎所有创建的 Java 对象实例都是被直接分配在堆上。堆被所有的线程共享，在虚拟机启动时，我们指定的“Xmx”之类参数就是用来指定最大堆空间等指标。理所当然，堆也是垃圾收集器重点照顾的区域，所以堆内空间还会被不同的垃圾收集器进行进一步的细分，最有名的就是新生代、老年代的划分。
* 第四，方法区（Method Area）。这也是所有线程共享的一块内存区域，用于存储所谓的元（Meta）数据，例如类结构信息，以及对应的运行时常量池、字段、方法代码等。由于早期的 Hotspot JVM 实现，很多人习惯于将方法区称为永久代（Permanent Generation）。Oracle JDK 8 中将永久代移除，同时增加了元数据区（Metaspace）。
* 第五，运行时常量池（Run-Time Constant Pool），这是方法区的一部分。如果仔细分析过反编译的类文件结构，你能看到版本号、字段、方法、超类、接口等各种信息，还有一项信息就是常量池。Java 的常量池可以存放各种常量信息，不管是编译期生成的各种字面量，还是需要在运行时决定的符号引用，所以它比一般语言的符号表存储的信息更加宽泛。
* 第六，本地方法栈（Native Method Stack）。它和 Java 虚拟机栈是非常相似的，支持对本地方法的调用，也是每个线程都会创建一个。在 Oracle Hotspot JVM 中，本地方法栈和 Java 虚拟机栈是在同一块儿区域，这完全取决于技术实现的决定，并未在规范中强制。



----
Java常见的垃圾收集器有哪些？
实际上，垃圾收集器（GC，Garbage Collector）是和具体 JVM 实现紧密相关的，不同厂商（IBM、Oracle），不同版本的 JVM，提供的选择也不同。接下来，我来谈谈最主流的 Oracle JDK。
Serial GC，它是最古老的垃圾收集器，“Serial”体现在其收集工作是单线程的，并且在进行垃圾收集过程中，会进入臭名昭著的“Stop-The-World”状态。当然，其单线程设计也意味着精简的 GC 实现，无需维护复杂的数据结构，初始化也简单，所以一直是 Client 模式下 JVM 的默认选项。从年代的角度，通常将其老年代实现单独称作 Serial Old，它采用了标记 - 整理（Mark-Compact）算法，区别于新生代的复制算法。Serial GC 的对应 JVM 参数是：-XX:+UseSerialGC

ParNew GC，很明显是个新生代 GC 实现，它实际是 Serial GC 的多线程版本，最常见的应用场景是配合老年代的 CMS GC 工作，下面是对应参数-XX:+UseConcMarkSweepGC -XX:+UseParNewGC

CMS（Concurrent Mark Sweep） GC，基于标记 - 清除（Mark-Sweep）算法，设计目标是尽量减少停顿时间，这一点对于 Web 等反应时间敏感的应用非常重要，一直到今天，仍然有很多系统使用 CMS GC。但是，CMS 采用的标记 - 清除算法，存在着内存碎片化问题，所以难以避免在长时间运行等情况下发生 full GC，导致恶劣的停顿。另外，既然强调了并发（Concurrent），CMS 会占用更多 CPU 资源，并和用户线程争抢。

Parallel GC，在早期 JDK 8 等版本中，它是 server 模式 JVM 的默认 GC 选择，也被称作是吞吐量优先的 GC。它的算法和 Serial GC 比较相似，尽管实现要复杂的多，其特点是新生代和老年代 GC 都是并行进行的，在常见的服务器环境中更加高效。开启选项是：-XX:+UseParallelGC
另外，Parallel GC 引入了开发者友好的配置项，我们可以直接设置暂停时间或吞吐量等目标，JVM 会自动进行适应性调整，例如下面参数：-XX:MaxGCPauseMillis=value-XX:GCTimeRatio=N // GC时间和用户时间比例 = 1 / (N+1)

G1 GC 这是一种兼顾吞吐量和停顿时间的 GC 实现，是 Oracle JDK 9 以后的默认 GC 选项。G1 可以直观的设定停顿时间的目标，相比于 CMS GC，G1 未必能做到 CMS 在最好情况下的延时停顿，但是最差情况要好很多。G1 GC 仍然存在着年代的概念，但是其内存结构并不是简单的条带式划分，而是类似棋盘的一个个 region。Region 之间是复制算法，但整体上实际可看作是标记 - 整理（Mark-Compact）算法，可以有效地避免内存碎片，尤其是当 Java 堆非常大的时候，G1 的优势更加明显。G1 吞吐量和停顿表现都非常不错，并且仍然在不断地完善，与此同时 CMS 已经在 JDK 9 中被标记为废弃（deprecated），所以 G1 GC 值得你深入掌握。


----
谈谈你的GC调优思路?
谈到调优，这一定是针对特定场景、特定目的的事情， 对于 GC 调优来说，首先就需要清楚调优的目标是什么？从性能的角度看，通常关注三个方面，内存占用（footprint）、延时（latency）和吞吐量（throughput），大多数情况下调优会侧重于其中一个或者两个方面的目标，很少有情况可以兼顾三个不同的角度。当然，除了上面通常的三个方面，也可能需要考虑其他 GC 相关的场景，例如，OOM 也可能与不合理的 GC 相关参数有关；或者，应用启动速度方面的需求，GC 也会是个考虑的方面。
基本的调优思路可以总结为：
* 理解应用需求和问题，确定调优目标。假设，我们开发了一个应用服务，但发现偶尔会出现性能抖动，出现较长的服务停顿。评估用户可接受的响应时间和业务量，将目标简化为，希望 GC 暂停尽量控制在 200ms 以内，并且保证一定标准的吞吐量。
* 掌握 JVM 和 GC 的状态，定位具体的问题，确定真的有 GC 调优的必要。具体有很多方法，比如，通过 jstat 等工具查看 GC 等相关状态，可以开启 GC 日志，或者是利用操作系统提供的诊断工具等。例如，通过追踪 GC 日志，就可以查找是不是 GC 在特定时间发生了长时间的暂停，进而导致了应用响应不及时。
* 这里需要思考，选择的 GC 类型是否符合我们的应用特征，如果是，具体问题表现在哪里，是 Minor GC 过长，还是 Mixed GC 等出现异常停顿情况；如果不是，考虑切换到什么类型，如 CMS 和 G1 都是更侧重于低延迟的 GC 选项。
* 通过分析确定具体调整的参数或者软硬件配置。
* 验证是否达到调优目标，如果达到目标，即可以考虑结束调优；否则，重复完成分析、调整、验证这个过程。


----
Java内存模型中的happen-before是什么？
Happen-before 关系，是 Java 内存模型中保证多线程操作可见性的机制，也是对早期语言规范中含糊的可见性概念的一个精确定义。它的具体表现形式，包括但远不止是我们直觉中的 synchronized、volatile、lock 操作顺序等方面，例如：
* 线程内执行的每个操作，都保证 happen-before 后面的操作，这就保证了基本的程序顺序规则，这是开发者在书写程序时的基本约定。
* 对于 volatile 变量，对它的写操作，保证 happen-before 在随后对该变量的读取操作。
* 对于一个锁的解锁操作，保证 happen-before 加锁操作。
* 对象构建完成，保证 happen-before 于 finalizer 的开始动作。
* 甚至是类似线程内部操作的完成，保证 happen-before 其他 Thread.join() 的线程等。
* 这些 happen-before 关系是存在着传递性的，如果满足 a happen-before b 和 b happen-before c，那么 a happen-before c 也成立。
  前面我一直用 happen-before，而不是简单说前后，是因为它不仅仅是对执行时间的保证，也包括对内存读、写操作顺序的保证。仅仅是时钟顺序上的先后，并不能保证线程交互的可见性。


----
Java程序运行在Docker等容器环境有哪些新问题？
对于 Java 来说，Docker 毕竟是一个较新的环境，例如，其内存、CPU 等资源限制是通过 CGroup（Control Group）实现的，早期的 JDK 版本（8u131 之前）并不能识别这些限制，进而会导致一些基础问题：
* 如果未配置合适的 JVM 堆和元数据区、直接内存等参数，Java 就有可能试图使用超过容器限制的内存，最终被容器 OOM kill，或者自身发生 OOM。
* 错误判断了可获取的 CPU 资源，例如，Docker 限制了 CPU 的核数，JVM 就可能设置不合适的 GC 并行线程数等。

从应用打包、发布等角度出发，JDK 自身就比较大，生成的镜像就更为臃肿，当我们的镜像非常多的时候，镜像的存储等开销就比较明显了。如果考虑到微服务、Serverless 等新的架构和场景，Java 自身的大小、内存占用、启动速度，都存在一定局限性，因为 Java 早期的优化大多是针对长时间运行的大型服务器端应用。



----
后台服务出现明显“变慢”，谈谈你的诊断思路？
首先，需要对这个问题进行更加清晰的定义:服务是突然变慢还是长时间运行后观察到变慢？类似问题是否重复出现？
“慢”的定义是什么，我能够理解是系统对其他方面的请求的反应延时变长吗?
第二，理清问题的症状，这更便于定位具体的原因，有以下一些思路：
* 问题可能来自于 Java 服务自身，也可能仅仅是受系统里其他服务的影响。初始判断可以先确认是否出现了意外的程序错误，例如检查应用本身的错误日志。对于分布式系统，很多公司都会实现更加系统的日志、性能等监控系统。一些 Java 诊断工具也可以用于这个诊断，例如通过 JFR（Java Flight Recorder），监控应用是否大量出现了某种类型的异常。如果有，那么异常可能就是个突破点。如果没有，可以先检查系统级别的资源等情况，监控 CPU、内存等资源是否被其他进程大量占用，并且这种占用是否不符合系统正常运行状况。

* 监控 Java 服务自身，例如 GC 日志里面是否观察到 Full GC 等恶劣情况出现，或者是否 Minor GC 在变长等；利用 jstat 等工具，获取内存使用的统计信息也是个常用手段；利用 jstack 等工具检查是否出现死锁等。

* 如果还不能确定具体问题，对应用进行 Profiling 也是个办法，但因为它会对系统产生侵入性，如果不是非常必要，大多数情况下并不建议在生产系统进行。

* 定位了程序错误或者 JVM 配置的问题后，就可以采取相应的补救措施，然后验证是否解决，否则还需要重复上面部分过程。

----
JVM优化Java代码时都做了什么？
JVM 在对代码执行的优化可分为运行时（runtime）优化和即时编译器（JIT）优化。运行时优化主要是解释执行和动态编译通用的一些机制，比如说锁机制（如偏斜锁）、内存分配机制（如 TLAB）等。除此之外，还有一些专门用于优化解释执行效率的，比如说模版解释器、内联缓存（inline cache，用于优化虚方法调用的动态绑定）。

JVM 的即时编译器优化是指将热点代码以方法为单位转换成机器码，直接运行在底层硬件之上。它采用了多种优化方式，包括静态编译器可以使用的如方法内联、逃逸分析，也包括基于程序运行 profile 的投机性优化（speculative/optimistic optimization）。这个怎么理解呢？比如我有一条 instanceof 指令，在编译之前的执行过程中，测试对象的类一直是同一个，那么即时编译器可以假设编译之后的执行过程中还会是这一个类，并且根据这个类直接返回 instanceof 的结果。如果出现了其他类，那么就抛弃这段编译后的机器码，并且切换回解释执行。

当然，JVM 的优化方式仅仅作用在运行应用代码的时候。如果应用代码本身阻塞了，比如说并发时等待另一线程的结果，这就不在 JVM 的优化范畴啦。


----
谈谈MySQL支持的事务隔离级别，以及悲观锁和乐观锁的原理和应用场景？

所谓隔离级别（Isolation Level），就是在数据库事务中，为保证并发数据读写的正确性而提出的定义，它并不是 MySQL 专有的概念，而是源于ANSI/ISO制定的SQL-92标准。
每种关系型数据库都提供了各自特色的隔离级别实现，虽然在通常的定义中是以锁为实现单元，但实际的实现千差万别。以最常见的 MySQL InnoDB 引擎为例，它是基于 MVCC（Multi-Versioning Concurrency Control）和锁的复合实现，按照隔离程度从低到高，MySQL 事务隔离级别分为四个不同层次：
* 读未提交（Read uncommitted），就是一个事务能够看到其他事务尚未提交的修改，这是最低的隔离水平，允许脏读出现。
* 读已提交（Read committed），事务能够看到的数据都是其他事务已经提交的修改，也就是保证不会看到任何中间性状态，当然脏读也不会出现。读已提交仍然是比较低级别的隔离，并不保证再次读取时能够获取同样的数据，也就是允许其他事务并发修改数据，允许不可重复读和幻象读（Phantom Read）出现。
* 可重复读（Repeatable reads），保证同一个事务中多次读取的数据是一致的，这是 MySQL InnoDB 引擎的默认隔离级别，但是和一些其他数据库实现不同的是，可以简单认为 MySQL 在可重复读级别不会出现幻象读。
* 串行化（Serializable），并发事务之间是串行化的，通常意味着读取需要获取共享读锁，更新需要获取排他写锁，如果 SQL 使用 WHERE 语句，还会获取区间锁（MySQL 以 GAP 锁形式实现，可重复读级别中默认也会使用），这是最高的隔离级别。

至于悲观锁和乐观锁，也并不是 MySQL 或者数据库中独有的概念，而是并发编程的基本概念。主要区别在于，操作共享数据时，“悲观锁”即认为数据出现冲突的可能性更大，而“乐观锁”则是认为大部分情况不会出现冲突，进而决定是否采取排他性措施。

反映到 MySQL 数据库应用开发中，悲观锁一般就是利用类似 SELECT … FOR UPDATE 这样的语句，对数据加锁，避免其他事务意外修改数据。乐观锁则与 Java 并发包中的 AtomicFieldUpdater 类似，也是利用 CAS 机制，并不会对数据加锁，而是通过对比数据的时间戳或者版本号，来实现乐观锁需要的版本判断。

我认为前面提到的 MVCC，其本质就可以看作是种乐观锁机制，而排他性的读写锁、双阶段锁等则是悲观锁的实现。

有关它们的应用场景，你可以构建一下简化的火车余票查询和购票系统。同时查询的人可能很多，虽然具体座位票只能是卖给一个人，但余票可能很多，而且也并不能预测哪个查询者会购票，这个时候就更适合用乐观锁。


----
谈谈Spring Bean的生命周期和作用域？

Spring Bean 生命周期比较复杂，可以分为创建和销毁两个过程。

首先，创建 Bean 会经过一系列的步骤，主要包括：

#实例化 Bean 对象。
#设置 Bean 属性。
#如果我们通过各种 Aware 接口声明了依赖关系，则会注入 Bean 对容器基础设施层面的依赖。具体包括 BeanNameAware、BeanFactoryAware 和 ApplicationContextAware，分别会注入 Bean ID、Bean Factory 或者 ApplicationContext。
#调用 BeanPostProcessor 的前置初始化方法 postProcessBeforeInitialization。
#如果实现了 InitializingBean 接口，则会调用 afterPropertiesSet 方法。
#调用 Bean 自身定义的 init 方法。
#调用 BeanPostProcessor 的后置初始化方法 postProcessAfterInitialization。
#创建过程完毕。

第二，Spring Bean 的销毁过程会依次调用 DisposableBean 的 destroy 方法和 Bean 自身定制的 destroy 方法。

Spring Bean 有五个作用域，其中最基础的有下面两种：
Singleton，这是 Spring 的默认作用域，也就是为每个 IOC 容器创建唯一的一个 Bean 实例。
Prototype，针对每个 getBean 请求，容器都会单独创建一个 Bean 实例。

从 Bean 的特点来看，Prototype 适合有状态的 Bean，而 Singleton 则更适合无状态的情况。另外，使用 Prototype 作用域需要经过仔细思考，毕竟频繁创建和销毁 Bean 是有明显开销的。

如果是 Web 容器，则支持另外三种作用域：
* Request，为每个 HTTP 请求创建单独的 Bean 实例。
* Session，很显然 Bean 实例的作用域是 Session 范围。
* GlobalSession，用于 Portlet 容器，因为每个 Portlet 有单独的 Session，GlobalSession 提供一个全局性的 HTTP Session。



----
对比Java标准NIO类库，你知道Netty是如何实现更高性能的吗？
单独从性能角度，Netty 在基础的 NIO 等类库之上进行了很多改进，例如：
* 更加优雅的 Reactor 模式实现、灵活的线程模型、利用 EventLoop 等创新性的机制，可以非常高效地管理成百上千的 Channel。
* 充分利用了 Java 的 Zero-Copy 机制，并且从多种角度，“斤斤计较”般的降低内存分配和回收的开销。例如，使用池化的 Direct Buffer 等技术，在提高 IO 性能的同时，减少了对象的创建和销毁；利用反射等技术直接操纵 SelectionKey，使用数组而不是 Java 容器等。
* 使用更多本地代码。例如，直接利用 JNI 调用 Open SSL 等方式，获得比 Java 内建 SSL 引擎更好的性能。
* 在通信协议、序列化等其他角度的优化。

总的来说，Netty 并没有 Java 核心类库那些强烈的通用性、跨平台等各种负担，针对性能等特定目标以及 Linux 等特定环境，采取了一些极致的优化手段。



----
谈谈常用的分布式ID的设计方案？Snowflake是否受冬令时切换影响？
首先，我们需要明确通常的分布式 ID 定义，基本的要求包括：
* 全局唯一，区别于单点系统的唯一，全局是要求分布式系统内唯一。
* 有序性，通常都需要保证生成的 ID 是有序递增的。例如，在数据库存储等场景中，有序 ID 便于确定数据位置，往往更加高效。

目前业界的方案很多，典型方案包括：
* 基于数据库自增序列的实现。这种方式优缺点都非常明显，好处是简单易用，但是在扩展性和可靠性等方面存在局限性。
* 基于 Twitter 早期开源的Snowflake的实现，以及相关改动方案。这是目前应用相对比较广泛的一种方式，其结构定义你可以参考下面的示意图。

整体长度通常是 64 （1 + 41 + 10+ 12 = 64）位，适合使用 Java 语言中的 long 类型来存储。头部是 1 位的正负标识位。紧跟着的高位部分包含 41 位时间戳，通常使用 System.currentTimeMillis()。后面是 10 位的 WorkerID，标准定义是 5 位数据中心 + 5 位机器 ID，组成了机器编号，以区分不同的集群节点。最后的 12 位就是单位毫秒内可生成的序列号数目的理论极限。

Snowflake 的官方版本是基于 Scala 语言，Java 等其他语言的参考实现有很多，是一种非常简单实用的方式，具体位数的定义是可以根据分布式系统的真实场景进行修改的，并不一定要严格按照示意图中的设计。
* Redis、ZooKeeper、MongoDB 等中间件，也都有各种唯一 ID 解决方案。其中一些设计也可以算作是 Snowflake 方案的变种。例如，MongoDB 的ObjectId提供了一个 12 byte（96 位）的 ID 定义，其中 32 位用于记录以秒为单位的时间，机器 ID 则为 24 位，16 位用作进程 ID，24 位随机起始的计数序列。

* 国内的一些大厂开源了其自身的部分分布式 ID 实现，InfoQ 就曾经介绍过微信的seqsvr，它采取了相对复杂的两层架构，并根据社交应用的数据特点进行了针对性设计，具体请参考相关代码实现。另外，百度、美团等也都有开源或者分享了不同的分布式 ID 实现，都可以进行参考。

关于第二个问题，Snowflake 是否受冬令时切换影响？我认为没有影响，你可以从 Snowflake 的具体算法实现寻找答案。我们知道 Snowflake 算法的 Java 实现，大都是依赖于 System.currentTimeMillis()，这个数值代表什么呢？从 Javadoc 可以看出，它是返回当前时间和 1970 年 1 月 1 号 UTC 时间相差的毫秒数，这个数值与夏 / 冬令时并没有关系，所以并不受其影响。



--------------------------------------------------------------------------------------------
##Java业务开发常见错误100例


#ThreadLocal
线程池会重用固定的几个线程，一旦线程重用，那么很可能首次从 ThreadLocal 获取的值是之前其他用户的请求遗留的值。这时，ThreadLocal 中的用户信息就是其他用户的信息。
使用类似 ThreadLocal 工具来存放一些数据时，需要特别注意在代码运行完后，显式地去清空设置的数据。在代码的 finally 代码块中，显式清除 ThreadLocal 中的数据。

#使用了线程安全的并发工具，并不代表解决了所有线程安全问题JDK 1.5 后推出的 ConcurrentHashMap，是一个高性能的线程安全的哈希表容器。“线程安全”这四个字特别容易让人误解，因为 ConcurrentHashMap 只能保证提供的原子性读写操作是线程安全的。
针对这个场景，我们可以举一个形象的例子。ConcurrentHashMap 就像是一个大篮子，现在这个篮子里有 900 个桔子，我们期望把这个篮子装满 1000 个桔子，也就是再装 100 个桔子。有 10 个工人来干这件事儿，大家先后到岗后会计算还需要补多少个桔子进去，最后把桔子装入篮子。ConcurrentHashMap 这个篮子本身，可以确保多个工人在装东西进去时，不会相互影响干扰，但无法确保工人 A 看到还需要装 100 个桔子但是还未装的时候，工人 B 就看不到篮子中的桔子数量。更值得注意的是，你往这个篮子装 100 个桔子的操作不是原子性的，在别人看来可能会有一个瞬间篮子里有 964 个桔子，还需要补 36 个桔子。回到 ConcurrentHashMap，我们需要注意 ConcurrentHashMap 对外提供的方法或能力的限制：使用了 ConcurrentHashMap，不代表对它的多个操作之间的状态是一致的，是没有其他线程在操作它的，如果需要确保需要手动加锁。诸如 size、isEmpty 和 containsValue 等聚合方法，在并发情况下可能会反映 ConcurrentHashMap 的中间状态。因此在并发情况下，这些方法的返回值只能用作参考，而不能用于流程控制。显然，利用 size 方法计算差异值，是一个流程控制。诸如 putAll 这样的聚合方法也不能确保原子性，在 putAll 的过程中去获取数据可能会获取到部分数据。

computeIfAbsent 为什么如此高效呢？答案就在源码最核心的部分，也就是 Java 自带的 Unsafe 实现的 CAS。它在虚拟机层面确保了写入数据的原子性，比加锁的效率高得多.


#CopyOnWriteArrayList
在 Java 中，CopyOnWriteArrayList 虽然是一个线程安全的 ArrayList，但因为其实现方式是，每次修改数据时都会复制一份数据出来，所以有明显的适用场景，即读多写少或者说希望无锁读的场景。


#
使用线程池需要注意哪些点？
线程池的声明需要手动进行Java 中的 Executors 类定义了一些快捷的工具方法，来帮助我们快速创建线程池。《阿里巴巴 Java 开发手册》中提到，禁止使用这些方法来创建线程池，而应该手动 new ThreadPoolExecutor 来创建线程池。这一条规则的背后，是大量血淋淋的生产事故，最典型的就是 newFixedThreadPool 和 newCachedThreadPool，可能因为资源耗尽导致 OOM 问题。
首先，我们来看一下 newFixedThreadPool 为什么可能会出现 OOM 的问题。
翻看 newFixedThreadPool 方法的源码不难发现，线程池的工作队列直接 new 了一个 LinkedBlockingQueue，而默认构造方法的 LinkedBlockingQueue 是一个 Integer.MAX_VALUE 长度的队列，可以认为是无界的, 虽然使用 newFixedThreadPool 可以把工作线程控制在固定的数量上，但任务队列是无界的。如果任务较多并且执行较慢的话，队列可能会快速积压，撑爆内存导致 OOM。

改为使用 newCachedThreadPool 方法来获得线程池。程序运行不久后，同样看到了如下 OOM 异常
这次 OOM 的原因是无法创建线程，翻看 newCachedThreadPool 的源码可以看到，这种线程池的最大线程数是 Integer.MAX_VALUE，可以认为是没有上限的，而其工作队列 SynchronousQueue 是一个没有存储空间的阻塞队列。这意味着，只要有请求到来，就必须找到一条工作线程来处理，如果当前没有空闲的线程就再创建一条新的。由于我们的任务需要 1 小时才能执行完成，大量的任务进来后会创建大量的线程。我们知道线程是需要分配一定的内存空间作为线程栈的，比如 1MB，因此无限制创建线程必然会导致 OOM.

#
Java 8 的 parallel stream 功能，可以让我们很方便地并行处理集合中的元素，其背后是共享同一个 ForkJoinPool，默认并行度是 CPU 核数 -1。


#HTTP 调用需要注意哪些点?
进行 HTTP 调用本质上是通过 HTTP 协议进行一次网络请求。网络请求必然有超时的可能性，因此我们必须考虑到这三点：
首先，框架设置的默认超时是否合理；
其次，考虑到网络的不稳定，超时后的请求重试是一个不错的选择，但需要考虑服务端接口的幂等性设计是否允许我们重试；
最后，需要考虑框架是否会像浏览器那样限制并发连接数，以免在服务并发很大的情况下，HTTP 调用的并发数限制成为瓶颈。


##
@Transactional 生效原则:
1，除非特殊配置（比如使用 AspectJ 静态织入实现 AOP），否则只有定义在 public 方法上的 @Transactional 才能生效。原因是，Spring 默认通过动态代理的方式实现 AOP，对目标方法进行增强，private 方法无法代理到，Spring 自然也无法动态增强事务处理逻辑。
2，必须通过代理过的类从外部调用目标方法才能生效。
Spring 通过 AOP 技术对方法进行增强，要调用增强过的方法必然是调用代理后的对象。我们尝试修改下 UserService 的代码，注入一个 self，然后再通过 self 实例调用标记有 @Transactional 注解的 createUserPublic 方法。设置断点可以看到，self 是由 Spring 通过 CGLIB 方式增强过的类.
CGLIB 通过继承方式实现代理类，private 方法在子类不可见，自然也就无法进行事务增强；
this 指针代表对象自己，Spring 不可能注入 this，所以通过 this 访问方法必然不是代理。把 this 改为 self就可以了。

#事务即便生效也不一定能回滚
第一，只有异常传播出了标记了 @Transactional 注解的方法，事务才能回滚。
第二，默认情况下，出现 RuntimeException（非受检异常）或 Error 的时候，Spring 才会回滚事务。


##
数据库索引：索引并不是万能药
不是所有针对索引列的查询都能用上索引:
第一，索引只能匹配列前缀。like把百分号放到后面走前缀匹配才可以走索引
第二，条件涉及函数操作无法走索引。
第三，联合索引只能匹配左边的列。
原因也很简单，在联合索引的情况下，数据是按照索引第一列排序，第一列数据相同时才会按照第二列排序。也就是说，如果我们想使用联合索引中尽可能多的列，查询条件中的各个列必须是联合索引中从最左边开始连续的列。如果我们仅仅按照第二列搜索，肯定无法走索引。

##
浮点数用 equals 做判等，不一定对，如果我们希望只比较 BigDecimal 的 value，可以使用 compareTo 方法
System.out.println(new BigDecimal("1.0").compareTo(new BigDecimal("1"))==0);

小心数值溢出问题数值计算还有一个要小心的点是溢出，不管是 int 还是 long，所有的基本数值类型都有超出表达范围的可能性。
而且是默默的溢出，并没有任何异常。这类问题非常容易被忽略，改进方式有下面 2 种。方法一是，考虑使用 Math 类的 addExact、subtractExact 等 xxExact 方法进行数值运算，这些方法可以在数值溢出时主动抛出异常。
方法二是，使用大数类 BigInteger。BigDecimal 是处理浮点数的专家，而 BigInteger 则是对大数进行科学计算的专家。


###
分享了若干和 List 列表相关的错误案例，基本都是由“想当然”导致的。
第一，想当然认为，Arrays.asList 和 List.subList 得到的 List 是普通的、独立的 ArrayList，在使用时出现各种奇怪的问题。
Arrays.asList 得到的是 Arrays 的内部类 ArrayList，List.subList 得到的是 ArrayList 的内部类 SubList，不能把这两个内部类转换为 ArrayList 使用。

Arrays.asList 直接使用了原始数组，可以认为是共享“存储”，而且不支持增删元素；List.subList 直接引用了原始的 List，也可以认为是共享“存储”，而且对原始 List 直接进行结构性修改会导致 SubList 出现异常。

对 Arrays.asList 和 List.subList 容易忽略的是，新的 List 持有了原始数据的引用，可能会导致原始数据也无法 GC 的问题，最终导致 OOM。

第二，想当然认为，Arrays.asList 一定可以把所有数组转换为正确的 List。当传入基本类型数组的时候，List 的元素是数组本身，而不是数组中的元素。

第三，想当然认为，内存中任何集合的搜索都是很快的，结果在搜索超大 ArrayList 的时候遇到性能问题。我们考虑利用 HashMap 哈希表随机查找的时间复杂度为 O(1) 这个特性来优化性能，不过也要考虑 HashMap 存储空间上的代价，要平衡时间和空间。

第四，想当然认为，链表适合元素增删的场景，选用 LinkedList 作为数据结构。在真实场景中读写增删一般是平衡的，而且增删不可能只是对头尾对象进行操作，可能在 90% 的情况下都得不到性能增益，建议使用之前通过性能测试评估一下。


###
我首先总结了业务代码中 5 种最容易出现空指针异常的写法，以及相应的修复方式。针对判空，通过 Optional 配合 Stream 可以避免大多数冗长的 if-else 判空逻辑，实现一行代码优雅判空。另外，要定位和修复空指针异常，除了可以通过增加日志进行排查外，在生产上使用 Arthas 来查看方法的调用栈和入参会更快捷。

POJO 中字段的 null 定位，从服务端的角度往往很难分清楚，到底是客户端希望忽略这个字段还是有意传了 null，因此我们尝试用 Optional类来区分 null 的定位。同时，为避免把空值更新到数据库中，可以实现动态 SQL，只更新必要的字段。

最后，我分享了数据库字段使用 NULL 可能会带来的三个坑（包括 sum 函数、count 函数，以及 NULL 值条件），以及解决方式。


###
处理异常容易犯的几个错和最佳实践:
第一，注意捕获和处理异常的最佳实践。首先，不应该用 AOP 对所有方法进行统一异常处理，异常要么不捕获不处理，要么根据不同的业务逻辑、不同的异常类型进行精细化、针对性处理；其次，处理异常应该杜绝生吞，并确保异常栈信息得到保留；最后，如果需要重新抛出异常的话，请使用具有意义的异常类型和异常消息。
第二，务必小心 finally 代码块中资源回收逻辑，确保 finally 代码块不出现异常，内部把异常处理完毕，避免 finally 中的异常覆盖 try 中的异常；或者考虑使用 addSuppressed 方法把 finally 中的异常附加到 try 中的异常上，确保主异常信息不丢失。此外，使用实现了 AutoCloseable 接口的资源，务必使用 try-with-resources 模式来使用资源，确保资源可以正确释放，也同时确保异常可以正确处理。
第三，虽然在统一的地方定义收口所有的业务异常是一个不错的实践，但务必确保异常是每次 new 出来的，而不能使用一个预先定义的 static 字段存放异常，否则可能会引起栈信息的错乱。
第四，确保正确处理了线程池中任务的异常，如果任务通过 execute 提交，那么出现异常会导致线程退出，大量的异常会导致线程重复创建引起性能问题，我们应该尽可能确保任务不出异常，同时设置默认的未捕获异常处理程序来兜底；如果任务通过 submit 提交意味着我们关心任务的执行结果，应该通过拿到的 Future 调用其 get 方法来获得任务运行结果和可能出现的异常，否则异常可能就被生吞了。



###
关于在 finally 代码块中抛出异常的坑，如果在 finally 代码块中返回值，你觉得程序会以 try 或 catch 中返回值为准，还是以 finally 中的返回值为准呢？
肯定是以finally语句块为准。
原因：首先需要明白的是在编译生成的字节码中，每个方法都附带一个异常表。异常表中的每一个条目代表一个异常处理器，并且由 from 指针、to 指针、target 指针以及所捕获的异常类型构成。这些指针的值是字节码索引（bytecode index，bci），用以定位字节码。其中，from 指针和 to 指针标示了该异常处理器所监控的范围，例如 try 代码块所覆盖的范围。target 指针则指向异常处理器的起始位置，例如 catch 代码块的起始位置；
当程序触发异常时，Java 虚拟机会从上至下遍历异常表中的所有条目。当触发异常的字节码的索引值在某个异常表条目的监控范围内，Java 虚拟机会判断所抛出的异常和该条目想要捕获的异常是否匹配。如果匹配，Java 虚拟机会将控制流转移至该条目 target 指针指向的字节码。如果遍历完所有异常表条目，Java 虚拟机仍未匹配到异常处理器，那么它会弹出当前方法对应的 Java 栈帧，并且在调用者（caller）中重复上述操作。在最坏情况下，Java 虚拟机需要遍历当前线程 Java 栈上所有方法的异常表。所以异常操作是一个非常耗费性能的操作；
finally 代码块的原理是复制 finally 代码块的内容，分别放在 try-catch 代码块所有正常执行路径以及异常执行路径的出口中。所以不管是是正常还是异常执行，finally都是最后执行的，所以肯定是finally语句块中为准。

###
对于手动抛出的异常，不建议直接使用 Exception 或 RuntimeException，通常建议复用 JDK 中的一些标准异常，比如IllegalArgumentException、IllegalStateException、UnsupportedOperationException，你能说说它们的适用场景，并列出更多常用异常吗？
IllegalArgumentException：不合法的参数异常，比如说限制不能为空或者有指定的发小范围，调用方没有按照规定传递参数，就可以抛出这个异常；
IllegalStateException：如果有状态流转的概念在里面（比如状态机），状态只能从A->B->C,若状态直接从A->C,就可以抛出该异常；
UnsupportedOperationException：不支持该操作异常，比如非抽象父类中有个方法，子类必须实现，父类中的方法就可以抛出次异常。老师在集合坑中提到的Arrays.asList 返回的 List 并不是我们期望的 java.util.ArrayList，而是 Arrays 的内部类 ArrayList。ArrayList 内部类继承自 AbstractList 类，并没有覆写父类的 add 方法，而父类中 add 方法的实现，就是抛出 UnsupportedOperationException。


###
问题 2：循环遍历 List，调用 remove 方法删除元素，往往会遇到 ConcurrentModificationException，原因是什么，修复方式又是什么呢？答：原因是，remove 的时候会改变 modCount，通过迭代器遍历就会触发 ConcurrentModificationException。

要修复这个问题，有以下两种解决方案。第一种，通过 ArrayList 的迭代器 remove。迭代器的 remove 方法会维护一个 expectedModCount，使其与 ArrayList 的 modCount 保持一致.
第二种，直接使用 removeIf 方法，其内部使用了迭代器的 remove 方法.



###
Files.lines 方法进行流式处理，需要使用 try-with-resources 进行资源释放。那么，使用 Files 类中其他返回 Stream 包装对象的方法进行流式处理，比如 newDirectoryStream 方法返回 DirectoryStream，list、walk 和 find 方法返回 Stream，也同样有资源释放问题吗？

答：使用 Files 类中其他返回 Stream 包装对象的方法进行流式处理，也同样会有资源释放问题。因为，这些接口都需要使用 try-with-resources 模式来释放。正如文中所说，如果不显式释放，那么可能因为底层资源没有及时关闭造成资源泄露。

###
Java 的 File 类和 Files 类提供的文件复制、重命名、删除等操作，是原子性的吗？

答：Java 的 File 和 Files 类的文件复制、重命名、删除等操作，都不是原子性的。原因是，文件类操作基本都是调用操作系统本身的 API，一般来说这些文件 API 并不像数据库有事务机制（也很难办到），即使有也很可能有平台差异性。




###
问题 2：生产级项目的文件日志肯定需要按时间和日期进行分割和归档处理，以避免单个文件太大，同时保留一定天数的历史日志，你知道如何配置吗？可以在官方文档找到答案。
答：参考配置如下，使用 SizeAndTimeBasedRollingPolicy 来实现按照文件大小和历史文件保留天数，进行文件分割和归档：

<rollingPolicy class="ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy">
    <!--日志文件保留天数-->
    <MaxHistory>30</MaxHistory>
    <!--日志文件最大的大小-->
    <MaxFileSize>100MB</MaxFileSize>
    <!--日志整体最大
     可选的totalSizeCap属性控制所有归档文件的总大小。当超过总大小上限时，将异步删除最旧的存档。
     totalSizeCap属性也需要设置maxHistory属性。此外，“最大历史”限制总是首先应用，“总大小上限”限制其次应用。
     -->
    <totalSizeCap>10GB</totalSizeCap>
</rollingPolicy>


###
问题 2：日期时间数据始终要保存到数据库中，MySQL 中有两种数据类型 datetime 和 timestamp 可以用来保存日期时间。你能说说它们的区别吗，它们是否包含时区信息呢？
答：datetime 和 timestamp 的区别，主要体现在占用空间、表示的时间范围和时区三个方面。

占用空间：datetime 占用 8 字节；timestamp 占用 4 字节。

表示的时间范围：datetime 表示的范围是从“1000-01-01 00:00:00.000000”到“9999-12-31 23:59:59.999999”；timestamp 表示的范围是从“1970-01-01 00:00:01.000000”到“2038-01-19 03:14:07.999999”。

时区：timestamp 保存的时候根据当前时区转换为 UTC，查询的时候再根据当前时区从 UTC 转回来；而 datetime 就是一个死的字符串时间（仅仅对 MySQL 本身而言）表示。

需要注意的是，我们说 datetime 不包含时区是固定的时间表示，仅仅是指 MySQL 本身。使用 timestamp，需要考虑 Java 进程的时区和 MySQL 连接的时区。而使用 datetime 类型，则只需要考虑 Java 进程的时区（因为 MySQL datetime 没有时区信息了，JDBC 时间戳转换成 MySQL datetime，会根据 MySQL 的 serverTimezone 做一次转换）。

如果你的项目有国际化需求，我推荐使用时间戳，并且要确保你的应用服务器和数据库服务器设置了正确的匹配当地时区的时区配置。

其实，即便你的项目没有国际化需求，至少是应用服务器和数据库服务器设置一致的时区，也是需要的。


###
问题 1：泛型类型擦除后会生成一个 bridge 方法，这个方法同时又是 synthetic 方法。除了泛型类型擦除，你知道还有什么情况编译器会生成 synthetic 方法吗？

答：Synthetic 方法是编译器自动生成的方法（在源码中不出现）。除了文中提到的泛型类型擦除外，Synthetic 方法还可能出现的一个比较常见的场景，是内部类和顶层类需要相互访问对方的 private 字段或方法的时候。

编译后的内部类和普通类没有区别，遵循 private 字段或方法对外部类不可见的原则，但语法上内部类和顶层类的私有字段需要可以相互访问。为了解决这个矛盾，编译器就只能生成桥接方法，也就是 Synthetic 方法，来把 private 成员转换为 package 级别的访问限制。

比如如下代码，InnerClassApplication 类的 test 方法需要访问内部类 MyInnerClass 类的私有字段 name，而内部类 MyInnerClass 类的 test 方法需要访问外部类 InnerClassApplication 类的私有字段 gender。



###
问题 2：关于注解继承问题，你觉得 Spring 的常用注解 @Service、@Controller 是否支持继承呢？

答：Spring 的常用注解 @Service、@Controller，不支持继承。这些注解只支持放到具体的（非接口非抽象）顶层类上（来让它们成为 Bean），如果支持继承会非常不灵活而且容易出错。


###
问题 1：除了通过 @Autowired 注入 Bean 外，还可以使用 @Inject 或 @Resource 来注入 Bean。你知道这三种方式的区别是什么吗？
答：我们先说一下使用 @Autowired、@Inject 和 @Resource 这三种注解注入 Bean 的方式：
@Autowired，是 Spring 的注解，优先按照类型注入。当无法确定具体注入类型的时候，可以通过 @Qualifier 注解指定 Bean 名称。
@Inject：是 JSR330 规范的实现，也是根据类型进行自动装配的，这一点和
@Autowired 类似。如果需要按名称进行装配，则需要配合使用 @Named。
@Autowired 和 @Inject 的区别在于，前者可以使用 required=false 允许注入 null，后者允许注入一个 Provider 实现延迟注入。
@Resource：JSR250 规范的实现，如果不指定 name 优先根据名称进行匹配（然后才是类型），如果指定 name 则仅根据名称匹配。

###
问题 2：当 Bean 产生循环依赖时，比如 BeanA 的构造方法依赖 BeanB 作为成员需要注入，BeanB 也依赖 BeanA，你觉得会出现什么问题呢？又有哪些解决方式呢？
答：Bean 产生循环依赖，主要包括两种情况：一种是注入属性或字段涉及循环依赖，另一种是构造方法注入涉及循环依赖。接下来，我分别和你讲一讲。
第一种，注入属性或字段涉及循环依赖，比如 TestA 和 TestB 相互依赖：

@Component
public class TestA {
@Autowired
@Getter
private TestB testB;
}

@Component
public class TestB {
@Autowired
@Getter
private TestA testA;
}
针对这个问题，Spring 内部通过三个 Map 的方式解决了这个问题，不会出错。基本原理是，因为循环依赖，所以实例的初始化无法一次到位，需要分步进行：
1,创建 A（仅仅实例化，不注入依赖）；
2,创建 B（仅仅实例化，不注入依赖）；
3,为 B 注入 A（此时 B 已健全）；
4,为 A 注入 B（此时 A 也健全）。

第二种，构造方法注入涉及循环依赖。遇到这种情况的话，程序无法启动，比如 TestC 和 TestD 的相互依赖：

@Component
public class TestC {
@Getter
private TestD testD;

    @Autowired
    public TestC(TestD testD) {
        this.testD = testD;
    }
}

@Component
public class TestD {
@Getter
private TestC testC;

    @Autowired
    public TestD(TestC testC) {
        this.testC = testC;
    }
}

这种循环依赖的主要解决方式，有 2 种：
1,改为属性或字段注入；
2,使用 @Lazy 延迟注入。比如如下代码：
@Component
public class TestC {
@Getter
private TestD testD;

    @Autowired
    public TestC(@Lazy TestD testD) {
        this.testD = testD;
    }
}

其实，这种 @Lazy 方式注入的就不是实际的类型了，而是代理类，获取的时候通过代理去拿值（实例化）。所以，它可以解决循环依赖无法实例化的问题。


###
问题 1：除了 Spring 框架这两讲涉及的 execution、within、@within、@annotation 四个指示器外，Spring AOP 还支持 this、target、args、@target、@args。你能说说后面五种指示器的作用吗？

答：关于这些指示器的作用，你可以参考官方文档，文档里已经写的很清晰。https://docs.spring.io/spring/docs/current/spring-framework-reference/core.html#aop-pointcuts-designators
Supported Pointcut Designators
Spring AOP supports the following AspectJ pointcut designators (PCD) for use in pointcut expressions:

execution: For matching method execution join points. This is the primary pointcut designator to use when working with Spring AOP.

within: Limits matching to join points within certain types (the execution of a method declared within a matching type when using Spring AOP).

this: Limits matching to join points (the execution of methods when using Spring AOP) where the bean reference (Spring AOP proxy) is an instance of the given type.

target: Limits matching to join points (the execution of methods when using Spring AOP) where the target object (application object being proxied) is an instance of the given type.

args: Limits matching to join points (the execution of methods when using Spring AOP) where the arguments are instances of the given types.

@target: Limits matching to join points (the execution of methods when using Spring AOP) where the class of the executing object has an annotation of the given type.

@args: Limits matching to join points (the execution of methods when using Spring AOP) where the runtime type of the actual arguments passed have annotations of the given types.

@within: Limits matching to join points within types that have the given annotation (the execution of methods declared in types with the given annotation when using Spring AOP).

@annotation: Limits matching to join points where the subject of the join point (the method being run in Spring AOP) has the given annotation.

总结一下，按照使用场景，建议使用下面这些指示器：
针对方法签名，使用 execution；
针对类型匹配，使用 within（匹配类型）、this（匹配代理类实例）、target（匹配代理背后的目标类实例）、args（匹配参数）；
针对注解匹配，使用 @annotation（使用指定注解标注的方法）、@target（使用指定注解标注的类）、@args（使用指定注解标注的类作为某个方法的参数）。

你可能会问，@within 怎么没有呢？
其实，对于 Spring 默认的基于动态代理或 CGLIB 的 AOP，因为切点只能是方法，使用 @within 和 @target 指示器并无区别；但需要注意如果切换到 AspectJ，那么使用 @within 和 @target 这两个指示器的行为就会有所区别了，@within 会切入更多的成员的访问（比如静态构造方法、字段访问），一般而言使用 @target 指示器即可。


###
文档数据库 MongoDB，也是一种常用的 NoSQL。你觉得 MongoDB 的优势和劣势是什么呢？它适合用在什么场景下呢？
答：MongoDB 是目前比较火的文档型 NoSQL。虽然 MongoDB 在 4.0 版本后具有了事务功能，但是它整体的稳定性相比 MySQL 还是有些差距。因此，MongoDB 不太适合作为重要数据的主数据库，但可以用来存储日志、爬虫等数据重要程度不那么高，但写入并发量又很大的场景。
虽然 MongoDB 的写入性能较高，但复杂查询性能却相比 Elasticsearch 来说没啥优势；虽然 MongoDB 有 Sharding 功能，但是还不太稳定。因此，我个人建议在数据写入量不大、更新不频繁，并且不需要考虑事务的情况下，使用 Elasticsearch 来替换 MongoDB。


###
问题 1：在讲述用户标识不能从客户端获取这个要点的时候，我提到开发同学可能会因为用户信息未打通而通过前端来传用户 ID。那我们有什么好办法，来打通不同的系统甚至不同网站的用户标识吗？

答：打通用户在不同系统之间的登录，大致有以下三种方案。
第一种，把用户身份放在统一的服务端，每一个系统都需要到这个服务端来做登录状态的确认，确认后在自己网站的 Cookie 中保存会话，这就是单点登录的做法。这种方案要求所有关联系统都对接一套中央认证服务器（中央保存用户会话），在未登录的时候跳转到中央认证服务器进行登录或登录状态确认。因此，这种方案适合一个公司内部的不同域名下的网站。

第二种，把用户身份信息直接放在 Token 中，在客户端任意传递，Token 由服务端进行校验（如果共享密钥话，甚至不需要同一个服务端进行校验），无需采用中央认证服务器，相对比较松耦合，典型的标准是 JWT。这种方案适合异构系统的跨系统用户认证打通，而且相比单点登录的方案，用户体验会更好一些。

第三种，如果需要打通不同公司系统的用户登录状态，那么一般都会采用 OAuth 2.0 的标准中的授权码模式，基本流程如下：
1，第三方网站客户端转到授权服务器，上送 ClientID、重定向地址 RedirectUri 等信息。
2，用户在授权服务器进行登录并且进行授权批准（授权批准这步可以配置为自动完成）。
3，授权完成后，重定向回到之前客户端提供的重定向地址，附上授权码。
4，第三方网站服务端通过授权码 +ClientID+ClientSecret 去授权服务器换取 Token。这里的 Token 包含访问 Token 和刷新 Token，访问 Token 过期后用刷新 Token 去获得新的访问 Token。
因为我们不会对外暴露 ClientSecret，也不会对外暴露访问 Token，同时使用授权码换取 Token 的过程是服务端进行的，客户端拿到的只是一次性的授权码，所以这种模式比较安全。


###
安全兜底：涉及钱时，必须考虑防刷、限量和防重
问题 1：防重、防刷都是事前手段，如果我们的系统正在被攻击或利用，你有什么办法及时发现问题吗？

答：对于及时发现系统正在被攻击或利用，监控是较好的手段，关键点在于报警阈值怎么设置。我觉得可以对比昨天同时、上周同时的量，发现差异达到一定百分比报警，而且报警需要有升级机制。此外，有的时候大盘很大的话，活动给整个大盘带来的变化不明显，如果进行整体监控可能出了问题也无法及时发现，因此可以考虑对于活动做独立的监控报警。

###
问题 2：任何三方资源的使用一般都会定期对账，如果在对账中发现我们系统记录的调用量低于对方系统记录的使用量，你觉得一般是什么问题引起的呢？

答：我之前遇到的情况是，在事务内调用外部接口，调用超时后本地事务回滚本地就没有留下数据。更合适的做法是：
1，请求发出之前先记录请求数据提交事务，记录状态为未知。
2，发布调用外部接口的请求，如果可以拿到明确的结果，则更新数据库中记录的状态为成功或失败。如果出现超时或未知异常，不能假设第三方接口调用失败，需要通过查询接口查询明确的结果。
3，写一个定时任务补偿数据库中所有未知状态的记录，从第三方接口同步结果。
值得注意的是，对账的时候一定要对两边，不管哪方数据缺失都可能是因为程序逻辑有 bug，需要重视。此外，任何涉及第三方系统的交互，都建议在数据库中保持明细的请求 / 响应报文，方便在出问题的时候定位 Bug 根因。


##
问题 1：虽然我们把用户名和密码脱敏加密保存在数据库中，但日志中可能还存在明文的敏感数据。你有什么思路在框架或中间件层面，对日志进行脱敏吗

答：如果我们希望在日志的源头进行脱敏，那么可以在日志框架层面做。比如对于 logback 日志框架，我们可以自定义 MessageConverter，通过正则表达式匹配敏感信息脱敏。
需要注意的是，这种方式有两个缺点。
第一，正则表达式匹配敏感信息的格式不一定精确，会出现误杀漏杀的现象。一般来说，这个问题不会很严重。要实现精确脱敏的话，就只能提供各种脱敏工具类，然后让业务应用在日志中记录敏感信息的时候，先手动调用工具类进行脱敏。
第二，如果数据量比较大的话，脱敏操作可能会增加业务应用的 CPU 和内存使用，甚至会导致应用不堪负荷出现不可用。考虑到目前大部分公司都引入了 ELK 来集中收集日志，并且一般而言都不允许上服务器直接看文件日志，因此我们可以考虑在日志收集中间件中（比如 logstash）写过滤器进行脱敏。这样可以把脱敏的消耗转义到 ELK 体系中，不过这种方式同样有第一点提到的字段不精确匹配导致的漏杀误杀的缺点。


--------------------------------------------------------------------------------------------
我总结了 20 个最常用的、最基础数据结构与算法，不管是应付面试还是工作需要，只要集中精力逐一攻克这 20 个知识点就足够了。这里面有 10 个数据结构：数组、链表、栈、队列、散列表、二叉树、堆、跳表、图、Trie 树；10 个算法：递归、排序、二分查找、搜索、哈希算法、贪心算法、分治算法、回溯算法、动态规划、字符串匹配算法。

https://www.cs.usfca.edu/~galles/visualization/Algorithms.html
此网站可以将指定算法可视化



--------------------------------------------------------------------------------------------


#Java 的引用类型有哪些？
强，弱，软

#String的UTF-8编码和GBK编码有什么区别?

#Java乱码问题？


#CSS position定义元素定位机制？
absolute, relative, fixed

#Lock 和 Synchronized的区别，为什么有Synchronized 了还要Lock？
1, Synchronized发生异常时会自动释放占有的锁，Lock不会。
2, Lock 等待锁的过程可以用interrupt 来终止等待，而synchronized只能等待锁的释放，不能响应中断。
3, Lock 可以用trylock来知道有没有获取锁成功。

#如何保证多线程同时访问多个资源不导致死锁？


#Java的内存模型


#Java垃圾回收有哪些算法？


#如何定位CPU 100%的问题？


#Spring事务怎么处理的？事务标签哪些情况下会失效？


#Spring异常怎么处理的？


#Spring用过哪些模块？
@Controller, @RestController区别


#Java ClassLoader机制，线程上下文类加载器。
SPI

#Java agent，instrument.


#分布式事务如何处理？


#乐观锁悲观锁的区别？


#MySQL的两种存储引擎的区别？
MyISM， InnoDB
MyISM 不支持事务,但是操作是原子性的。
MyISM 不支持外键，支持表锁。


#一条SQL执行时间过长，如何优化？
1，SQL是否涉及多表关联，或者子查询，能否拆分。
2，加索引
3，做历史表
4，主从


#Redis 有哪些数据类型


#消息中间件如何保证消息不丢失，不重复处理？


#微服务有哪些组件，启动顺序怎样的？
MQ --> Register --> Configure -->...


#集群环境会话怎么处理的？


#两个整数如何不用第三个变量进行互换。


#给定3个数字，输出三个数字拼接成的最大的数。


#树数据结构的遍历，非递归如何遍历（堆栈+for循环）


#有N个阶梯，一个人每一步只能走一个台阶或是两个台阶，问这个人上这个阶梯有多少种走法？




--------------------------------------------------------------------------------------------
与上级意见不一致时，你怎么处理？
自己的优点缺点是什么？
你的学习方法是怎么样的？学习项目中遇到的最大困难是什么以及如何解决。
说一件最能证明你能力的事情。
针对你申请的这个职位，你认为你还缺什么？
独立工作以及参加团队协作时，你如何让自己的能力得到充分发挥？
介绍一下你的职业规划？
你对业内信息的关注渠道有哪些？
你喜欢聚会还是看书？平常喜欢做什么？最近读了什么书？
除了专业知识外，有没有学到哪门课程比较精深。

前一家的离职原因是什么？讲一讲你印象最深的一件事
介绍一个你影响最深的项目

============================================================================================