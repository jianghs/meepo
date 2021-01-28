package me.jianghs.meepo.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

/**
 * @className: InterruptRunner
 * @description:
 * @author: jianghs430
 * @createDate: 2021/1/28 15:31
 * @version: 1.0
 */
public class InterruptRunner implements Runnable {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Override
    public void run() {
        try {
            for (int i = 0; i < 100000; i++) {
                if (Thread.interrupted()) {
                    throw new Exception("线程停止");
                }
                logger.info("{}循环输出i:{}", Thread.currentThread().getName(), i++);
            }
            logger.info("{}后续打印", Thread.currentThread().getName());
        } catch (Exception e) {
            logger.error("{}捕获异常:{}", Thread.currentThread().getName(), e.toString());

        }

    }
}
