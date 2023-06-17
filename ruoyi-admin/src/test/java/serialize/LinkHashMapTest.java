package serialize;

import com.alibaba.druid.util.LRUCache;
import org.junit.jupiter.api.Test;

/**
 * @author Aaron.H.Shen
 * @date 2023/6/14 15:27
 */
public class LinkHashMapTest {

	@Test
	void test() {
		LRUCache<String, Object> lruCache = new LRUCache<String, Object>(10);
		lruCache.put("1", 1);
		lruCache.put("2", 2);
		lruCache.put("3", 3);
		lruCache.put("4", 4);
		lruCache.put("5", 5);
		lruCache.put("6", 6);
		lruCache.put("7", 7);
		lruCache.put("8", 8);
		lruCache.put("9", 9);
		lruCache.put("10", 10);
		lruCache.put("11", 11);


		new Thread(() -> {
			lruCache.put("9", 111000);
			System.out.println("111：" + lruCache);
		}).start();

		new Thread(() -> {
			lruCache.put("9", 666666);
			System.out.println("222：" + lruCache);
		}).start();

		new Thread(() -> {
			lruCache.put("9", 123);
			System.out.println("333：" + lruCache);
		}).start();

		new Thread(() -> {
			lruCache.put("9", 444);
			System.out.println("444：" + lruCache);
		}).start();

		System.out.println(lruCache);
	}
}
