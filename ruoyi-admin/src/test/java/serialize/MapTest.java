package serialize;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author as2i
 * @date 2023/1/7 16:38
 */
public class MapTest {

    /**
     *  treeMap是一个根据key值排序的map
     */
    @Test
    public void treeMapTest() {
        TreeMap treeMap = new TreeMap();
        treeMap.put("ugjs", "张三");
        treeMap.put("ajgk", "李四");
        treeMap.put("virtk", "王五");
        treeMap.put("bowkt", "赵六");

        System.out.println(treeMap.get("virtk"));
        System.out.println(treeMap);
    }

    @Test
    public void hashMapTest() {
        Map hashMap = new HashMap();
        hashMap.put("ugjs", "张三");
        hashMap.put("ajgk", "李四");
        hashMap.put("virtk", "王五");
        hashMap.put("bowkt", "赵六");
        hashMap.put("awe", "赵六1");
        hashMap.put("eee", "赵六2");
        hashMap.put("bowtttkt", "赵六3");

        System.out.println(hashMap);
    }


    @Test
    public void linkedHashMapTest() {
        Map hashMap = new LinkedHashMap();
        hashMap.put("ugjs", "张三");
        hashMap.put("ajgk", "李四");
        hashMap.put("virtk", "王五");
        hashMap.put("bowkt", "赵六");
        hashMap.put("awe", "赵六1");
        hashMap.put("eee", "赵六2");
        hashMap.put("bowtttkt", "赵六3");

        System.out.println(hashMap);
    }
}
