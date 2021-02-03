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
                // 如果没有资源等待生产
                if (list.size() == 0) {
                    try {
                        list.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                list.remove(0);
                logger.info("{}:消费，剩余产品数量{}", Thread.currentThread().getName(), list.size());
                list.notifyAll();
            }
        }
    }
}
