import com.ruoyi.RuoYiApplication;
import com.ruoyi.common.core.domain.entity.SysUser;
import com.ruoyi.common.core.redis.RedisCache;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;

/**
 * @author as2i
 * @date 7/22/2022 4:14 PM
 */

@SpringBootTest(classes = RuoYiApplication.class)
public class ApplicationTest {

    @Autowired
    private RedisCache redisCache;

    @Test
    public void redisCatchListTest() {
        List<SysUser> userList = new LinkedList<>();

        userList.add(new SysUser(1L, "张三", "123@qq.com", "13300000000"));
        userList.add(new SysUser(2L, "lisi", "123@qq.com", "13300000001"));
        userList.add(new SysUser(3L, "王五", "123@qq.com", "13300000002"));
        userList.add(1, new SysUser(4L, "zhaoliu", "123@qq.com", "13300000003"));

        redisCache.setCacheList("userList", userList);

        List<SysUser> cacheList = redisCache.getCacheList("userList");
        System.out.println(cacheList);
    }

    @Test
    public void redisCatchMapTest() throws InterruptedException {
        Map<String, SysUser> userMap = new HashMap<>();
        userMap.put("1", new SysUser(1L, "张三", "123@qq.com", "13300000000"));
        userMap.put("2", new SysUser(2L, "lisi", "123@qq.com", "13300000001"));
        userMap.put("3", new SysUser(3L, "王五", "123@qq.com", "13300000002"));

        redisCache.setCacheMap("userMap", userMap);


        SysUser user = redisCache.getCacheMapValue("userMap", "2");
        System.out.println(user);

        redisCache.setCacheMapValue("userMap", "2", new SysUser(4L, "zhaoliu", "123@qq.com", "13300000003"));



        Map<String, SysUser> cacheMap = redisCache.getCacheMap("userMap");

        System.out.println(cacheMap);

        redisCache.expire("userMap", 5);
        Thread.sleep(8000);

        System.out.println("=========================================");
        System.out.println(redisCache.getCacheMap("userMap"));
    }

    @Test
    public void deleteRedisCatch() {
        redisCache.deleteObject("userList");
    }
}
