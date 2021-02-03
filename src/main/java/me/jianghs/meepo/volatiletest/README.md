# volatile

## JMM Java内存模型

所有数据存在主存中，每个线程都有自己的工作内存。线程对数据的操作必须再工作内存中进行，而不能在主存中进行。并且每个线程不能访问其他线程的工作内存。

## 并发3大概念

* 原子性

    JMM中只有简单的读取和赋值（必须是数字赋值）是原子操作。

    synchronized和Lock保证每一时刻只有一个线程执行代码块，从而保证了原子性。

* 可见性

    * volatile:当一个共享变量被这个关键字修饰时，它会保证修改的值立刻更新到主存，其他线程读取时就能从主存读取最新值。
    * synchronized和Lock：保证每一时刻只有一个线程执行代码块，并且在释放锁之前会将最新的变量值刷新到主存。
    * 注意System.out 和 日志框架，底层自带synchronized，然后锁粗化，无法重现。

* 有序性

    JMM的先天有序性，happen-before（先行发生）原则。

## volatile

### 有序性举例

```java
public class Test() {
    public static void main(String[] args) {
        x = 1; // 非volatile
        y = 1; // 非volatile
        flag = true; // volatile
        x = 2; // 非volatile
        y = 2; // 非volatile
    }
}
```

  * 可以确保flag之前的语句在flag之后的语句执行。

  * 无法保证flag之前的语句或者之后的语句内部的执行顺序。


### 原理

编译后的代码，加入volatile关键字后，会多出一个lock前缀指令。

这个指令相当于一个内存屏障：

- 确保屏障前后的指令顺序。
- 强制对工作内存的修改立即刷入主存。
- 写操作导致其他CPU中对应的缓存无效。

### 常用场景

- 状态量标记，一个线程操作另一个线程运行停止
- 单例模式中的double check