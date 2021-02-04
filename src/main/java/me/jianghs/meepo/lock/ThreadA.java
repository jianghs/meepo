package me.jianghs.meepo.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @className: ThreadA
 * @description:
 * @author: jianghs430
 * @createDate: 2021/2/4 15:26
 * @version: 1.0
 */
public class ThreadA implements Runnable{
    private Logger logger = LoggerFactory.getLogger(ThreadA.class);
    Lock lock;

    public ThreadA(Lock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
//        Lock lock = new ReentrantLock();
        lock.lock();
        try {
            logger.info("{}得到了锁...", Thread.currentThread().getName());
        } catch (Exception e) {
            logger.error("异常", e);
        } finally {
            lock.unlock();
            logger.info("{}释放了锁...", Thread.currentThread().getName());
        }
    }
}
