import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author as2i
 * @date 7/22/2022 5:30 PM
 */
public class ThreadTest {

    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Test
    public void threadTest() {
        Object lock = new Object();

        Thread thread1 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 0; i <= 100; i = i + 2) {
                    logger.info(i + "");
                    try {
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }

        }, "线程1");

        Thread thread2 = new Thread(() -> {
            synchronized (lock) {
                for (int i = 1; i < 100; i = i + 2) {
                    logger.info(i + "");
                    try {
                        lock.notify();
                        lock.wait();
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }
            }
        }, "线程2");

        thread1.start();
        thread2.start();
    }
}
