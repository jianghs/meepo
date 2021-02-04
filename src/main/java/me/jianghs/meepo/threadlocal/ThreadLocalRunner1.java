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
public class ThreadLocalRunner1 implements Runnable {
    private Logger logger = LoggerFactory.getLogger(ThreadLocalRunner1.class);

    private ThreadLocal<String> threadLocal;
    private boolean flag;

    public ThreadLocalRunner1(ThreadLocal threadLocal, boolean flag) {
        this.threadLocal = threadLocal;
        this.flag = flag;
    }

    @Override
    public void run() {
        try {
            threadLocal.set("线程1");
            logger.info("线程1的threadlocal值：{}，flag值：{}", threadLocal.get(), flag);
            threadLocal.remove();
            logger.info("线程1的threadlocal值：{}，flag值：{}", threadLocal.get(), flag);
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
