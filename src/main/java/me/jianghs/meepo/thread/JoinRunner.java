package me.jianghs.meepo.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JoinRunner implements Runnable {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void run() {
        synchronized (this) {
            logger.info("{}开始...", Thread.currentThread().getName());
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                logger.error("异常：{}", e);
                e.printStackTrace();
            }
            logger.info("{}结束...", Thread.currentThread().getName());
        }

    }
}
