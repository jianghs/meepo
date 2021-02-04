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
public class ThreadB implements Runnable{
    private Logger logger = LoggerFactory.getLogger(ThreadB.class);
    Lock lock;

    public ThreadB(Lock lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
//        Lock lock = new ReentrantLock();
        if (lock.tryLock()) {
            lock.lock();
            try {
                logger.info("{}得到了锁...", Thread.currentThread().getName());
            } catch (Exception e) {
                logger.error("异常", e);
            } finally {
                lock.unlock();
                logger.info("{}释放了锁...", Thread.currentThread().getName());
            }
        } else {
            logger.info("{}获取锁失败...", Thread.currentThread().getName());
        }

    }
}
