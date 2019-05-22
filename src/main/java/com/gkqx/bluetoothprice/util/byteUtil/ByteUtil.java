package com.gkqx.bluetoothprice.util.byteUtil;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.regex.Pattern;

import static com.gkqx.bluetoothprice.common.socketComon.SocketCommon.*;


/**
 * 读取文件流之后对byte数组的处理方法
 * 因为byte类型数据只有-128~127，因此在读取的时候，如果对应位置上的数为负数，用0x000000ff&将其转化为正数
 * @author Innocence
 * @date 2019/4/25 002517:28
 */
public class ByteUtil {


    /**
     * 合并多个byte数组；
     * @author Innocence
     * @date 2019/4/25 002517:05
     * @param  srcArrays
     * @return byte[]
     */
    public  byte[] sysCopy(List<byte[]> srcArrays) {
        int len = 0;
        for (byte[] srcArray : srcArrays) {
            len += srcArray.length;
        }
        byte[] destArray = new byte[len];
        int destLen = 0;
        for (byte[] srcArray : srcArrays) {
            System.arraycopy(srcArray, 0, destArray, destLen, srcArray.length);
            destLen += srcArray.length;
        }
        return destArray;
    }
    /**
    * 将大byte数组拆分成小的byte数组
    * @author Innocence
    * @date 2019/5/17 001717:31
    * @param bytes, size
    * @return java.util.List<byte[]>
    */
    public byte[][] splitBytes(byte[] bytes, int size) {
        double splitLength = Double.parseDouble(size + "");
        int arrayLength = (int) Math.ceil(bytes.length / splitLength);
        byte[][] result = new byte[arrayLength][];
        int from, to;
        for (int i = 0; i < arrayLength; i++) {

            from = (int) (i * splitLength);
            to = (int) (from + splitLength);
            if (to > bytes.length)
                to = bytes.length;
            result[i] = Arrays.copyOfRange(bytes, from, to);
        }
        return result;
    }

    /**
     * 字节重组成队列
     * @param bytes
     * @return
     */
    public static Queue<Byte> bytes2Queue(byte[] bytes) {
        Queue<Byte> queue = new LinkedList<Byte>();
        for(int i=0; i<bytes.length; i++) {
            queue.offer(bytes[i]);
        }
        return queue;
    }

