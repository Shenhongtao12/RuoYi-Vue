package io;

import com.ruoyi.framework.web.domain.server.Sys;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.junit.jupiter.api.Test;

import java.io.*;
import java.net.URL;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * apache File Utils Test
 * @author as2i
 * @date 2022/10/27 16:49
 */
public class ApacheFileUtilsTest {

    @Test
    public void copyFile() {
        // 将文件copy到target目标处，需要提供文件名及后缀
        File sourceFile = new File("D:\\as2i\\installPackage\\apache-maven-3.6.3-bin.tar.gz");
        File targetFile = new File("D:\\test\\maven_copy.tar.gz");
        try {
            long start = System.currentTimeMillis();
            FileUtils.copyFile(sourceFile, targetFile);
            System.out.println("耗时：" + (System.currentTimeMillis() - start));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void copyDirectory() {
        // 将目录下的文件copy到目标目录下
        File sourceFile = new File("D:\\as2i\\桌面壁纸");
        File targetFile = new File("D:\\test\\images");
        try {
            FileUtils.copyDirectory(sourceFile, targetFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void copyURLToFile() {
        // 把URL的文件保存到本地
        File targetFile = new File("D:\\test\\images\\copyURLToFile.jpg");
        try {
            URL url = new URL("https://dfis-gdev.newegg.org/ProductImageOriginal/66-666-003-08.jpg");

            long start = System.currentTimeMillis();
            FileUtils.copyURLToFile(url, targetFile);
            System.out.println("耗时：" + (System.currentTimeMillis() - start));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void deleteFile() {
        // 删除本地文件
        File targetFile = new File("D:\\test\\maven_copy.tar.gz");
        try {
            long start = System.currentTimeMillis();
            FileUtils.delete(targetFile);
            System.out.println("耗时：" + (System.currentTimeMillis() - start));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void writeStringToFile() {
        // 文本写入本地文件
        String str = "https://c1.neweggimages.com/ProductImageCompressAll1280/83-151-042-V04.jpg\n" +
                "https://c1.neweggimages.com/ProductImageCompressAll1280/83-151-042-V82.jpg\n" +
                "https://c1.neweggimages.com/ProductImageCompressAll1280/83-151-042-V03.jpg\n" +
                "https://c1.neweggimages.com/ProductImageCompressAll1280/83-151-042-V05.jpg\n" +
                "https://c1.neweggimages.com/ProductImageCompressAll1280/24-012-050-01.jpg\n" +
                "https://c1.neweggimages.com/ProductImageCompressAll1280/24-012-050-03.jpg\n" +
                "https://c1.neweggimages.com/ProductImageCompressAll1280/24-012-050-04.jpg\n" +
                "https://c1.neweggimages.com/ProductImageCompressAll1280/24-012-050-07.jpg\n" +
                "天将降大任于斯人也，必先苦其心志，饿其体肤";
        File targetFile = new File("D:\\test\\imageUrlList.txt");
        try {
            long start = System.currentTimeMillis();
            FileUtils.write(targetFile, str, StandardCharsets.UTF_8);
            System.out.println("耗时：" + (System.currentTimeMillis() - start));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void ioUtilsTest() throws IOException {
        // IOUtils
        File targetFile = new File("D:\\test\\copyFile.zip");
        FileInputStream inputStream = new FileInputStream(targetFile);
        try {
            long start = System.currentTimeMillis();

            BufferedInputStream bufferedInputStream = IOUtils.buffer(inputStream);
            FileOutputStream outputStream = new FileOutputStream("D:\\test\\copyFile2222.zip");
            BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(outputStream);
            IOUtils.copy(bufferedInputStream, bufferedOutputStream);
            byte[] buffer = new byte[1024];
            IOUtils.write(buffer, bufferedOutputStream);
            System.out.println("耗时：" + (System.currentTimeMillis() - start));
        } finally {
            IOUtils.close(inputStream);
        }
    }
}
