package com.gkqx.bluetoothprice.util.socketUtil;

import com.gkqx.bluetoothprice.util.byteUtil.ByteUtil;
import com.gkqx.bluetoothprice.util.imgUtil.ImageToHex;

import java.io.FileNotFoundException;
import java.util.ArrayList;

/**
 * @ClassName AllMsg
 * @Description 将图片的点阵数据流拆分成若干小数组，将对应的标识符添加到小数组，再合并成一个流
 *              每个小数组固定size=255
 * @Author Innocence
 * @Date 2019/4/28 002815:48
 * @Version 1.0
 **/
public class AllMsg {
    public byte[] hex(String filePath,String goodsId,String macAddress) throws FileNotFoundException {
        ArrayList<byte[]> list = new ArrayList<>();
        //获取图片的点阵数据
        byte[] bytes = new ImageToHex().toHex(filePath);
        //将点阵数据拆分小数组
        ByteUtil byteUtil = new ByteUtil();
        byte[][] splitBytes = byteUtil.splitBytes(bytes, 215);
        //将对应的标识符添加到小数组
        for (int i=0;i<splitBytes.length;i++){
            //流开头的标识符
            byte[] img = "IMG:".getBytes();
            //商品id
            byte[] goods = goodsId.getBytes();
            //mac地址
            byte[] mac = macAddress.getBytes();
            //切割成的流的大小
            int length = splitBytes[i].length;
            String num = null;
            if (length<1000){
                num = "0"+String.valueOf(length);
            }else if(length>=1000 && length<=9999){
                num = String.valueOf(length);
            }
            byte[] numBytes = num.getBytes();
            //表示第几个包的数据
            int codeNum = i+1;
            String stringNum = null;
            if (codeNum<10){
                stringNum = "000"+String.valueOf(codeNum);
            }else if(codeNum>=10 && codeNum<99){
                stringNum = "00"+String.valueOf(codeNum);
            }else if (codeNum>=99 && codeNum<999){
                stringNum = "0"+String.valueOf(codeNum);
            }else if (codeNum>=999 && codeNum<9999){
                stringNum = String.valueOf(codeNum);
            }
            byte[] stringNumBytes = stringNum.getBytes();
            list.add(img);
            list.add(goods);
            list.add(mac);
            list.add(stringNumBytes);
            list.add(numBytes);
            list.add(splitBytes[i]);
        }
        byte[] sysCopy = new ByteUtil().sysCopy(list);
        return sysCopy;
    }

}
