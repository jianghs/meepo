package me.jianghs.meepo.volatiletest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @className: a
 * @description:
 * @author: jianghs430
 * @createDate: 2021/1/28 18:01
 * @version: 1.0
 */
public class MachineMain {
    volatile static boolean flag = false;
    private static Logger logger = LoggerFactory.getLogger(MachineMain.class);

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while(!flag){
            };
        }).start();
        Thread.sleep(100);
        flag = true;
        System.out.println("主线程运行完毕");
    }

}
