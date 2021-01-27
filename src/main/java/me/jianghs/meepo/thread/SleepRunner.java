package me.jianghs.meepo.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @className: SleepRunner
 * @description:
 * @author: jianghs430
 * @createDate: 2021/1/27 9:34
 * @version: 1.0
 */
public class SleepRunner implements Runnable {
    Logger logger = LoggerFactory.getLogger(this.getClass());


    public SleepRunner() {
        count = 0;
    }

    private static int count;

    @Override
    public void run() {
        synchronized (this) {
            for(int i = 0; i < 5; i++) {
                logger.info("{}的count是：{}", Thread.currentThread().getName(), count ++);
            }
        }

    }
}
