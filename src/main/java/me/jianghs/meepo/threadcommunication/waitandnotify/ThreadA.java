package me.jianghs.meepo.threadcommunication.waitandnotify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @className: Consumer
 * @description:
 * @author: jianghs430
 * @createDate: 2021/2/3 13:37
 * @version: 1.0
 */
public class ThreadA implements Runnable {
    private Logger logger = LoggerFactory.getLogger(ThreadA.class);
    private Object lock;

    public ThreadA(Object lock) {
        this.lock = lock;
    }

    @Override
    public void run() {
        synchronized (lock) {
            try {
                logger.info("{}等待....", Thread.currentThread().getName());
                lock.wait(4000);
                logger.info("{}被唤醒成功....", Thread.currentThread().getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
