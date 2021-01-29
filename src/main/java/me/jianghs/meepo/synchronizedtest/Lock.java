package me.jianghs.meepo.synchronizedtest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @className: Lock
 * @description:
 * @author: jianghs430
 * @createDate: 2021/1/29 14:47
 * @version: 1.0
 */
public class Lock implements Runnable {
    private Logger logger = LoggerFactory.getLogger(Lock.class);

    @Override
    public void run() {
        try {
            String name = Thread.currentThread().getName();
            if ("LOCK-A".equals(name)) {
                this.print1();
            } else {
                this.print2();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void print1() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            logger.info("{}的print1正在打印{}", Thread.currentThread().getName(), i);
            Thread.sleep(500);
        }
    }

    public void print2() throws InterruptedException {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                logger.info("{}的print2正在打印{}", Thread.currentThread().getName(), i);
                Thread.sleep(500);
            }
        }

    }

}
