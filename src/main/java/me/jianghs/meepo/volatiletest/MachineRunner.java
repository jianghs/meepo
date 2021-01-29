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
public class MachineRunner implements Runnable {

    private Logger logger = LoggerFactory.getLogger(MachineRunner.class);
    private boolean stop = false;

    public void setStop(boolean stop) {
        this.stop = stop;
    }

    @Override
    public void run() {
        while (!stop) {
            // 空处理
        }
    }
}
