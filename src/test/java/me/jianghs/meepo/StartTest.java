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

        THREAD_COUNT = 50;
    }

    @DisplayName("继承Thread类")
    @Test
    void test1() {
        for (int i = 0; i < THREAD_COUNT; i++) {
            String name = "线程" + i;
            log.info("========={}创建=========", name);
            Thread thread = new Printer(name);
            thread.start();
        }
    }

    @DisplayName("实现Runnable接口")
    @Test
    void test2() {
        for (int i = 0; i < THREAD_COUNT; i++) {
            String name = "线程" + i;
            log.info("========={}创建=========", name);
            Thread printer = new Thread(new PrinterRunnable(), name);
            printer.start();
        }
    }

    @DisplayName("实现Callable接口")
    @Test
    void test3() throws ExecutionException, InterruptedException, TimeoutException {
        List<FutureTask<Integer>> list = new ArrayList<>();
        for (int i = 0; i < THREAD_COUNT; i++) {
            String name = "线程" + i;
            log.info("========={}创建=========", name);
            Callable<Integer> printCallable = new PrinterCallable();
            FutureTask<Integer> futureTask = new FutureTask<>(printCallable);
            Thread thread = new Thread(futureTask, name);
            thread.start();
            list.add(futureTask);
            // get 默认会阻塞线程
//            log.info("{}执行后的返回值：{}", name ,futureTask.get());

            // 如果线程的执行时间超过设置的超时时间，则会异常
//            log.info("{}执行后的返回值：{}", name ,futureTask.get(100, TimeUnit.MILLISECONDS));
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
        for(int i = 0; i < THREAD_COUNT; i++){
            PrinterRunnable demo = new PrinterRunnable();
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


        //为线程池中的线程分配任务,使用submit方法，传入的参数可以是Runnable的实现类，也可以是Callable的实现类
        for(int i = 0; i < THREAD_COUNT; i++){
            PrinterCallable demo = new PrinterCallable();
            FutureTask<Integer> futureTask = new FutureTask<>(demo);
            pool.submit(futureTask);
        }

        //关闭线程池
        //shutdown ： 以一种平和的方式关闭线程池，在关闭线程池之前，会等待线程池中的所有的任务都结束，不在接受新任务
        //shutdownNow ： 立即关闭线程池
        pool.shutdown();
    }
}
