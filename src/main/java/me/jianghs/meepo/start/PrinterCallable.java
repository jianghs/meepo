package me.jianghs.meepo.start;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Callable;

/**
 * @className: PrinterCallable
 * @description:
 * @author: jianghs430
 * @createDate: 2021/1/26 16:07
 * @version: 1.0
 */
public class PrinterCallable implements Callable<Integer> {
    Logger log = LoggerFactory.getLogger(PrinterCallable.class);

    private static int count = 0;

    @Override
    public Integer call() {
        synchronized (this) {
            for(int i = 0; i < 5; i++) {
                log.info("{}的count是：{}", Thread.currentThread().getName(), count ++);
            }
        }

        return count;
    }
}
