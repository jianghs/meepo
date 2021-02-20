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