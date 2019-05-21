package com.gkqx.bluetoothprice.util.socketUtil;

import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName ReciveMsg
 * @Description 测试服务器接收客户端消息的方法
 * @Author Innocence
 * @Date 2019/4/24 002417:09
 * @Version 1.0
 **/
public class ReciveMsg {
    public static void main(String[] args) throws IOException {
        //创建一个服务器
        ServerSocket socket = new ServerSocket(8899);
        //开始监听
        Socket accept = socket.accept();
        //接收客户端的消息
        BufferedInputStream bis = new BufferedInputStream(accept.getInputStream());
        byte[] b = new byte[200];
        int len ;
        while ((len=bis.read(b))!=-1){
            String string = new String(b, 0, len);
            System.out.println(string);
        }

    }

}
