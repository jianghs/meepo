package me.jianghs.meepo.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @className: ThreadLockConditionA
 * @description:
 * @author: jianghs430
 * @createDate: 2021/2/5 10:44
 * @version: 1.0
 */
public class ThreadLockConditionA implements Runnable {
    private Logger logger = LoggerFactory.getLogger(ThreadLockConditionA.class);
    private Lock lock;
    private Condition condition;

    public ThreadLockConditionA(Lock lock, Condition condition) {
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        lock.lock();
        try {
            logger.info("{}开始等待", Thread.currentThread().getName());
            condition.await();
            logger.info("{}等待结束，被唤醒...", Thread.currentThread().getName());
        } catch (Exception e) {
            logger.error("异常", e);
        } finally {
            lock.unlock();
        }

    }
}
