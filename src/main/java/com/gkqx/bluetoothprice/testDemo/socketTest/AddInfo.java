package com.gkqx.bluetoothprice.testDemo.socketTest;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * @ClassName AddInfo
 * @Description TODD
 * @Author Innocence
 * @Date 2019/4/24 002414:13
 * @Version 1.0
 **/
public class AddInfo {

    public String addInfo(String oldPath,String contant){
        try{
            FileWriter writer = new FileWriter(oldPath,true);
            writer.write(contant);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return oldPath;
    }
}
