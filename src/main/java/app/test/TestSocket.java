package app.test;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.net.SocketException;
import java.util.Arrays;

/**
 * @Author guc
 * @Date 2019/7/19 11:15
 * @Description TODO
 */
public class TestSocket {
    private static String HOST = "192.168.64.3";
    private static int PORT = 502;
    private static int TIMEOUT = 10 * 1000;
    private static int KEEP_ALIVE_PERIOD = 5 * 1000;

    public static void main(String[] args) {
        final Socket socket = new Socket();
        try {
            socket.setSoTimeout(TIMEOUT);
            socket.connect(new InetSocketAddress(HOST, PORT), TIMEOUT);

            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            int len;

            // 建立连接后获得输出流
            OutputStream outputStream = socket.getOutputStream();
            byte[] msg = new byte[]{0x00, 0x00, 0x00, 0x00, 0x00, 0x06, 0x00, 0x03, 0x00, 0x42, 0x00, 0x04};
            sentToServer(socket, msg);
            startKeepAliveThread(socket);
            while ((len = inputStream.read(bytes)) != -1) {
                //注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
                System.out.println("数据长度：" + len + " get message from server(bytes): " + Arrays.toString(bytes));
                System.out.println("返回数据长度：" + bytes[5]);
                System.out.println("请求码：" + bytes[7]);
                System.out.println("返回数据长度：" + bytes[8] / 2);
                byte[] result = new byte[bytes[8]];
                for (int i = 0; i < result.length; i++) {
                    result[i] = bytes[9 + i];
                }
                System.out.println("返回数据为：" + Arrays.toString(result));
            }
            inputStream.close();
            outputStream.close();
            socket.close();

        } catch (SocketException se) {
            System.out.println("SocketException " + se.getMessage());
        } catch (IOException ioe) {
            System.out.println("IOException " + ioe.getMessage());
        }
    }

    /**
     * 网服务端发送数据
     *
     * @param socket
     * @param msg
     * @throws Exception
     */
    private static void sentToServer(Socket socket, byte[] msg) throws IOException {
        socket.getOutputStream().write(msg);
        //通过shutdownOutput高速服务器已经发送完数据，后续只能接受数据
//        socket.shutdownOutput();
    }

    /**
     * 保活心跳
     *
     * @param period
     * @param socket
     * @param msg
     * @throws IOException
     * @throws InterruptedException
     */
    private static void keepAlive(int period, Socket socket, byte[] msg) throws IOException, InterruptedException {
        int i = 0;
        while (true) {
            i++;
            System.out.println("keepAlive次数：" + i);
            Thread.sleep(period);
            sentToServer(socket, msg);
        }
    }

    /**
     * 开启心跳线程
     *
     * @param socket
     */
    private static void startKeepAliveThread(final Socket socket) {
        new Thread() {
            @Override
            public void run() {
                super.run();
                byte[] msg2 = new byte[]{0x00, 0x00, 0x00, 0x00, 0x00, 0x06, 0x00, 0x03, 0x00, 0x42, 0x00, 0x04};
                try {
                    keepAlive(KEEP_ALIVE_PERIOD, socket, msg2);
                } catch (IOException ie) {
                    System.out.println("IOException in thread" + ie.getMessage());
                } catch (InterruptedException ie) {
                    System.out.println("InterruptedException in thread" + ie.getMessage());
                }
            }
        }.start();

    }
}
