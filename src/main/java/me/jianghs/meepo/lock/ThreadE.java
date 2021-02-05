package me.jianghs.meepo.lock;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;

/**
 * @className: ThreadD
 * @description:
 * @author: jianghs430
 * @createDate: 2021/2/5 13:30
 * @version: 1.0
 */
public class ThreadE implements Runnable {

    private Logger logger = LoggerFactory.getLogger(ThreadE.class);
    private Lock lock;
    private Condition condition;
    private volatile boolean flag;

    public ThreadE(Lock lock, Condition condition, boolean flag) {
        this.lock = lock;
        this.condition = condition;
        this.flag = flag;
    }

    @Override
    public void run() {
        while(true) {
            lock.lock();
            try {
                if(!flag) {
                    condition.await();
                }
                flag = false;
                Thread.sleep(100);
                logger.info("{}运行", Thread.currentThread().getName());
                condition.signal();
            } catch (Exception e) {
                logger.error("异常", e);
            } finally {
                lock.unlock();
            }
        }
    }
}
