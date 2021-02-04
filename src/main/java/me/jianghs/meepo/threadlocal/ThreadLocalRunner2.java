package me.jianghs.meepo.threadlocal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @className: ThreadLocalRunner
 * @description:
 * @author: jianghs430
 * @createDate: 2021/2/4 13:38
 * @version: 1.0
 */
public class ThreadLocalRunner2 implements Runnable {
    private Logger logger = LoggerFactory.getLogger(ThreadLocalRunner2.class);

    private ThreadLocal<String> threadLocal;
    private boolean flag;

    public ThreadLocalRunner2(ThreadLocal threadLocal, boolean flag) {
        this.threadLocal = threadLocal;
        this.flag = flag;
    }

    @Override
    public void run() {
        try {
            threadLocal.set("线程2");
            logger.info("线程2的threadlocal值：{}，flag值：{}", threadLocal.get(), flag);
            threadLocal.remove();
            logger.info("线程2的threadlocal值：{}，flag值：{}", threadLocal.get(), flag);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
