package me.jianghs.meepo;

import me.jianghs.meepo.lock.*;
import me.jianghs.meepo.start.Printer;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @className: LockTest
 * @description:
 * @author: jianghs430
 * @createDate: 2021/2/4 15:29
 * @version: 1.0
 */
@DisplayName("Lock测试案例")
public class LockTest {
    private static final Logger log = LoggerFactory.getLogger(LockTest.class);

    @DisplayName("Lock加锁")
    @Test
    void test1() {
        List<Thread> threads = new ArrayList<>();
        Lock lock = new ReentrantLock();
        for (int i = 0; i < 5; i++) {
            String name = "线程" + i;
            Thread thread = new Thread(new ThreadA(lock), name);
            threads.add(thread);
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }

    @DisplayName("Lock-tryLock加锁")
    @Test
    void test2() {
        List<Thread> threads = new ArrayList<>();
        Lock lock = new ReentrantLock();
        for (int i = 0; i < 5; i++) {
            String name = "线程" + i;
            Thread thread = new Thread(new ThreadB(lock), name);
            threads.add(thread);
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }

    @DisplayName("Lock-lockInterruptibly加锁")
    @Test
    void test3() throws InterruptedException {
        Lock lock = new ReentrantLock();
        Thread thread1 = new Thread(new ThreadC(lock), "线程A");
        Thread thread2 = new Thread(new ThreadC(lock), "线程B");
        thread1.start();
        Thread.sleep(100);
        thread2.start();

        Thread.sleep(100);
        thread2.interrupt();
    }

    @DisplayName("Lock-condition 等待通知")
    @Test
    void test4() throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        Thread thread1 = new Thread(new ThreadLockConditionA(lock, condition), "线程A");
        Thread thread2 = new Thread(new ThreadLockConditionB(lock, condition), "线程B");
        thread1.start();
        Thread.sleep(1000);
        thread2.start();
    }

    @DisplayName("Lock-condition 生产者消费者")
    @Test
    void test5() throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        List<Object> list = new ArrayList<>();
        Thread thread1 = new Thread(new LockProducer(list, lock, condition), "生产者");
        Thread thread2 = new Thread(new LockConsumer(list, lock, condition), "消费者");
        thread1.start();
        thread2.start();
        Thread.sleep(1000);
    }

    @DisplayName("Lock-condition 生产者消费者2")
    @Test
    void test6() throws InterruptedException {
        Lock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        List<Object> list = new ArrayList<>();
        Thread thread1 = new Thread(new LockProducer(list, lock, condition), "生产者1");
        Thread thread2 = new Thread(new LockProducer(list, lock, condition), "生产者2");
        Thread thread3 = new Thread(new LockConsumer(list, lock, condition), "消费者1");
        Thread thread4 = new Thread(new LockConsumer(list, lock, condition), "消费者2");
        thread1.start();
        thread2.start();
        thread3.start();
        thread4.start();
        Thread.sleep(1000);
    }
}
