package me.jianghs.meepo;

import me.jianghs.meepo.start.Printer;
import me.jianghs.meepo.thread.JoinMainRunner;
import me.jianghs.meepo.thread.JoinRunner;
import me.jianghs.meepo.thread.SleepRunner;
import org.junit.jupiter.api.BeforeAll;
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
        JoinRunner joinRunner = new JoinRunner();
        Thread thread1 = new Thread(joinRunner, "线程1");
        Thread thread2 = new Thread(joinRunner, "线程2");

        Thread mainThread = new Thread(new JoinMainRunner(thread1), "主线程");
        mainThread.start();

        // 主线程启动
        thread1.start();
        // 暂停1s
        Thread.sleep(1000);
        thread2.start();
        thread2.join();

//        thread1.start();
//        thread2.start();

        Thread.sleep(60000);
        log.info("主线程处理结束");
    }
}
