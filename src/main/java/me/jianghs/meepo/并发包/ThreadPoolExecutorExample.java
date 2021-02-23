package me.jianghs.meepo.并发包;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @className: ThreadPoolExecutorExample
 * @description:
 * @author: jianghs430
 * @createDate: 2021/2/22 17:06
 * @version: 1.0
 */
public class ThreadPoolExecutorExample {

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int corePoolSize = 5;
        int maximumPoolSize = 10;
        int keepAliveTime = 600;
        Executor executor = new ThreadPoolExecutor(corePoolSize,
                maximumPoolSize,
                keepAliveTime,
                TimeUnit.SECONDS,
                new ArrayBlockingQueue<>(10),
                new MyThreadFactory(),
                new ThreadPoolExecutor.DiscardPolicy());

        for(int i = 0; i < 5; i++) {
            executor.execute(() -> {
                System.out.println("run task" + Thread.currentThread());
            });
        }

//        Future<String> future = threadPoolExecutor.submit(() -> "run task 123");
//
//        System.out.println(future.get());
    }

    private static class MyThreadFactory implements ThreadFactory {
        private final static String PREFIX = "DD";
        private final static AtomicInteger INC = new AtomicInteger();
        @Override
        public Thread newThread(Runnable r) {
            ThreadGroup threadGroup = new ThreadGroup("MyPool");
            Thread thread = new Thread(threadGroup, r, PREFIX + "_" + INC.getAndIncrement());
            thread.setPriority(10);
            return thread;
        }
    }
}
