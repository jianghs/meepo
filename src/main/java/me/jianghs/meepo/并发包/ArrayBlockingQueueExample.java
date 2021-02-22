package me.jianghs.meepo.并发包;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ArrayBlockingQueue;

/**
 * @className: ArrayBlockingQueueExample
 * @description:
 * @author: jianghs430
 * @createDate: 2021/2/22 10:50
 * @version: 1.0
 */
public class ArrayBlockingQueueExample {
    private static Logger logger = LoggerFactory.getLogger(ArrayBlockingQueueExample.class);

    public static void main(String[] args) {
        ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(2);
        try {
            arrayBlockingQueue.put("1");
            arrayBlockingQueue.put("2");
            // 超过数量后，会进入阻塞状态
            arrayBlockingQueue.put("3");
        } catch (InterruptedException e) {
            logger.error("error:", e);
            e.printStackTrace();
        }


    }
}
