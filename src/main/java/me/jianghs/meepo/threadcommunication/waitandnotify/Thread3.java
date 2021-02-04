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
public class Thread3 implements Runnable {
    private Logger logger = LoggerFactory.getLogger(Thread3.class);
    List<Object> list;

    public Thread3(List<Object> list) {
        this.list = list;
    }
    @Override
    public void run() {
        while (true) {
            synchronized (list) {
                try {
                    // 多消费者时，需采用while
                    while (list.size()%3 == 2 || list.size()%3 == 0) {
                        list.wait();
                    }
                    logger.info("{}运行结果：{}", Thread.currentThread().getName(), "B");
                    list.add(new Object());
                    list.notifyAll();
                } catch (InterruptedException e) {
                    logger.error("异常", e);
                }
            }
        }
    }
}
