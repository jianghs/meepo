package me.jianghs.meepo.synchronizedtest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @className: StaticLooper
 * @description:
 * @author: jianghs430
 * @createDate: 2021/1/29 15:08
 * @version: 1.0
 */
public class StaticLooper implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(StaticLooper.class);
    @Override
    public void run() {
        String name = Thread.currentThread().getName();
        try {
            if ("A".equals(name)) {
                print1();
            } else if ("B".equals(name)) {
                this.print2();
            } else {
                this.print3();
            }
        } catch (Exception e) {

        }

    }

    public synchronized static void print1() throws InterruptedException {
        for (int i = 0; i < 10; i++) {
            logger.info("{}的print1正在打印{}", Thread.currentThread().getName(), i);
            Thread.sleep(500);
        }
    }

    public void print2() throws InterruptedException {
        synchronized (StaticLooper.class) {
            for (int i = 0; i < 10; i++) {
                logger.info("{}的print2正在打印{}", Thread.currentThread().getName(), i);
                Thread.sleep(500);
            }
        }
    }

    public void print3() throws InterruptedException {
        synchronized (this) {
            for (int i = 0; i < 10; i++) {
                logger.info("{}的print2正在打印{}", Thread.currentThread().getName(), i);
                Thread.sleep(500);
            }
        }
    }

}
