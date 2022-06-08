import com.ruoyi.common.core.domain.entity.SysUser;
import org.junit.jupiter.api.Test;

/**
 * @author as2i
 * @date 5/28/2022 1:24 PM
 */
public class MyTest {

    @Test
    void test() {
        Double i1 = 100.0;
        Double i2 = 100.0;
        Double i3 = 200.0;
        Double i4 = 200.0;

        System.out.println(i1 == i2 );
        System.out.println(i3.equals(i4));


        byte a = 127;
        byte b = 127;
        // 报编译错误:cannot convert from int to byte
        // b = a + b;
        b += a;


    }
}
