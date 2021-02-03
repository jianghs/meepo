# 线程间通信

## wait&notify&notifyAll

* wait操作之前必须获得锁
* wait后线程进入等待状态，并且释放锁
* notify会随机唤醒一个正在等待的线程
* notifyAll会唤醒全部正在等待的线程，具体执行顺序取决于线程优先级、CPU调度。
* notify和notifyAll执行完不会立即释放锁，只有退出synchronized代码块才会释放。
* wait时调用interrupt会报异常

## wait&notify&notifyAll为什么定义在Object类

由于每个对象都拥有monitor（即锁），所以让当前线程等待某个对象的锁，当然应该通过这个对象来操作了。而不是用当前线程来操作，因为当前线程可能会等待多个线程的锁，如果通过线程来操作，就非常复杂了。
