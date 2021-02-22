package me.jianghs.meepo.并发包;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

/**
 * @className: Semaphore
 * @description:
 * @author: jianghs430
 * @createDate: 2021/2/20 17:27
 * @version: 1.0
 */
public class SemaphoreExample {

    private static Logger logger = LoggerFactory.getLogger(SemaphoreExample.class);


    public static void main(String[] args) {
        int MAX_PERMIT_LOGIN_ACCOUNT = 10;
        LoginService loginService = new LoginService(MAX_PERMIT_LOGIN_ACCOUNT);

        IntStream.range(0, 20).forEach(i -> new Thread(() -> {
            boolean login = loginService.login();
            if (!login) {
                logger.info("登录失败");
                return;
            }
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                loginService.logout();
            }
        }).start());
    }

    private static class LoginService {
        private static Logger logger = LoggerFactory.getLogger(LoginService.class);

        private final Semaphore semaphore;

        private LoginService(int maxPermitLoginAccount) {
            this.semaphore = new Semaphore(maxPermitLoginAccount, true);
        }

        public boolean login() {
            try {
                this.semaphore.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
                return false;
            }
            logger.info("login success");
            return true;
        }

        public void logout() {
            this.semaphore.release();
            logger.info("login out");
        }
    }
}
