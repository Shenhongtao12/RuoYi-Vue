package com.ruoyi.web.controller;

import com.ruoyi.common.utils.http.HttpUtils;
import com.ruoyi.common.utils.uuid.UUID;
import org.apache.commons.io.FileUtils;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.net.URL;
import java.net.URLConnection;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import javax.net.ssl.HttpsURLConnection;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

/**
 * @author as2i
 * @date 2022/10/19 9:28
 */
@RestController
@RequestMapping("api-test")
public class NeweggController {

    @GetMapping("getImage")
    public void getNetworkImage(@RequestParam(name = "imageUrl", defaultValue = "") String imageUrl, HttpServletResponse response) throws Exception {
        HttpsURLConnection urlConnection = HttpUtils.sendSSL(imageUrl);
        InputStream fis = urlConnection.getInputStream();
        byte[] buffer = new byte[1024];
        int r = 0;
        ServletOutputStream outputStream = response.getOutputStream();
        String fileName = UUID.fastUUID() + imageUrl.substring(imageUrl.lastIndexOf("."));
        FileOutputStream fileOutputStream = new FileOutputStream("D:\\test\\images\\" + fileName);

        long start = System.currentTimeMillis();
        while ((r = fis.read(buffer)) != -1) {
            outputStream.write(buffer, 0, r);
            fileOutputStream.write(buffer, 0, r);
        }
        System.out.println("耗时：" + (System.currentTimeMillis() - start));

        fis.close();
        outputStream.close();
        fileOutputStream.close();
    }

    @GetMapping("getImageByBuffer")
    public void getNetworkImageBuffer(@RequestParam(name = "imageUrl", defaultValue = "") String imageUrl, HttpServletResponse response) throws Exception {
        HttpsURLConnection urlConnection = HttpUtils.sendSSL(imageUrl);
        BufferedInputStream fis = new BufferedInputStream(urlConnection.getInputStream());

        byte[] buffer = new byte[1024];
        int r = 0;
        ServletOutputStream outputStream = response.getOutputStream();
        String fileName = UUID.fastUUID() + imageUrl.substring(imageUrl.lastIndexOf("."));

        FileOutputStream fileOutputStream = new FileOutputStream("D:\\test\\images\\" + fileName);
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(fileOutputStream);
        long start = System.currentTimeMillis();
        while ((r = fis.read(buffer)) != -1) {
            outputStream.write(buffer, 0, r);
            bufferedOutputStream.write(buffer, 0, r);
        }

        System.out.println("耗时：" + (System.currentTimeMillis() - start));

        fis.close();
        outputStream.close();
        fileOutputStream.close();
    }
}
