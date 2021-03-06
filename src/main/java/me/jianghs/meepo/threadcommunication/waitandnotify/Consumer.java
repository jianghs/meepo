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
public class Consumer implements Runnable {
    private Logger logger = LoggerFactory.getLogger(Consumer.class);
    private List<Object> list;

    public Consumer(List<Object> list) {
        this.list = list;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (list) {
                try {
                    // 多消费者时，需采用while
                    while (list.size() == 0) {
                        list.wait();
                    }
                    list.remove(0);
                    logger.info("{}:消费，剩余产品数量{}", Thread.currentThread().getName(), list.size());
                    list.notifyAll();
                } catch (InterruptedException e) {
                    logger.error("消费异常", e);
                }
            }
        }
    }
}
