package com.gkqx.bluetoothprice.testDemo.DrawTest;

/**
 * @ClassName ChartGraphics
 * @Description TODD
 * @Author Innocence
 * @Date 2019/4/17 001710:05
 * @Version 1.0
 **/
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGImageEncoder;

public class ChartGraphics {
    private BufferedImage image;
    private int imageWidth = 212;  //图片的宽度
    private int imageHeight = 104; //图片的高度
    //生成图片文件
    @SuppressWarnings("restriction")
    public void createImage(String fileLocation) {
        BufferedOutputStream bos = null;
        if(image != null){
            try {
                FileOutputStream fos = new FileOutputStream(fileLocation);
                bos = new BufferedOutputStream(fos);

                JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(bos);
                encoder.encode(image);
                bos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }finally{
                if(bos!=null){//关闭输出流
                    try {
                        bos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public void graphicsGeneration(String name, String id, String classname, String imgurl) {
        int H_title = 16;     //头部高度
        int H_main = 72;  //中间高度
        int H_btn = 16;  //最下面的高度
        int tip_2_top = (H_title+H_main);//头部和中间高度
        int H_pic = 16; //条形码图片高度


        image = new BufferedImage(imageWidth, imageHeight, BufferedImage.TYPE_INT_RGB);
        //设置图片的背景色
        Graphics2D main = (Graphics2D) image.getGraphics();
        main.setColor(Color.white);
        main.fillRect(0, 0, imageWidth, imageHeight);

        //***********************页面头部
        Graphics title = image.createGraphics();
        //设置区域颜色
        title.setColor(new Color(0, 0, 0));
        //填充区域并确定区域大小位置
        title.fillRect(0, 0, imageWidth, H_title);
        //设置字体颜色，先设置颜色，再填充内容
        title.setColor(Color.white);
        //设置字体
        int textSize = 16;
        Font titleFont = new Font("宋体", Font.BOLD, textSize);
        title.setFont(titleFont);
        String headStr = "美汁源果粒果汁";
        title.drawString(headStr, (imageWidth-headStr.length()*textSize)/2,(imageHeight-textSize)/2);

        //***********************设置中间的文字框
        Graphics2D tip = image.createGraphics();
        //设置区域颜色
        tip.setColor(new Color(255, 255, 255));
        //填充区域并确定区域大小位置
        tip.fillRect(0, H_title, 106, H_main);
        //设置字体颜色，先设置颜色，再填充内容
        tip.setColor(Color.black);
        //设置字体
        Font tipFont = new Font("宋体", Font.PLAIN, 14);
        tip.setFont(tipFont);
        //中间左边的字
        String mainLeft1 = "单位：GE";
        String mainLeft2 = "规格：420ML";
        String mainLeft3 = "产地：中国武汉";
        String mainLeft4 = "编码：607868";
        tip.drawString(mainLeft1, 0, H_title+14);
        tip.drawString(mainLeft2, 0, H_title+14*2);
        tip.drawString(mainLeft3,0,H_title+14*3);
        tip.drawString(mainLeft4,0,H_title+14*4);

        //**********************设置中间右边的文字
        Graphics2D rig = image.createGraphics();
        //设置区域颜色
        rig.setColor(new Color(255,255,255));
        //填充区域并确定区域大小位置
        rig.fillRect(106,H_title,53,52);
        //设置字体颜色，先设置颜色，再填充内容
        rig.setColor(Color.black);
        Font rigFont = new Font("幼圆", Font.BOLD, 32);
        rig.setFont(rigFont);
        String mainRight1 = "33.";
        rig.drawString(mainRight1,106,H_title+2);

        Graphics2D rigRrightop = image.createGraphics();
        rigRrightop.setColor(new Color(255,255,255));
        rigRrightop.fillRect(159,H_title,30,30);
        rigRrightop.setColor(Color.black);
        Font rigRrightopFont = new Font("宋体", Font.PLAIN, 24);
        rigRrightop.setFont(rigRrightopFont);
        String mainRight2 = "40";
        rigRrightop.drawString(mainRight2,159,H_title+2);

        Graphics2D rigRightDown = image.createGraphics();
        rigRightDown.setColor(new Color(255,255,255));
        rigRightDown.fillRect(189,46,23,22);
        rigRightDown.setColor(Color.black);
        Font rigRightDownFont = new Font("宋体", Font.PLAIN, 20);
        rigRightDown.setFont(rigRightDownFont);
        String mainRight3 = "元";
        rigRightDown.drawString(mainRight3,190,48);

        //写右下角的编码区
        Graphics2D unicodeErea = image.createGraphics();
        unicodeErea.setColor(new Color(255,255,255));
        unicodeErea.fillRect(106,68,106,20);
        unicodeErea.setColor(Color.black);
        Font unicodeEreaFont = new Font("宋体", Font.PLAIN, 8);
        unicodeErea.setFont(unicodeEreaFont);
        String mainRight4 = "1234567890123";
        unicodeErea.drawString(mainRight4,(imageWidth-106-mainRight4.length()*8)/2,69);

        //***********************设置最下面的横线和内容
        Graphics2D footLine = image.createGraphics();
        footLine.setColor(new Color(0,0,0));
        footLine.fillRect(0,89,imageWidth,1);

        Graphics2D footText = image.createGraphics();
        footText.setColor(new Color(255,255,255));
        footText.fillRect(0,90,imageWidth,14);
        footText.setColor(Color.black);
        Font footTextFont = new Font("宋体", Font.PLAIN, 12);
        footText.setFont(footTextFont);
        String downText = "价格举报电话：12538 监督电话：40060688768";
        footText.drawString(downText,(imageWidth-downText.length()*12)/2,91);
        //***********************插入二维码图片
        /*Graphics mainPic = image.getGraphics();
        BufferedImage bimg = null;
        try {
            bimg = javax.imageio.ImageIO.read(new java.io.File(imgurl));
        } catch (Exception e) {}

        if(bimg!=null){
            mainPic.drawImage(bimg, 0, H_title, imageWidth, H_mainPic, null);
            mainPic.dispose();
        }*/



        createImage("F:/GoogleDownload/test.png");

    }

    public static void main(String[] args) {
        ChartGraphics cg = new ChartGraphics();
        try {
            cg.graphicsGeneration("ewew", "1", "12", null);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
