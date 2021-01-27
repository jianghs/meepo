package me.jianghs.meepo;

import me.jianghs.meepo.start.Printer;
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

    @DisplayName("继承Thread类")
    @Test
    void test1() {
        SleepRunner sleepRunner = new SleepRunner();
        Thread thread1 = new Thread(sleepRunner, "线程1");
        Thread thread2 = new Thread(sleepRunner, "线程2");
        thread1.start();
        thread2.start();
    }
}
