package serialize;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

/**
 * @author as2i
 * @date 2023/1/5 13:30
 */
public class HashMapTest {

    @Test
    public void hashCodeTest() {
        String name = "张三";
        System.out.println(name.hashCode());

        Person person = new Person("李四", "xxxxxxeeee");
        System.out.println(person.hashCode());

    }
    class Person {
        String name;
        String address;

        public Person(String name, String address) {
            this.name = name;
            this.address = address;
        }

        @Override
        public int hashCode() {
            return super.hashCode();
        }
    }
}
