# ThreadLocal

- 每个Thread中有一个ThreadLocalMap
- ThreadLocalMap的key是ThreadLocal,值由我们自己设定
- ThreadLocal是个弱引用，当为null时，会被GC
- 当ThreadLocal是null时，会被垃圾回收，但是ThreadLocalMap的生命周期是和Thread一致的。此时会造成key消失但是value还存在，引起内存泄露。
- 解决办法：使用完ThreadLocal后，执行remove操作，避免出现内存溢出情况。