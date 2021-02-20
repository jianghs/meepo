package me.jianghs.meepo.并发包;

import java.util.concurrent.atomic.AtomicBoolean;

/**
 * @className: TryLock
 * @description:
 * @author: jianghs430
 * @createDate: 2021/2/19 9:01
 * @version: 1.0
 */
public class TryLock {

    private final AtomicBoolean ab = new AtomicBoolean(false);
    
    private final ThreadLocal<Boolean> threadLocal = ThreadLocal.withInitial(() -> false);

    public boolean tryLock() {
        boolean result = ab.compareAndSet(false, true);
        if (result) {
            threadLocal.set(true);
        }
        return result;
    }

    public boolean release() {
        if (threadLocal.get()) {
            threadLocal.set(false);
            return ab.compareAndSet(true, false);
        } else {
            return false;
        }
    }
}
