package me.jianghs.meepo;

import me.jianghs.meepo.thread.InterruptRunner;
import me.jianghs.meepo.thread.InterruptSleepRunner;
import me.jianghs.meepo.thread.JoinRunner;
import me.jianghs.meepo.thread.SleepRunner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @className: ThreadTest
 * @description:
 * @author: jianghs430
 * @createDate: 2021/1/27 9:38
 * @version: 1.0
 */
@DisplayName("Thread类测试案例")
public class ThreadTest {
    private static final Logger log = LoggerFactory.getLogger(ThreadTest.class);

    @DisplayName("sleep")
    @Test
    void test1() throws InterruptedException {
        SleepRunner sleepRunner = new SleepRunner();
        Thread thread1 = new Thread(sleepRunner, "线程1");
        Thread thread2 = new Thread(sleepRunner, "线程2");
        thread1.start();
        thread2.start();

        Thread.sleep(20000);
        log.info("主线程处理结束");
    }

    @DisplayName("join")
    @Test
    void test2() throws InterruptedException {
        log.info("主线程处理开始");
        JoinRunner joinRunner = new JoinRunner();
        Thread thread1 = new Thread(joinRunner, "线程1");
        thread1.start();
        thread1.join();
        log.info("主线程处理结束");
        Thread.sleep(10000);
    }

    @DisplayName("interrupt-sleep")
    @Test
    void test3() throws InterruptedException {
        InterruptSleepRunner interruptRunner = new InterruptSleepRunner();
        Thread thread1 = new Thread(interruptRunner, "子线程");

//        thread1.interrupt();
        thread1.start();

        // 主线程休眠1s
        Thread.sleep(1000);
        thread1.interrupt();
        Thread.sleep(10000);
    }

    @DisplayName("isInterrupt-sleep")
    @Test
    void test4() throws InterruptedException {
        InterruptSleepRunner interruptRunner = new InterruptSleepRunner();
        Thread thread1 = new Thread(interruptRunner, "子线程");
        thread1.start();
        log.info("thread1的中断标记为{}", thread1.isInterrupted());

        // 主线程休眠1s
        Thread.sleep(1000);
        thread1.interrupt();
        Thread.sleep(1000);
        log.info("thread1的中断标记为{}", thread1.isInterrupted());
        Thread.sleep(10000);
    }

    @DisplayName("interrupt-no-sleep")
    @Test
    void test5() throws InterruptedException {
        InterruptRunner interruptRunner = new InterruptRunner();
        Thread thread1 = new Thread(interruptRunner, "子线程");
        thread1.setDaemon(true);
        thread1.start();
        log.info("thread1的中断标记为{}", thread1.isInterrupted());
        // 确保子线程启动
        Thread.sleep(100);
        thread1.interrupt();
        Thread.sleep(100);
        log.info("thread1的中断标记为{}", thread1.isInterrupted());
        Thread.sleep(2000);
    }


}
