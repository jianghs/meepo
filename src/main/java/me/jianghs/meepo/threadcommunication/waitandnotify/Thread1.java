package me.jianghs.meepo.threadcommunication.waitandnotify;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * @className: Resource
 * @description:
 * @author: jianghs430
 * @createDate: 2021/2/3 14:35
 * @version: 1.0
 */
public class Thread1 implements Runnable {
    private Logger logger = LoggerFactory.getLogger(Thread1.class);
//    private volatile int num;
//    private Object lock;
    List<Object> list;

    public Thread1(List<Object> list) {
        this.list = list;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (list) {
                try {
                    // 多消费者时，需采用while
                    while (list.size() % 3 == 0 || list.size() % 3 == 1) {
                        list.wait();
                    }
                    logger.info("{}运行结果：{}", Thread.currentThread().getName(), "C");
                    list.add(new Object());
                    list.notifyAll();
                } catch (InterruptedException e) {
                    logger.error("异常", e);
                }
            }
        }
    }
}
