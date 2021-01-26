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
public class PrinterRunnable implements Runnable {
    private static final Logger log = LoggerFactory.getLogger(PrinterRunnable.class);

    @Override
    public void run() {
        log.info("{}打印进行中...", Thread.currentThread().getName());
    }
}
