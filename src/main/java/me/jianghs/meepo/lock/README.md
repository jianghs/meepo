# Lock

## 有了synchronized为什么还要有Lock?

- `synchronized`是java内置关键字，而`Lock`是个java类。
- `synchronized`无法判断是否获取到锁，而`Lock`可以。
- `synchronized`自动获取释放锁，而`Lock`需要手动加锁及释放锁
- `synchronized`的锁可重入、不可中断、非公平，而`Lock`锁可重入、可中断、可公平。
- `Lock`锁适合大量同步的代码的同步问题，`synchronized`锁适合代码少量的同步问题。
- `synchronized`放弃锁只有两种情况：①线程执行完了同步代码块的内容②发生异常；而`Lock`不同，它可以设定超时时间，也就是说他可以在获取锁时便设定超时时间，如果在你设定的时间内它还没有获取到锁，那么它会放弃获取锁然后响应放弃操作。