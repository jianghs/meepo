package me.jianghs.meepo;

import me.jianghs.meepo.start.Printer;
import me.jianghs.meepo.start.PrinterCallable;
import me.jianghs.meepo.start.PrinterRunnable;
import me.jianghs.meepo.threadcommunication.waitandnotify.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @className: StartTest
 * @description:
 * @author: jianghs430
 * @createDate: 2021/1/26 15:29
 * @version: 1.0
 */
@DisplayName("waitAndNotify测试案例")
public class WaitAndNotifyTest {
    private static final Logger log = LoggerFactory.getLogger(WaitAndNotifyTest.class);

    @DisplayName("waitAndNotify")
    @Test
    void test1() throws InterruptedException {
        Object lock = new Object();

        Thread threadA = new Thread(new ThreadA(lock));
        Thread threadB = new Thread(new ThreadB(lock));
        threadA.start();

        Thread.sleep(1000);
        threadB.start();
    }

    @DisplayName("waitAndNotify2")
    @Test
    void test2() throws InterruptedException {
        Object lock = new Object();

        Thread threadA1 = new Thread(new ThreadA(lock));
        Thread threadA2 = new Thread(new ThreadA(lock));
        Thread threadA3 = new Thread(new ThreadA(lock));
        threadA1.start();
        threadA2.start();
        threadA3.start();

        // 只会随机唤醒一个
        Thread.sleep(1000);
        Thread threadB = new Thread(new ThreadB(lock));
        threadB.start();
    }

    @DisplayName("waitAndNotify3")
    @Test
    void test22() throws InterruptedException {
        Object lock = new Object();

        Thread threadA = new Thread(new ThreadA(lock));
        threadA.start();

        Thread.sleep(5000);
//        Thread threadB = new Thread(new ThreadB(lock));
//        threadB.start();
    }

    @DisplayName("waitAndNotifyAll")
    @Test
    void test3() throws InterruptedException {
        Object lock = new Object();

        Thread threadA1 = new Thread(new ThreadA(lock));
        Thread threadA2 = new Thread(new ThreadA(lock));
        Thread threadA3 = new Thread(new ThreadA(lock));
        threadA1.start();
        threadA2.start();
        threadA3.start();

        Thread.sleep(1000);
        Thread threadC = new Thread(new ThreadC(lock));
        threadC.start();
    }

    @DisplayName("1生产：1消费")
    @Test
    void test4() throws InterruptedException {
        List<Object> objects = new ArrayList<>();
        Thread producer = new Thread(new Producer(objects));
        Thread consumer = new Thread(new Consumer(objects));
        producer.start();
        consumer.start();

        Thread.sleep(5000);
    }

    @DisplayName("n生产：n消费")
    @Test
    void test5() throws InterruptedException {
        List<Object> objects = new ArrayList<>();
        Thread producer1 = new Thread(new Producer(objects), "生产者1");
        Thread producer2 = new Thread(new Producer(objects), "生产者2");
        Thread consumer1 = new Thread(new Consumer(objects), "消费者1");
        Thread consumer2 = new Thread(new Consumer(objects), "消费者2");

        producer1.start();
        producer2.start();
        consumer1.start();
        consumer2.start();
        Thread.sleep(100);
    }

    @DisplayName("三个线程按顺序输出")
    @Test
    void test6() throws InterruptedException {
        List<Object> list = new ArrayList<>();
        Thread thread1 = new Thread(new Thread1(list), "线程1");
        Thread thread2 = new Thread(new Thread2(list), "线程2");
        Thread thread3 = new Thread(new Thread3(list), "线程3");

        thread1.start();
        thread2.start();
        thread3.start();
        Thread.sleep(2000);
    }
}
