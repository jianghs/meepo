package me.jianghs.meepo.并发包;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @className: CountDownLatch
 * @description:
 * @author: jianghs430
 * @createDate: 2021/2/20 14:17
 * @version: 1.0
 */
public class CyclicBarrierExample {

    private static Logger logger = LoggerFactory.getLogger(CyclicBarrierExample.class);

    public static void main(String[] args) throws BrokenBarrierException, InterruptedException {

        CyclicBarrier cyclicBarrier = new CyclicBarrier(11);

        // 创建10个线程
        for (int i = 0; i < 10; i++) {
            new Thread(new Tourist(i, cyclicBarrier)).start();
        }

        // 主线程进入阻塞，等待所有乘客都上车
        cyclicBarrier.await();
        logger.info("所有乘客已经上车");

        cyclicBarrier.await();
        logger.info("所有乘客已经下车");
    }
}
