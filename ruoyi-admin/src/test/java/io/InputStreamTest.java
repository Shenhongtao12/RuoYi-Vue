package io;

import org.junit.jupiter.api.Test;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

/**
 * @author as2i
 * @date 2022/8/23 11:19
 */
public class InputStreamTest {
    /**
     * 一次性全部读取
     */
    @Test
    public void inputTest() {
        FileInputStream file = null;
        try {
            file = new FileInputStream("D:\\test\\memo.txt");
            byte data[] = new byte[1024];

            int len = file.read(data);

            System.out.println(new String(data, 0, len));
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                file.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    /**
     * 单个字节读取
     */
    @Test
    public void inputTest2() {
        FileInputStream file = null;
        try {
            file = new FileInputStream("D:\\test\\memo.txt");
            byte data[] = new byte[1024];
            int foot = 0; // 操作data数组的脚标
            int temp = 0;

            // 第一步：temp = input.read()，读取一个单个字节，并且将内容给temp变量
            // 第二步：temp != -1，将接收到的temp的数值判断是否为-1，如果为-1则表示退出循环，如果不是，则保存数据
            while ((temp = file.read()) != -1) {
                data[foot] = (byte) temp; // 保存读取进来的单个字节
                foot++;
            }

            System.out.println(new String(data, 0, foot));
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                file.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Test
    public void outputTest() {
        OutputStream outputStream = null;
        try {
            File file = new File("D:\\test\\memo1.txt");
            if (file.getParentFile().exists()) {
                boolean a = file.getParentFile().mkdirs();
            }

            outputStream = Files.newOutputStream(file.toPath());

            String data = "hello world !\\r\\n hello world !\\r\\n hello world !\\r\\n hello world !";
            outputStream.write(data.getBytes(StandardCharsets.UTF_8));
        } catch (Exception e) {
            throw new RuntimeException(e);
        } finally {
            try {
                outputStream.close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
