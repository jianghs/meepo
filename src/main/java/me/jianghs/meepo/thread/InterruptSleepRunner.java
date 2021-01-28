package me.jianghs.meepo.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @className: InterruptRunner
 * @description:
 * @author: jianghs430
 * @createDate: 2021/1/28 15:31
 * @version: 1.0
 */
public class InterruptSleepRunner implements Runnable {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void run() {
        logger.info("{}开始...", Thread.currentThread().getName());
        try {
            for(int i = 0; i < 100; i++) {
                Thread.sleep(50);
                logger.info("{}循环输出i:{}", Thread.currentThread().getName(), i);
            }
        } catch (InterruptedException e) {
            logger.error("异常：{}", e);
        }
        logger.info("{}结束...", Thread.currentThread().getName());
    }
}
