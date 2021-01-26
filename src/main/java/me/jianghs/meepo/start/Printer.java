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

    public Printer(String name) {
        super(name);
    }

    @Override
    public void run() {
        log.info("{}打印进行中...", this.getName());
    }
}
