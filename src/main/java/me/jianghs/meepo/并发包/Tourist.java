package me.jianghs.meepo.并发包;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.TimeUnit;

/**
 * @className: Tourist
 * @description:
 * @author: jianghs430
 * @createDate: 2021/2/20 15:32
 * @version: 1.0
 */
public class Tourist implements Runnable {
    private int touristId;
    private CyclicBarrier cyclicBarrier;

    private static Logger logger = LoggerFactory.getLogger(Tourist.class);


    public Tourist(int touristId, CyclicBarrier cyclicBarrier) {
        this.touristId = touristId;
        this.cyclicBarrier = cyclicBarrier;
    }

    @Override
    public void run() {
        logger.info("乘客{}开始", touristId);
        this.spendSeveralSeconds();
        this.waitAndPrint("乘客" + touristId + "已经上车，正在等其他乘客上车");

        this.spendSeveralSeconds();
        this.waitAndPrint("乘客" + touristId + "已经下车，正在等其他乘客下车");
    }

    private void waitAndPrint(String msg) {
        logger.info(msg);
        try {
            cyclicBarrier.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (BrokenBarrierException e) {
            e.printStackTrace();
        }
    }

    private void spendSeveralSeconds() {
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
