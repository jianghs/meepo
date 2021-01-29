package me.jianghs.meepo.synchronizedtest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @className: Looper
 * @description:
 * @author: jianghs430
 * @createDate: 2021/1/29 13:40
 * @version: 1.0
 */
public class Looper implements Runnable {
    Logger logger = LoggerFactory.getLogger(Looper.class);
    @Override
    public void run() {
        try {
            String name = Thread.currentThread().getName();
            if ("A".equals(name)) {
                this.print1();
            } else if ("B".equals(name)) {
                this.print2();
            } else {
                this.print3();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void print1() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            logger.info("{}的print1正在打印{}", Thread.currentThread().getName(), i);
            if (i == 5) {
                throw new InterruptedException();
            }
            Thread.sleep(500);
        }
    }

    public synchronized void print2() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            logger.info("{}的print2正在打印{}", Thread.currentThread().getName(), i);
            Thread.sleep(500);
        }
    }

    public void print3() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            logger.info("{}的print3正在打印{}", Thread.currentThread().getName(), i);
            Thread.sleep(500);
        }
    }
}
