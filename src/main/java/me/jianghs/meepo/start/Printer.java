package me.jianghs.meepo.start;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @className: Pprinter
 * @description:
 * @author: jianghs430
 * @createDate: 2021/1/26 15:08
 * @version: 1.0
 */
public class Printer extends Thread {
    private static final Logger log = LoggerFactory.getLogger(Printer.class);

    private static int count = 0;

    @Override
    public void run() {
        // 注意类锁和对象锁
        synchronized (this) {
            for (int i = 0; i < 5; i++) {
                log.info("{}的count:{}", Thread.currentThread().getName(), count++);
            }
        }

    }
}
