package com.gkqx.bluetoothprice.util.imgUtil;

import com.gkqx.bluetoothprice.util.byteUtil.ByteUtil;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * @ClassName ImageToHex2
 * @Description 将图片数据转化成点阵数据，传送过去的流就小很多，该方法留着备用
 * @Author Innocence
 * @Date 2019/4/25 002511:54
 * @Version 1.0
 **/
public class ImageToHex {
    public byte[] toHex(String filePath) {
        byte[] result = null;
        List<byte[]> bytes = new ArrayList<>();
        ByteUtil util = new ByteUtil();
        try {
            FileInputStream fis = new FileInputStream(filePath);
            BufferedInputStream bis = new BufferedInputStream(fis);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();

            byte[] buff = new byte[1024];
            while ((bis.read(buff))!=-1) {
                bytes.add(buff);
            }
            byte[] copy = util.sysCopy(bytes);
            byte[] intoFile = util.intoFile(filePath);
            //图片数据转换为点阵数据
            byte[] dotBuf = new byte[util.getImgWidth(intoFile) * util.widFianl(intoFile)];
            byte m;
            //这里两个byte数组的元素是常量，不允许改动
            byte[] mask = {-128, 64, 32, 16, 8, 4, 2, 1};
            byte[] maskR = {127, -65, -33, -17, -9, -5, -3, -2};
            for (int i = 0; i < util.getImgHeight(intoFile); i++) {
                for (int j = 0; j < util.allFianl(intoFile); j = j + 3) {
                    m = (byte) ((j / 3) % 8);
                    if (copy[util.allFianl(intoFile) * i + j] == -1) {
                        dotBuf[i * util.widFianl(intoFile) + j / util.getBigCount(intoFile)] |= mask[m];
                    } else {
                        dotBuf[i * util.widFianl(intoFile) + j / util.getBigCount(intoFile)] &= maskR[m];
                    }
                }
            }

            bos.write(dotBuf, 0, util.getImgWidth(intoFile) * util.widFianl(intoFile));
            result = bos.toByteArray();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }
}
