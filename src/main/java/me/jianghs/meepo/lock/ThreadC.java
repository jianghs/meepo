package me.jianghs.meepo.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Lock;

/**
 * @className: ThreadA
 * @description:
 * @author: jianghs430
 * @createDate: 2021/2/4 15:26
 * @version: 1.0
 */
public class ThreadC implements Runnable{
    private Logger logger = LoggerFactory.getLogger(ThreadC.class);
    Lock lock;

    public ThreadC(Lock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        try {
            doSomething();
        } catch (InterruptedException e) {
            logger.error("{}被打断异常：",Thread.currentThread().getName(), e);
        }
    }

    private void doSomething() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            logger.info("{}得到了锁...", Thread.currentThread().getName());
            for (; ; ) {
//                logger.info("{}", i);
            }
        } finally {
            lock.unlock();
            logger.info("{}释放了锁...", Thread.currentThread().getName());
        }
    }
}
