package me.jianghs.meepo.threadcommunication.waitandnotify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @className: Producer
 * @description:
 * @author: jianghs430
 * @createDate: 2021/2/3 13:37
 * @version: 1.0
 */
public class ThreadB implements Runnable {
    private Logger logger = LoggerFactory.getLogger(ThreadB.class);
    private Object lock;

    public ThreadB(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            logger.info("{}开始唤醒....", Thread.currentThread().getName());
            lock.notify();
            logger.info("{}唤醒结束....", Thread.currentThread().getName());
        }
    }
}
