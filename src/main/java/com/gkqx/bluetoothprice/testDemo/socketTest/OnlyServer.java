package com.gkqx.bluetoothprice.testDemo.socketTest;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName OnlyServer
 * @Description TODD
 * @Author Innocence
 * @Date 2019/4/24 002411:40
 * @Version 1.0
 **/
public class OnlyServer {

    public void load(File file) throws IOException {
        //创建一个服务器
        ServerSocket socket = new ServerSocket(8899);
        //开始监听
        Socket accept = socket.accept();
        //获取socket的输出流
        PrintStream printStream = new PrintStream(accept.getOutputStream());
        //获取文件的输入流
        BufferedInputStream bufferedInputStream = new BufferedInputStream(new FileInputStream(file));
        System.out.println("开始传输。。。");
        byte[] b = new byte[1024];
        int len = 0;
        while ((len = bufferedInputStream.read(b))!=-1){
            printStream.write(b,0,len);
        }
        System.out.println("传输完成");
        bufferedInputStream.close();
        printStream.close();
        socket.close();

    }



    public static void main(String[] args) throws IOException {
        String pathName = "F:/LymTools/bluetoothprice/src/main/resources/static/images/dc35adc4e6da44b8a1dff012fe50631c.bmp";
        File file = new File(pathName);
        new OnlyServer().load(file);
    }
}
