package me.jianghs.meepo.start;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
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
    /**
     * Computes a result, or throws an exception if unable to do so.
     *
     * @return computed result
     * @throws Exception if unable to compute a result
     */
    @Override
    public Integer call() throws Exception {
        log.info("{}的call方法内部执行", Thread.currentThread().getName());
        // 随机返回一个数字
//        Thread.sleep(500);
        return new Random().nextInt(100);
    }
}
