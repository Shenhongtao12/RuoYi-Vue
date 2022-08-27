package nio;

import java.io.IOException;
import java.io.OutputStream;
import java.net.Socket;

/**
 * @author as2i
 * @date 2022/8/24 13:42
 */
public class NIOClient {
    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            new Thread(() -> {
                Socket socket = null;
                try {
                    socket = new Socket("127.0.0.1", 8888);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                OutputStream out = null;
                try {
                    out = socket.getOutputStream();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
                String s = "hello world " + finalI;
                try {
                    out.write(s.getBytes());
                    out.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }).start();
        }

    }
}
