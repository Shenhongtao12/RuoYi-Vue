package lock8;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 读-读：可以共享
 * 读-写：不可以共享
 * 写-写：不可以共享
 * @author as2i
 * @date 2022/7/27 17:58
 */
public class TestReadWriteLock {
    public static void main(String[] args) {
        MyCacheLock myCache = new MyCacheLock();
        //写线程
        /*for (int i = 1; i <= 5; i++) {
            final int temp = i;
            new Thread(()->{
                myCache.put(temp+"",temp+"");
            },String.valueOf(i)).start();
        }*/
        for (int i = 1; i <= 10; i++) {
            final int temp = i;
            new Thread(()->{
                myCache.get(temp+"");
            },String.valueOf(i)).start();
        }
    }
}
class MyCacheLock{
    //volatile：关键字
    public volatile Map<String,Object> map = new HashMap<String,Object>();
    /*
     * 为了提高效率，只允许有一个线程来写，但允许多个线程来读
     * */
    //读写锁：（并不是普通的Lock锁），是一种更加“细粒度”的锁
    private ReentrantReadWriteLock reentrantReadWriteLock = new ReentrantReadWriteLock();
    //存--》写的过程
    public void put(String key,Object value){
        reentrantReadWriteLock.writeLock().lock();
        System.out.println("put: " + Thread.currentThread().getName());
        try {
            if (map.get("1") != null) {
                return;
            }
            System.out.println(Thread.currentThread().getName()+"写入"+key);
            map.put(key,value);
            System.out.println(Thread.currentThread().getName()+"写入成功");
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reentrantReadWriteLock.writeLock().unlock();
        }
    }
    //去--》读的过程
    public void get(String key){
        if (map.get("1") == null) {
            put("1", 123);
        }
        reentrantReadWriteLock.readLock().lock();
        try {
            TimeUnit.MILLISECONDS.sleep(Math.round((Math.random()* 1000)));
            System.out.println(Thread.currentThread().getName()+"读出"+key);
            TimeUnit.MILLISECONDS.sleep(Math.round((Math.random()* 2000)));
            System.out.println(Thread.currentThread().getName()+"读出成功" + map.get("1"));
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reentrantReadWriteLock.readLock().unlock();
        }
    }

    public void asyncPut(String key,Object value) {
        new Thread(() -> {
            System.out.println("====================");
            this.put(key, value);
        });
    }
}
//自定义缓存
class MyCache{
    //volatile：关键字
    public volatile Map<String,Object> map = new HashMap<String,Object>();
    /*
     * 为了提高效率，只允许有一个线程来写，但允许多个线程来读
     * */
    //存--》写的过程
    public void put(String key,Object value){
        System.out.println(Thread.currentThread().getName()+"写入"+key);
        map.put(key,value);
        System.out.println(Thread.currentThread().getName()+"写入成功");
    }
    //去--》读的过程
    public void get(String key){
        System.out.println(Thread.currentThread().getName()+"读入"+key);
        map.get(key);
        System.out.println(Thread.currentThread().getName()+"读入成功");
    }
}
