package me.jianghs.meepo.thread;

/**
 * @className: SleepMain
 * @description:
 * @author: jianghs430
 * @createDate: 2021/1/27 13:41
 * @version: 1.0
 */
public class SleepMain {
    public static void main(String[] args) {
        SleepRunner sleepRunner = new SleepRunner();
        Thread thread1 = new Thread(sleepRunner, "线程1");
        Thread thread2 = new Thread(sleepRunner, "线程2");
        thread1.start();
        thread2.start();
    }
}
