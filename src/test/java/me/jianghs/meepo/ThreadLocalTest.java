package me.jianghs.meepo;

import me.jianghs.meepo.threadlocal.ThreadLocalRunner1;
import me.jianghs.meepo.threadlocal.ThreadLocalRunner2;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @className: ThreadLocalTest
 * @description:
 * @author: jianghs430
 * @createDate: 2021/2/4 13:42
 * @version: 1.0
 */
@DisplayName("ThreadLocal类测试案例")
public class ThreadLocalTest {
    Logger logger = LoggerFactory.getLogger(ThreadLocalTest.class);

    @DisplayName("ThreadLocal")
    @Test
    void test1() throws InterruptedException {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        threadLocal.set("main");
        Thread thread1 = new Thread(new ThreadLocalRunner1(threadLocal, false));
        Thread thread2 = new Thread(new ThreadLocalRunner2(threadLocal, true));
        thread1.start();
        thread2.start();

        Thread.sleep(3000);
        logger.info(threadLocal.get());
    }
}
