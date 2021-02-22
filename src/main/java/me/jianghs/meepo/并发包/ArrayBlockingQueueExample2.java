package me.jianghs.meepo.并发包;

import org.openjdk.jmh.annotations.Benchmark;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @className: ArrayBlockingQueueExample
 * @description:
 * @author: jianghs430
 * @createDate: 2021/2/22 10:50
 * @version: 1.0
 */
public class ArrayBlockingQueueExample2 {
    private static Logger logger = LoggerFactory.getLogger(ArrayBlockingQueueExample2.class);

    public static void main(String[] args) {
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(10);
        // 启动11个生产者线程
        IntStream.range(0, 10).boxed().map(i -> new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    String data = String.valueOf(System.currentTimeMillis());
                    logger.info("生产者的值为{}", data);
                    arrayBlockingQueue.put(data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        })).forEach(Thread::start);

        // 启动11个消费者线程
        IntStream.range(0, 10).boxed().map(i -> new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                    String data = arrayBlockingQueue.take();
                    logger.info("消费者的值为{}", data);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    break;
                }
            }
        })).forEach(Thread::start);
    }
}
