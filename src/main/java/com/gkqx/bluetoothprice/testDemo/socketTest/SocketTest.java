package com.gkqx.bluetoothprice.testDemo.socketTest;

import java.io.IOException;
import java.math.RoundingMode;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.DecimalFormat;

/**
 * @ClassName SocketTest
 * @Description socket测试
 * @Author Innocence
 * @Date 2019/4/24 002410:47
 * @Version 1.0
 **/
public class SocketTest extends ServerSocket{

    //服务器端口
    private static final int SERVER_PORT = 8899;

    private static DecimalFormat df = null;

    static {
        // 设置数字格式，保留一位有效小数
        df = new DecimalFormat("#0.0");
        df.setRoundingMode(RoundingMode.HALF_UP);
        df.setMinimumFractionDigits(1);
        df.setMaximumFractionDigits(1);
    }

    public SocketTest() throws IOException {
        super(SERVER_PORT);
    }
    /**
    * 使用线程处理每个客户端传输的文件
    * @author Innocence
    * @date 2019/4/24 002410:55
    * @param
    * @return
    */
    public void load() throws Exception{
        while (true){
            //server尝试接收其他socket的连接请求，server的accep方法是阻塞的
            Socket socket = this.accept();
            /**
             * 我们的服务端处理客户端的连接请求是同步进行的， 每次接收到来自客户端的连接请求后，
             * 都要先跟当前的客户端通信完之后才能再处理下一个连接请求。 这在并发比较多的情况下会严重影响程序的性能，
             * 为此，我们可以把它改为如下这种异步处理与客户端通信的方式
             */
            //每接收到一个socket就建立一个新的线程来处理
            new Thread(new Task(socket)).start();

        }
    }



    public static void main(String[] args) {

        try {
            SocketTest test = new SocketTest();
            test.load();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
