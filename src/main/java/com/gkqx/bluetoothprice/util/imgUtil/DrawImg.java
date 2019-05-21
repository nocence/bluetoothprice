package com.gkqx.bluetoothprice.util.imgUtil;

import com.gkqx.bluetoothprice.model.Goods;
import com.gkqx.bluetoothprice.util.fileUtil.FileUtil;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;


/**
 * @ClassName Drawgoods
 * @Description 生成图片的工具类
 * @Author Innocence
 * @Date 2019/4/19 001910:37
 * @Version 1.0
 **/
public class DrawImg {
    /**
    * 生成整张图片的工具,图片大小已经限定为212*104。生成图片之后保存到本地
    * @author Innocence
    * @date 2019/4/19 001914:59
    * @param
    * @return  图片保存地址
    */
    public String DrawInfo(Goods goods, String path) throws IOException {
        //得到图片缓存区，INT精确度达到一定,RGB三原色，高度104,宽度212
        BufferedImage bi = new BufferedImage(212,104,BufferedImage.TYPE_INT_RGB);
        //得到它的绘制环境(这张图片的笔)
        Graphics2D g2 = (Graphics2D) bi.getGraphics();

        //头部信息
        g2.fillRect(0,0,212,16);//填充一个矩形 左上角坐标(0,0),宽212,高16;
        //设置颜色
        g2.setColor(Color.black);
        g2.fillRect(0,0,212,16);//填充整张图片(其实就是设置背景颜色)
        g2.setFont(new Font("宋体",Font.BOLD,14)); //设置字体:字体、字号、大小
        g2.setColor(Color.white);//设置背景颜色
        String headStr = goods.getGoodsName();
        g2.drawString(headStr,57,13); //向图片上写字符串

        //左边信息部分
        g2.fillRect(0,16,106,70);
        g2.setColor(Color.white);
        g2.fillRect(0,16,106,70);
        g2.setFont(new Font("宋体",Font.PLAIN,12));
        g2.setColor(Color.black);
        String midLeft1 = "单位："+goods.getGoodsUnit();
        String midLeft2 = "规格："+goods.getGoodsSpecs();
        String midLeft3 = "产地："+goods.getGoodsPlace();
        String midLeft4 = "编码："+goods.getGoodsCode();
        g2.drawString(midLeft1,0,34);
        g2.drawString(midLeft2,0,47);
        g2.drawString(midLeft3,0,60);
        g2.drawString(midLeft4,0,73);

        //截取价格部分的整数和小数部分
        String price = goods.getGoodsPrice();
        String integerPrice = price.substring(0, price.lastIndexOf(".")+1 );
        String doublePrice = price.substring(price.lastIndexOf(".") + 1);
        //右边价格整数部分
        g2.fillRect(106,16,53,52);
        g2.setColor(Color.white);
        g2.fillRect(106,16,53,52);
        g2.setFont(new Font("幼圆",Font.BOLD,36));
        g2.setColor(Color.black);
        String midRight1 = integerPrice;
        g2.drawString(midRight1,90,52);

        //右边价格小数和单位部分
        g2.fillRect(159,16,53,52);
        g2.setColor(Color.white);
        g2.fillRect(159,16,53,52);
        g2.setFont(new Font("宋体",Font.PLAIN,20));
        g2.setColor(Color.black);
        String midRight2 = doublePrice;
        String midRight3 = "元";
        g2.drawString(midRight2,159,35);
        g2.drawString(midRight3,189,49);

        //右边的编码和条形码部分（条形码还没画）
        g2.fillRect(96,68,116,20);
        g2.setColor(Color.white);
        g2.fillRect(96,68,116,20);
        g2.setFont(new Font("宋体",Font.PLAIN,12));
        g2.setColor(Color.black);
        String midRightDown = goods.getGoodsBarcode();
        g2.drawString(midRightDown,106,62);

        //下边的黑线
        g2.fillRect(0,82,212,2);
        g2.setColor(Color.black);
        g2.fillRect(0,82,212,2);

        //最下面的监督和举报电话
        g2.fillRect(0,84,212,20);
        g2.setColor(Color.white);
        g2.fillRect(0,84,212,20);
        g2.setFont(new Font("宋体",Font.PLAIN,12));
        g2.setColor(Color.black);
        String down = "举报监督电话：12358 ，"+goods.getGoodsTell();
        g2.drawString(down,12,98);

        FileUtil fileUtil = new FileUtil();
        String fileName = fileUtil.creatFileName()+".bmp";

        String fileClassPath = path+fileName;
        ImageIO.write(bi,"bmp",new FileOutputStream(fileClassPath));//保存图片 bmp表示保存格式
        return fileClassPath;
    }

}
