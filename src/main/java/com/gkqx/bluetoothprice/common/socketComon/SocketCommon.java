package com.gkqx.bluetoothprice.common.socketComon;


/**
 * @ClassName SocketCommon
 * @Description 定义服务端的一些常量信息
 * @Author Innocence
 * @Date 2019/4/25 002517:25
 * @Version 1.0
 **/
public class SocketCommon {

    /**
    * 服务端端口
    * @author Innocence
    * @date 2019/4/26 002614:50
    */
    public static final int SERVER_PORT = 8899;

    /**
    * 表示与硬件端约定的通信信息格式的每个固定标识符的长度
    * @author Innocence
    * @date 2019/5/14 001416:05
    */
    public static final int SIGN_LENGTH = 4;

    /**
    * 与硬件端约定的MAC地址的长度
    * @author Innocence
    * @date 2019/5/14 001416:05
    */
    public static final int MAC_DATA_LENGTH = 12;

    /**
    * 与硬件端约定的商品ID的长度
    * @author Innocence
    * @date 2019/5/22 002210:21
    */
    public static final int PID_DATA_LENGTH = 16;

    /**
    * 如果价签第一次连上来，商品Id是没有的，所以是16个0的字符串
    * @author Innocence
    * @date 2019/5/22 002211:21
    */
    public static final String INIT_PID = "0000000000000000";
    /**
    * 与硬件端约定的标识MAC地址的标识符
    * @author Innocence
    * @date 2019/5/14 001416:05
    */
    public static final String MAC = "MAC:";

    /**
    * 与硬件端约定的标识商品ID的标识符
    * @author Innocence
    * @date 2019/5/14 001416:06
    */
    public static final String PID = "PID:";

    /**
    * 与硬件端约定的标识图片像素的标识符
    * @author Innocence
    * @date 2019/5/14 001416:06
    */
    public static final String PIX = "PIX:";

    /**
    * 单点发送和群发图片时，组装进数据流的不同标识
    * @author Innocence
    * @date 2019/7/16 001616:30
    * @param
    * @return
    */
    public static final String POINT_IMG_SIGN = "IMG:";//单点发送
    public static final String CLUSTER_IMG_SIGN = "CMG:";//群发
    /**
    * 服务器与硬件之间的响应报文
    * @author Innocence
    * @date 2019/5/27 002709:28
    */
    public static final String SUCCESS_RETURN = "ACK:00";
    public static final String FEILED_RETURN = "ACK:01";
    public static final String IMG_END = "ACK:02";//硬件端返回的图片接收完成
    public static final String NUM_ERROR = " ACK:03";//数据包顺序不对
}
