package me.jianghs.meepo.synchronizedtest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @className: FeeCalculator
 * @description:
 * @author: jianghs430
 * @createDate: 2021/1/29 13:24
 * @version: 1.0
 */
public class FeeCalculator implements Runnable {
    Logger logger = LoggerFactory.getLogger(FeeCalculator.class);
    public double fee;

    public FeeCalculator(double fee) {
        this.fee = fee;
    }

    @Override
    public synchronized void run() {
        this.fee *= 0.2;
        logger.info("手续费为：{}", fee);
    }
}
