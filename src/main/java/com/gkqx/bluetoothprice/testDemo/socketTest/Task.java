package com.gkqx.bluetoothprice.testDemo.socketTest;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * @ClassName Task
 * @Description 处理客户端传输过来的文件线程类
 * @Author Innocence
 * @Date 2019/4/24 002411:03
 * @Version 1.0
 **/
public class Task implements Runnable{

    private Socket socket;

    private DataInputStream dis;

    private FileOutputStream fos;

    public Task(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try{
            dis = new DataInputStream(socket.getInputStream());
            // 文件名和长度
            String fileName = dis.readUTF();
            long fileLength = dis.readLong();
            File directory = new File("D:\\FTCache");
            if(!directory.exists()) {
                directory.mkdir();
            }
            File file = new File(directory.getAbsolutePath() + File.separatorChar + fileName);
            fos = new FileOutputStream(file);
            // 开始接收文件
            byte[] bytes = new byte[1024];
            int length = 0;
            while((length = dis.read(bytes, 0, bytes.length)) != -1) {
                fos.write(bytes, 0, length);
                fos.flush();
            }
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            try{
                if (fos != null){
                    fos.close();
                }
                if (dis != null){
                    dis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
