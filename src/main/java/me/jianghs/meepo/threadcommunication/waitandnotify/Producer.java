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
public class Producer implements Runnable {
    private Logger logger = LoggerFactory.getLogger(Producer.class);
    private List<Object> list;


    public Producer(List<Object> list) {
        this.list = list;
    }

    @Override
    public void run() {
        while (true) {
            synchronized (list) {
                try {
                    // 多生产者时，需采用while
                    while (list.size() == 100) {
                        list.wait();
                    }
                    list.add(new Object());
                    logger.info("{}:生产，剩余产品数量{}", Thread.currentThread().getName(), list.size());
                    list.notifyAll();
                } catch (InterruptedException e) {
                    logger.error("生产异常", e);
                }
            }
        }
    }
}
