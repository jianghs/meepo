package me.jianghs.meepo;

import me.jianghs.meepo.volatiletest.MachineRunner;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

/**
 * @className: VolatileTest
 * @description:
 * @author: jianghs430
 * @createDate: 2021/1/29 16:56
 * @version: 1.0
 */
@DisplayName("volatile关键字测试案例")
public class VolatileTest {

    @DisplayName("可见性测试")
    @Test
    public void test1() throws InterruptedException {
        MachineRunner target = new MachineRunner();
        Thread thread = new Thread(target, "A");
        thread.start();

//        Thread.sleep(1000);

        // 这里虽然设置为true，但是不一定会立刻从thread1的工作内存写入到主内存
        target.setStop(true);
//        Thread thread1 = new Thread(target, "B");
//        thread1.start();

    }
}
