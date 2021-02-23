package me.jianghs.meepo.并发包;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * @className: RecursiveTaskSum
 * @description:
 * @author: jianghs430
 * @createDate: 2021/2/23 10:23
 * @version: 1.0
 */
public class RecursiveTaskSum extends RecursiveTask<Long> {
    private static Logger logger = LoggerFactory.getLogger(RecursiveTaskSum.class);

    private final long[] numbers;

    private final int startIndex;

    private final int endIndex;

    private static final long MAX_HOLDS = 10000L;

    public RecursiveTaskSum(long[] numbers) {
        this(numbers, 0, numbers.length);
    }

    public RecursiveTaskSum(long[] numbers, int startIndex, int endIndex) {
        this.numbers = numbers;
        this.startIndex = startIndex;
        this.endIndex = endIndex;
    }

    @Override
    protected Long compute() {
        int length = endIndex - startIndex;
        if (length <= MAX_HOLDS) {
            long result = 0L;
            for (int i = 0; i < length; i++) {
                result += numbers[i];
            }
            return result;
        }

        // 拆分任务
        int tempEndIndex = startIndex + length / 2;
        RecursiveTaskSum firstTask = new RecursiveTaskSum(numbers, startIndex, tempEndIndex);
        firstTask.fork();
        RecursiveTaskSum secondTask = new RecursiveTaskSum(numbers, tempEndIndex, endIndex);
        secondTask.fork();

        Long firstResult = firstTask.join();
        Long secondResult = secondTask.join();

        return firstResult + secondResult;
    }

    public static void main(String[] args) {
        long[] numbers = LongStream.rangeClosed(1, 9_000_000).toArray();
        RecursiveTaskSum recursiveTaskSum = new RecursiveTaskSum(numbers);
        Long sum = ForkJoinPool.commonPool().invoke(recursiveTaskSum);
        logger.info("最终结果{}", sum);
    }
}
