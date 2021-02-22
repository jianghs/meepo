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