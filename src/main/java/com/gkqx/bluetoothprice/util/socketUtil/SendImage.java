package com.gkqx.bluetoothprice.util.socketUtil;


import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @ClassName SendImage
 * @Description 测试图片和字符串同时发送
 * @Author Innocence
 * @Date 2019/4/24 002416:22
 * @Version 1.0
 **/
public class SendImage {
    /**
     * 图片开始的标识
     */
    private final String IMAGE_START = "image:";
    /**
     * 一条完整信息结束的标识
     */
    private final String MESSAGE_END = "over";
    /**
     * 文件名结束的表示
     */
    private final String FILE_NAME_END = "?";

    public void sendImage(File file,String str) throws IOException {
        //创建一个服务器
        ServerSocket socket = new ServerSocket(8899);
        //开始监听
        Socket accept = socket.accept();
        //获取socket的输出流
        PrintStream ps = new PrintStream(accept.getOutputStream());
        //获取文件的输入流
        BufferedInputStream bis = new BufferedInputStream(new FileInputStream(file));
        System.out.println("开始发送");
        //将文本发送过去

        ps.write(str.getBytes());
        //发送图片开始的标识
        ps.write(IMAGE_START.getBytes());
        //发送图片
        int len;
        byte[] b = new byte[1024];
        while((len=bis.read(b))!=-1){
            ps.write(b,0,len);
        }

        System.out.println("传输完成");
        bis.close();
        ps.close();
        socket.close();

    }


}