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
    public static final int SHORT_LENGTH = 4;

    /**
    * 与硬件端约定的每个固定标识符长度和标识内容长度的和
    * @author Innocence
    * @date 2019/5/14 001416:05
    */
    public static final int COMBINATION_LENGTH = 8;

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
    public static final String GID = "GID:";

    /**
    * 与硬件端约定的标识图片像素的标识符
    * @author Innocence
    * @date 2019/5/14 001416:06
    */
    public static final String PIX = "PIX:";

}
