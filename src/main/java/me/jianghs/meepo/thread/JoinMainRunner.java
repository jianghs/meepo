package me.jianghs.meepo.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class JoinMainRunner implements Runnable {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private Thread joinRunner;

    public JoinMainRunner(Thread joinRunner) {
        this.joinRunner = joinRunner;
    }

    @Override
    public void run() {
        synchronized (this) {
            logger.info("{}开始...", Thread.currentThread().getName());
            try {
                joinRunner.join();
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                logger.error("异常：{}", e);
                e.printStackTrace();
            }
            logger.info("{}结束...", Thread.currentThread().getName());
        }

    }
}