    /**
     * 队列按照字节大小出列，
     * @param queue
     * @param size
     * @return
     */
    public static byte[] queueOutByte(Queue<Byte> queue, int size) {
        List<Byte> link = new LinkedList<Byte>();
        Byte tempByte = null;
        byte[] byt = null;
        for(int i=0; i<size; i++) {
            tempByte = queue.poll();
            if( tempByte == null ) break;
            link.add(tempByte);
        }

        // 组装基本类型字节
        byt = new byte[link.size()];
        for (int i=0; i<link.size(); i++) {
            byt[i] = link.get(i);
        }
        return byt;
    }
    /**
     * 将文件流读取出来.因为文件属性在数据流前54个字节就能得到，所以这里只需要读一部分数据就足够
     * @author Innocence
     * @date 2019/4/26 002610:27
     * @param filePath
     * @return byte[] bytes
     */
    public byte[] intoFile(String filePath){
        byte[] bytes = null;
        FileInputStream fis = null;
        BufferedInputStream bis = null;
        try {
            fis = new FileInputStream(filePath);
            bis = new BufferedInputStream(fis);
            bytes = new byte[1024];
            bis.read(bytes);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            try{
                if (bis!=null){
                    bis.close();
                }
                if (fis!=null){
                    fis.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return bytes;
    }
    /**
     * 将图片流的图片宽度计算出来.
     * @author Innocence
     * @date 2019/4/26 002610:50
     * @param bytes
     * @return int
     */
    public int getImgWidth(byte[] bytes){

        return 0x00ffffff*(0x000000ff&bytes[21])+0x0000ffff*(0x000000ff&bytes[20])+0x000000ff*(0x000000ff&bytes[19])+0x000000ff&bytes[18];
    }
    /**
     * 将图片流的图片高度计算出来
     * @author Innocence
     * @date 2019/4/26 002610:57
     * @param bytes
     * @return int
     */
    public int getImgHeight(byte[] bytes){
        return 0x00ffffff*(0x000000ff&bytes[25])+0x0000ffff*(0x000000ff&bytes[24])+0x000000ff*(0x000000ff&bytes[23])+0x000000ff&bytes[22];
    }
    /**
     * 将图片流的图片位数计算出来
     * @author Innocence
     * @date 2019/4/26 002610:57
     * @param bytes
     * @return int
     */
    public int getBigCount(byte[] bytes){
        return 0x000000ff*(0x000000ff&bytes[29])+0x000000ff&bytes[28];
    }

    /**
     * 计算常量1，根据图片宽度，字节位数得到常量1
     * @author Innocence
     * @date 2019/4/26 002614:00
     * @param bytes
     * @return int
     */
    public int widFianl(byte[] bytes){
        int widFianl = (getImgWidth(bytes)+7)/8;
        return widFianl;
    }
    /**
     * 计算常量2，算出图片横着一排的点
     * @author Innocence
     * @date 2019/4/26 002614:06
     * @param bytes
     * @return void
     */
    public int allFianl(byte[] bytes){
        int allFianl = ((getImgWidth(bytes) * getBigCount(bytes) + 31) >> 5) << 2;
        return allFianl;
    }
    /**
     * 将byte转换成int，然后利用Integer.toHexString(int)来转换成16进制字符串
     * @author Innocence
     * @date 2019/4/26 002617:18
     * @param bytes
     * @return java.lang.String
     */
    public String bytesToHexString(byte[] bytes){
        StringBuilder stringBuilder = new StringBuilder("");
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        for (int i = 0; i < bytes.length; i++) {
            int v = bytes[i] & 0xFF;
            String hv = Integer.toHexString(v);
            if (hv.length() < 2) {
                stringBuilder.append(0);
            }
            stringBuilder.append(hv);
        }
        return stringBuilder.toString();
    }
    /**
     * 十六进制转化成字符串
     * @author Innocence
     * @date 2019/5/5 000509:31
     * @param
     * @return
     */
    public String toStringHex(String s) {
        byte[] baKeyword = new byte[s.length() / 2];
        for (int i = 0; i < baKeyword.length; i++) {
            try {
                baKeyword[i] = (byte) (0xff & Integer.parseInt(s.substring(
                        i * 2, i * 2 + 2), 16));
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        try {
            s = new String(baKeyword, "utf-8");// UTF-16le:Not
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return s;
    }

    public static void main(String[] args) throws FileNotFoundException {
        ByteUtil byteUtil = new ByteUtil();
//        String s = byteUtil.toStringHex("7365727665724F4B");
//        System.out.println(s);
//        int i = Integer.parseInt("000012");
//        System.out.println(i);00000000000000000000000000000000    01234567890123456789012345678910
        String s = "MAC:6A9208A6C13BPID:00000000000000000000000000000000PIX:250*122";
        Pattern pattern = Pattern.compile("[0-9]*");

        //根据得到的消息长度截取第一段信息
        String s1 = s.substring(SIGN_LENGTH, SIGN_LENGTH+MAC_DATA_LENGTH);
        System.out.println("第一段消息："+s1);
        //获取第二段消息的长度
        String s2 = s.substring(SIGN_LENGTH*2+MAC_DATA_LENGTH, SIGN_LENGTH*2+MAC_DATA_LENGTH+PID_DATA_LENGTH);
        System.out.println("第二段消息："+s2);
        boolean matches = pattern.matcher(s).matches();
        System.out.println(matches);
        //获取第三段消息长度
        String s3 = s.substring(SIGN_LENGTH*3+MAC_DATA_LENGTH+PID_DATA_LENGTH);
        //第三段消息
        System.out.println("第三段消息："+s3);
//        //测试数据切割
//        AllMsg allMsg = new AllMsg();
//        byte[] hex = allMsg.hex("F:\\LymTools\\bluetoothprice\\src\\main\\resources\\static\\images\\ca5102a20a634c23b343fd13b3b9dc3a.bmp");
//        byte[][] bytes = byteUtil.splitBytes(hex, 243);
//        System.out.println(bytes.length);
//        for (byte[] b:bytes) {
//            int length=b.length;
//            System.out.println(length);
//            String s4 = byteUtil.bytesToHexString(b);
//            System.out.println("手动空格");
//            System.out.println(s4);
//
//        }
//        System.out.println("完成");
    }
}
