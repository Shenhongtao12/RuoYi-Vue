package io;

import entity.Person;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * @author as2i
 * @date 2022/8/24 10:52
 */
public class SerializableTest {

    @Test
    public void test() throws Exception {
        //1. 将存有多个自定义对象的集合序列化操作，保存到 list.txt 文件中。

        ArrayList<Person> list = new ArrayList<>();
        list.add(new Person("张三",20));
        list.add(new Person("李四",21));
        list.add(new Person("王五",23));
        //把集合对象序列化到list.txt文件中
        Path path = Paths.get("D:\\test\\list.txt");
        ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(path));
        oos.writeObject(list);
        //2. 反序列化 list.txt ，并遍历集合，打印对象信息。
        ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(path));
        Object o = ois.readObject();
        ArrayList<Person> list1 = (ArrayList<Person>)o;
        for (Person person : list) {
            System.out.println(person);
        }

    }
}
