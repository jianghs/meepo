package me.jianghs.meepo.并发包;

import me.jianghs.meepo.volatiletest.MachineMain;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @className: CountDownLatch
 * @description:
 * @author: jianghs430
 * @createDate: 2021/2/20 14:17
 * @version: 1.0
 */
public class CountDownLatchExample {

    private static Logger logger = LoggerFactory.getLogger(CountDownLatchExample.class);

    public static void main(String[] args) throws InterruptedException {
        // 转换为商品的价格
        List<ProductPrice> list = new ArrayList<>();
        ProductPrice productPrice1 = new ProductPrice(1, 0.5);
        list.add(productPrice1);
        ProductPrice productPrice2 = new ProductPrice(2, 1.5);
        list.add(productPrice2);
        ProductPrice productPrice3 = new ProductPrice(3, 2.5);
        list.add(productPrice3);
        ProductPrice productPrice4 = new ProductPrice(4, 3.5);
        list.add(productPrice4);
        ProductPrice productPrice5 = new ProductPrice(5, 4.5);
        list.add(productPrice5);

        // 定于CountDownLatch，计数器数量为子任务数量
        CountDownLatch latch = new CountDownLatch(list.size());
        list.forEach(pp -> new Thread(() -> {
            logger.info("{}开始计算价格", pp.getProdId());
            // 休眠模拟
            try {
                Random random = new Random(pp.getProdId());
                TimeUnit.SECONDS.sleep(random.nextInt());
                if (pp.getProdId() % 2 == 0) {
                    pp.setPrice(pp.getProdId() * 0.9);
                } else {
                    pp.setPrice(pp.getProdId() * 0.7);
                }
                logger.info("{}结束计算价格", pp.getProdId());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                latch.countDown();
            }
        }).start());

        // 主线程等待所有子线程执行完成
        latch.await();
        logger.info("主线程执行完成");

    }
}
