package me.jianghs.meepo.并发包;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.crypto.Data;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Exchanger;

/**
 * @className: CountDownLatch
 * @description:
 * @author: jianghs430
 * @createDate: 2021/2/20 14:17
 * @version: 1.0
 */
public class ExchangerExample {

    private static Logger logger = LoggerFactory.getLogger(ExchangerExample.class);

    public static void main(String[] args) {
        Exchanger<String> exchanger = new Exchanger<>();

        new Thread(() -> {
            logger.info("线程1启动");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                String data = exchanger.exchange("我来自线程1");
                logger.info("从线程2收到的数据{}", data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();

        new Thread(() -> {
            logger.info("线程2启动");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            try {
                String data = exchanger.exchange("我来自线程2");
                logger.info("从线程1收到的数据{}", data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }
}
