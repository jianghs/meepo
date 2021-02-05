package me.jianghs.meepo.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @className: LockProducer
 * @description:
 * @author: jianghs430
 * @createDate: 2021/2/5 11:03
 * @version: 1.0
 */
public class LockProducer implements Runnable {
    private Logger logger = LoggerFactory.getLogger(LockProducer.class);
    private List<Object> list;
    private Lock lock;
    private Condition condition;

    public LockProducer(List<Object> list, Lock lock, Condition condition) {
        this.list = list;
        this.lock = lock;
        this.condition = condition;
    }

    @Override
    public void run() {
        while(true) {
            lock.lock();
            try {
                // 此处需用while
                while(list.size() == 100) {
                    condition.await();
                }
                list.add(new Object());
                logger.info("{}开始生产，剩余：{}", Thread.currentThread().getName(), list.size());
                // 此处需用signalAll
                condition.signalAll();
            } catch (Exception e) {
                logger.error("异常", e);
            } finally {
                lock.unlock();
            }
        }
    }
}
