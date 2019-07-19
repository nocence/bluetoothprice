package com.gkqx.bluetoothprice.util.imgUtil;

import com.gkqx.bluetoothprice.util.byteUtil.ByteUtil;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Arrays;

/**
 * @ClassName ImageToHex2
 * @Description 将图片数据转化成点阵数据，传送过去的流就小很多
 * @Author Innocence
 * @Date 2019/4/25 002511:54
 * @Version 1.0
 **/
public class ImageToHex {
    public byte[] toHex(String filePath) {
        byte[] result = null;
        ByteUtil util = new ByteUtil();
        ByteArrayOutputStream bos = null;
        try {
            bos = new ByteArrayOutputStream();
            byte[] copy = util.toByteArray(filePath);
            byte[] newCopy = Arrays.copyOfRange(copy, 54, copy.length);
            byte[] intoFile = util.intoFile(filePath);
            //图片数据转换为点阵数据(以横排的点为基准)
            byte[] dotBuf = new byte[util.getImgWidth(intoFile) * util.widFianl(intoFile)];

            int adr=0;
            int bit=0;
            int getBigCount = util.getBigCount(intoFile);
            int imgHeight = util.getImgHeight(intoFile);
            int imgWidth = util.getImgWidth(intoFile);
            int bmpLine = util.allFianl(intoFile);
            int dotLine = util.widFianl(intoFile);
            //这里两个byte数组的元素是常量，不允许改动
            byte[] mask = {-128, 64, 32, 16, 8, 4, 2, 1};
            byte[] maskR = {127, -65, -33, -17, -9, -5, -3, -2};
            /*220*104的显示屏点阵转化函数*/
//            for (int i = 0; i < imgWidth; i++) {
//                for (int j = 0; j < imgHeight; j++) {
//                    adr =(imgHeight-1-j)*bmpLine+i*getBigCount/8;
//                    bit = (i*getBigCount)%8;
//                    if ((newCopy[adr] & mask[bit]) != 0) {
//                        dotBuf[(j/8) * imgWidth + i] |= mask[j%8];
//                    } else {
//                        dotBuf[(j/8) * imgWidth + i] &= maskR[j%8];
//                    }
//                }
//            }
            /*250*122的显示屏点阵转化函数*/
            for (int i = 0; i < imgWidth; i++) {
                for (int j = 0; j < imgHeight; j++) {
                    adr =(imgHeight-1-j)*bmpLine + (imgWidth-1-i)*getBigCount/8;
                    bit = ((imgWidth-1-i)*getBigCount)%8;
                    if ((newCopy[adr] & mask[bit]) == 0) {
                        dotBuf[(imgWidth-1-i)*dotLine + j/8] &= maskR[j%8];
                    } else {
                        dotBuf[(imgWidth-1-i)*dotLine + j/8] |= mask[j%8];
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
