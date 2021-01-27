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
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void run() {
        synchronized (this) {
            logger.info("{}开始...", Thread.currentThread().getName());
            try {
                for (int i = 0; i < 10; i++) {
                    Thread.sleep(500);
                    logger.info("{}输出{}", Thread.currentThread().getName(), i);
                }
            } catch (InterruptedException e) {
                logger.error("异常：{}", e);
                e.printStackTrace();
            }
            logger.info("{}结束...", Thread.currentThread().getName());
        }

    }
}
