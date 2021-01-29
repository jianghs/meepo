package me.jianghs.meepo;

import me.jianghs.meepo.synchronizedtest.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @className: SynchronizedTest
 * @description:
 * @author: jianghs430
 * @createDate: 2021/1/29 13:26
 * @version: 1.0
 */
@DisplayName("synchronized关键字测试案例")
public class SynchronizedTest {
    Logger logger = LoggerFactory.getLogger(SynchronizedTest.class);

    @DisplayName("线程安全问题验证")
    @Test
    public void test1() {
        Runnable cal = new FeeCalculator(100);
        Thread t1 = new Thread(cal);
        Thread t2 = new Thread(cal);
        t1.start();
        t2.start();
        // 输出结果可能为 20 4,4 20, 4 4
    }

    @DisplayName("同步方法")
    @Test
    public void test2() throws InterruptedException {
        Looper looper = new Looper();
        /**
         * 期望A和B互斥 A和C B和C不互斥
         * A在i=5时抛出异常，B获得锁后执行
         */
        Thread t1 = new Thread(looper, "A");
        Thread t2 = new Thread(looper, "B");
        Thread t3 = new Thread(looper, "C");
        t1.start();
        t2.start();
        t3.start();

        Thread.sleep(20000);

        /**
         * 两个线程访问两个对象的同一个方法，不会互斥
         */
        logger.info("=====================开始第二段====================");
        Thread thread1 = new Thread(new Looper(), "B");
        Thread thread2 = new Thread(new Looper(), "B");
        thread1.start();
        thread2.start();

        Thread.sleep(20000);
    }

    @DisplayName("同步语句块-this vs x")
    @Test
    public void test3() throws InterruptedException {
        Lock lock = new Lock();
        BlockLooper looper = new BlockLooper(lock);

        /**
         * A B同步， A C异步 B C异步
         */
        Thread t1 = new Thread(looper, "A");
        Thread t2 = new Thread(looper, "B");
        Thread t3 = new Thread(looper, "C");

        t1.start();
        t2.start();
        t3.start();


        Thread.sleep(40000);
    }

    @DisplayName("同步语句块-this vs x")
    @Test
    public void test4() throws InterruptedException {
        Lock lock = new Lock();
        BlockLooper looper = new BlockLooper(lock);

        /**
         * C 和 LockA 同步
         * C 和 LockB 同步
         */
        Thread t3 = new Thread(looper, "C");


//        Thread thread1 = new Thread(lock, "LOCK-A");
        Thread thread2 = new Thread(lock, "LOCK-B");
        t3.start();
//        thread1.start();
        thread2.start();

        Thread.sleep(20000);
    }

    @DisplayName("同步静态方法")
    @Test
    public void test5() throws Exception {
        StaticLooper looper = new StaticLooper();

        /**
         * A B同步， A C异步 B C异步
         */
        Thread t1 = new Thread(looper, "A");
        Thread t2 = new Thread(looper, "B");
        Thread t3 = new Thread(looper, "C");

        t1.start();
        t2.start();
        t3.start();


        Thread.sleep(40000);
    }
}
