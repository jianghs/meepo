## 并发包

### AtomicInteger\AtomicBoolean\AtomicLong\AtomicReference

原理：volatile + 乐观锁，CAS（UnSafe类），内存中的值、目标旧值、更新后的新值，只有当内存中的值和目标旧值相等时，才会将内存中的值更新为新值。
同时通过自旋，保证更新成功。

#### ABA
- 一个线程从内存位置V拿出值A，此时另外一个线程也拿出A，并且修改成了B，后面又修改成A。这个时候第一个线程CAS时发现仍然是A，从而CAS操作成功。
- 解决方案：操作时添加标识或者通过版本号。用AtomicStampedReference/AtomicMarkableReference

### CountDownLatch（倒计数门阀）

主任务等待所有子任务执行完成后继续执行。

1. 指定一个大于0的int数字。计数器
1. countDown()每次调用会将count减1，直至0。
1. await()使得主线程进入阻塞状态，直到count为0。

### CyclicBarrier（循环屏障）

允许多个线程执行完相应的操作后彼此等待共同到达一个障点。

1. 指定一个大于0的int整数，分片数量
1. 所有的await调用导致其值为0时候，reset操作会默认执行。

### CountDownLatch VS CyclicBarrier
1. CountDownLatch的await方法等待计数器倒数到0，而CyclicBarrier的await方法的线程会等待其他线程到达障点。
1. CyclicBarrier的count可以被重置，从而重复使用。而CountDownLatch不行。
1. CyclicBarrier底层由Lock和Condition实现，CountDownLatch由AQS实现。
1. CountDownLatch允许count为0，CyclicBarrier不允许parties为0。

### Exchanger（交换器）

两个线程间进行数据交换

1. exchange()方法是一个阻塞方法，只有成对的线程执行exchange调用后才会退出阻塞。
1. 可以实现生产者、消费者。consumer直接exchange(null)

### Semaphore（信号量）

一个时刻允许多个线程对共享资源进行并行操作。

1. Semaphore的tryAcquire设置为1，实现tryLock。
1. 许可证的释放必须确保执行了acquire的线程才可以。

### BlockingQueue（阻塞队列）

1. 当队列种的资源满时，对队列写操作的线程阻塞挂起。
1. 当队列种的资源为0时，对队列读操作的线程阻塞挂起。

#### ArrayBlockingQueue

基于数组结构实现的FIFO阻塞队列。

1. 阻塞式写入put
1. 指定时间内写入阻塞offer
1. 非阻塞式写入add


1. 阻塞式读take
1. 指定时间内读取阻塞poll
1. 非阻塞式读peek

原理：Lock+Condition

#### PriorityBlockingQueue（优先级阻塞队列）

基于数组实现的无边界有序阻塞队列。

根据某种规则，将插入尾部的数据进行排序，所以不遵循FIFO。理论上是无边界的。

初始化指定的参数是指初始容量

原理：Lock+Condition

#### LinkedBockingQueue

可选边界基于链表实现的FIFO队列。

可以在构造函数时指定边界，默认无边界。

#### DelayQueue

无边界阻塞队列。存入队列的数据元素会被延迟单位事件后才被消费。根据优先级排序，根据过期时间排序。

#### SynchronousQueue

每次的写入操作必须等待其他线程读取。

#### LinkedBlockingDeque

基于链表实现的双向阻塞队列。

#### LinkedTransferQueue

transfer方法将元素防止队列尾部，并且在没有被移除时，产生该元素数据的线程一直处于阻塞状态。

### ConcurrentQueue（并发队列）

无锁（Lock Free）依赖CPU底层技术实现。

#### ConcurrentLinkedQueue

无锁的、线程安全的、性能高效的、基于链表结构实现的FIFO单向队列。

#### ConcurrentLinkedDeque

无锁的、线程安全的、性能高效的、基于链表结构实现的FIFO双向队列。

### ConcurrentMap（并发映射）

#### ConcurrentHashMap
1. 1.8之前采用分段锁
    
    每个分段锁对象可同步若干桶。默认由16个锁对象组成。

1. 1.8及1.8以后采用数组+单向链表+红黑树，利用CAS+Synchronized保证安全性。

#### ConcurrentSkipListMap（基于跳表）

### 写时拷贝算法（CopyOnWrite）

所有线程读取数据时不会对数据加锁。写时才会加锁，然后将最新的数据复制一份，基于最新的复制数据进行写操作，最后将引用指向最新的数据集合。

数组复制会带来开销。

不能保证实时一致，只能保证最终一致。

适用场景：读远大于写。

#### CopyOnWriteArrayList

#### CopyOnWriteArraySet

### 线程池

#### ThreadPoolExecutor

1. corePoolSize：线程池中的核心线程数量，即使处于空闲，数量也不会减少。
1. maximumPoolSize：线程池允许的最大线程数量。
1. keepAliveTime：线程池中超过核心线程数且处于空闲状态的线程存活的时间。
1. TimeUnit：单位
1. BlockingQueue：存放已提交至线程池但未被执行的任务。
1. ThreadFactory：线程工厂，可以自定义线程名、优先级等信息
1. 拒绝策略：任务数量超过阻塞对列时的拒绝策略
    1. Discard丢弃
    1. Abort中止
    1. DiscardOldest丢弃最老
    1. CallerRuns调用者线程执行策略

线程池创建后，内部运行的线程只有第一次执行时才会被创建。

1. shutdown:等正在执行的线程和阻塞队列中的线程执行完成后关闭。新提交的任务会被拒绝。可以结合线程池awaitTermination等待线程池关闭后的下一步动作。
1. shutdownNow:立即关闭
1. 二者结合
#### ScheduledThreadPoolExecutor

可以实现定时任务。

#### Executors

1. newCachedThreadPool创建一个可缓存线程池，如果线程池长度超过处理需要，可灵活回收空闲线程，若无可回收，则新建线程。
1. newFixedThreadPool 创建一个定长线程池，可控制线程最大并发数，超出的线程会在队列中等待。
1. newScheduledThreadPool 创建一个定长线程池，支持定时及周期性任务执行。
1. newSingleThreadExecutor 创建一个单线程化的线程池，它只会用唯一的工作线程来执行任务，保证所有任务按照指定顺序(FIFO, LIFO, 优先级)执行。

阻塞队列默认时无边界的，资源有限的情况下，容易引起OOM

### Fork/Join

将一个复杂的任务拆分（Fork）成若干个并行执行，然后将结果合并（Join）

#### ForkJoinPool

#### ForkJoinTask

1. RecursiveTask:返回计算结果
1. RecursiveAction:不会返回计算结果