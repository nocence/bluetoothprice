package com.gkqx.bluetoothprice.testDemo.DrawTest;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * @ClassName DrawDemo
 * @Description TODD
 * @Author Innocence
 * @Date 2019/4/17 001716:55
 * @Version 1.0
 **/
public class DrawDemo {
    public static void main(String[] args) throws FileNotFoundException, IOException {

        //得到图片缓冲区
        BufferedImage bi = new BufferedImage(212,104,BufferedImage.TYPE_INT_RGB);//INT精确度达到一定,RGB三原色，高度104,宽度212

        //得到它的绘制环境(这张图片的笔)
        Graphics2D g2 = (Graphics2D) bi.getGraphics();

        //头部信息
        g2.fillRect(0,0,212,16);//填充一个矩形 左上角坐标(0,0),宽212,高16;
        //设置颜色
        g2.setColor(Color.black);
        g2.fillRect(0,0,212,16);//填充整张图片(其实就是设置背景颜色)
        g2.setFont(new Font("宋体",Font.BOLD,14)); //设置字体:字体、字号、大小
        g2.setColor(Color.white);//设置背景颜色
        String headStr = "美汁源果粒果汁";
        int x = (212-headStr.length()*14)/2;
        g2.drawString(headStr,x,13); //向图片上写字符串

        //左边信息部分
        g2.fillRect(0,16,106,70);
        g2.setColor(Color.white);
        g2.fillRect(0,16,106,70);
        g2.setFont(new Font("宋体",Font.PLAIN,12));
        g2.setColor(Color.black);
        String midLeft1 = "单位：GE";
        String midLeft2 = "规格：420ML";
        String midLeft3 = "产地：中国武汉";
        String midLeft4 = "编码：068078";
        g2.drawString(midLeft1,0,34);
        g2.drawString(midLeft2,0,47);
        g2.drawString(midLeft3,0,60);
        g2.drawString(midLeft4,0,73);

        //右边价格整数部分
        g2.fillRect(106,16,53,52);
        g2.setColor(Color.white);
        g2.fillRect(106,16,53,52);
        g2.setFont(new Font("幼圆",Font.BOLD,36));
        g2.setColor(Color.black);
        String midRight1 = "330.";
        g2.drawString(midRight1,90,52);

        //右边价格小数和单位部分
        g2.fillRect(159,16,53,52);
        g2.setColor(Color.white);
        g2.fillRect(159,16,53,52);
        g2.setFont(new Font("宋体",Font.PLAIN,20));
        g2.setColor(Color.black);
        String midRight2 = "40";
        String midRight3 = "元";
        g2.drawString(midRight2,159,35);
        g2.drawString(midRight3,189,49);

        //右边的编码和条形码部分（条形码还没画）
        g2.fillRect(96,68,116,20);
        g2.setColor(Color.white);
        g2.fillRect(96,68,116,20);
        g2.setFont(new Font("宋体",Font.PLAIN,12));
        g2.setColor(Color.black);
        String midRightDown = "1234567890123";
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
        String down = "举报监督电话：12358 ，4006068768";
        g2.drawString(down,12,98);

        //将生成的图片放在项目路径下
        String path = DrawDemo.class.getResource("").getPath();
        System.out.println(path);
        ImageIO.write(bi,"PNG",new FileOutputStream(path+"a.bmp"));//保存图片 JPEG表示保存格式


    }


}
