package lock8;

import java.util.concurrent.TimeUnit;

/**
 * * 8锁现象：其实就是8个关于锁的问题
 * *   1、标准情况下，是先打印“发短信”还是“打电话”--（发短息  打电话）
 * *   2、sendMessage延迟4秒，是先打印“发短信”还是“打电话”--（发短息  打电话）
 * *   上述出现两种情况的原因：synchronized;
 * *
 *
 * @author as2i
 * @date 2022/7/27 17:34
 */
public class Test01 {
    public static void main(String[] args) {
        Phone phone = new Phone();

        new Thread(() -> {
            phone.sendMessage();
        }, "A").start();

        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        new Thread(() -> {
            phone.call();
        }, "B").start();
    }
}

class Phone {
    //synchronized：锁的对象是方法调用者--phone对象
    //两个方法共用同一把锁，谁先拿到谁执行
    public synchronized void sendMessage() {
        try {
            TimeUnit.SECONDS.sleep(4);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("发短信");
    }

    public synchronized void call() {
        System.out.println("打电话");
    }

}
