package me.jianghs.meepo;

import me.jianghs.meepo.start.Printer;
import me.jianghs.meepo.start.PrinterCallable;
import me.jianghs.meepo.start.PrinterRunnable;
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
@DisplayName("多线程启动测试案例")
public class StartTest {
    private static final Logger log = LoggerFactory.getLogger(StartTest.class);

    private static int THREAD_COUNT = 0;
    @BeforeAll
    public static void init() {
        log.info("初始化线程数量");

        THREAD_COUNT = 10;
    }

    @DisplayName("继承Thread类")
    @Test
    void test1() {
        List<Thread> threads = new ArrayList<>();
        // 使用了对象锁,只能new一次
        Printer printer = new Printer();
        for (int i = 0; i < THREAD_COUNT; i++) {
            String name = "线程" + i;
//            log.info("========={}创建=========", name);
            Thread thread = new Thread(printer, name);
            threads.add(thread);
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }

    @DisplayName("实现Runnable接口")
    @Test
    void test2() {
        List<Thread> threads = new ArrayList<>();
        // 使用了对象锁,只能new一次
        PrinterRunnable printerRunnable = new PrinterRunnable();
        for (int i = 0; i < THREAD_COUNT; i++) {
            String name = "线程" + i;
            Thread printer = new Thread(printerRunnable, name);
            threads.add(printer);
        }
        for (Thread thread : threads) {
            thread.start();
        }
    }

    @DisplayName("实现Callable接口")
    @Test
    void test3() throws ExecutionException, InterruptedException {
        List<FutureTask<Integer>> list = new ArrayList<>();
        List<Thread> threads = new ArrayList<>();
        // 使用了对象锁,只能new一次
        Callable<Integer> printCallable = new PrinterCallable();
        for (int i = 0; i < THREAD_COUNT; i++) {
            String name = "线程" + i;
            FutureTask<Integer> futureTask = new FutureTask<>(printCallable);
            Thread thread = new Thread(futureTask, name);
            threads.add(thread);
            list.add(futureTask);
        }
        // 启动线程
        for (Thread thread : threads) {
            thread.start();
        }
        for(FutureTask<Integer> futureTask : list) {
            log.info("执行后的返回值：{}", futureTask.get());
        }
    }

    @DisplayName("线程池-Runnable")
    @Test
    void test4() {
        //使用Executors工具类中的方法创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(THREAD_COUNT);
        //为线程池中的线程分配任务,使用submit方法，传入的参数可以是Runnable的实现类，也可以是Callable的实现类
        // 使用了对象锁,只能new一次
        PrinterRunnable demo = new PrinterRunnable();
        for(int i = 0; i < THREAD_COUNT; i++){
            pool.submit(demo);
        }

        //关闭线程池
        //shutdown ： 以一种平和的方式关闭线程池，在关闭线程池之前，会等待线程池中的所有的任务都结束，不在接受新任务
        //shutdownNow ： 立即关闭线程池
        pool.shutdown();
    }

    @DisplayName("线程池-Callable")
    @Test
    void test5() {
        //使用Executors工具类中的方法创建线程池
        ExecutorService pool = Executors.newFixedThreadPool(THREAD_COUNT);
        // 使用了对象锁,只能new一次
        PrinterCallable demo = new PrinterCallable();
        //为线程池中的线程分配任务,使用submit方法，传入的参数可以是Runnable的实现类，也可以是Callable的实现类
        for(int i = 0; i < THREAD_COUNT; i++){
            FutureTask<Integer> futureTask = new FutureTask<>(demo);
            pool.submit(futureTask);
        }

        //关闭线程池
        //shutdown ： 以一种平和的方式关闭线程池，在关闭线程池之前，会等待线程池中的所有的任务都结束，不在接受新任务
        //shutdownNow ： 立即关闭线程池
        pool.shutdown();
    }
}
