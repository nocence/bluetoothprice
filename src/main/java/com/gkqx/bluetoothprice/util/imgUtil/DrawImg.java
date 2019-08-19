package com.gkqx.bluetoothprice.util.imgUtil;

import com.gkqx.bluetoothprice.model.Goods;
import com.gkqx.bluetoothprice.util.fileUtil.FileUtil;
import sun.font.FontDesignMetrics;

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
    * 生成整张图片的工具,图片大小已经限定为250*122。生成图片之后保存到本地
    * @author Innocence
    * @date 2019/4/19 001914:59
    * @param
    * @return  图片保存地址
    */
    public String DrawInfo(Goods goods, String path) throws IOException {
        //得到图片缓冲区
        BufferedImage bi = new BufferedImage(250,122,BufferedImage.TYPE_INT_RGB);//INT精确度达到一定,RGB三原色，高度122,宽度250
        int oldWidth = bi.getWidth();
        int oldHeight = bi.getHeight();
        //得到它的绘制环境(这张图片的笔)
        Graphics2D g2 = (Graphics2D) bi.getGraphics();

        //头部信息
        g2.fillRect(0,0,oldWidth,20);//填充一个矩形 左上角坐标(0,0),宽212,高16;
        g2.setColor(Color.black);//设置颜色
        g2.fillRect(0,0,oldWidth,20);//填充整张图片(其实就是设置背景颜色)
        g2.setFont(new Font("宋体",Font.BOLD,18)); //设置字体:字体、字号、大小
        FontDesignMetrics headMetrics = FontDesignMetrics.getMetrics(new Font("宋体",Font.BOLD,18));//获取分辨率
        g2.setColor(Color.white);//设置背景颜色
        String headStr = goods.getGoodsName();
        int headStrWidth = headMetrics.stringWidth(headStr);
        int headLeft = (oldWidth-headStrWidth)/2; //左边位置
        int headTop = 1+headMetrics.getAscent(); //顶边位置+上升距离（原本字体基线位置对准画布的y坐标导致字体偏上ascent距离，加上ascent后下移刚好顶边吻合）
        g2.drawString(headStr,headLeft,headTop);

        //左边信息部分
        g2.fillRect(0,20,oldWidth/2,80);
        g2.setColor(Color.white);
        g2.fillRect(0,20,oldWidth/2,80);
        g2.setFont(new Font("宋体",Font.PLAIN,16));
        FontDesignMetrics midLeftMetrics = FontDesignMetrics.getMetrics(new Font("宋体",Font.BOLD,16));
        g2.setColor(Color.black);
        String midLeft1 = "单位："+goods.getGoodsUnit();
        int midLeftAscent = midLeftMetrics.getAscent();
        g2.drawString(midLeft1,0,22+midLeftAscent);
        String midLeft2 = "规格："+goods.getGoodsSpecs();
        g2.drawString(midLeft2,0,42+midLeftAscent);
        String midLeft3 = "产地："+goods.getGoodsPlace();
        g2.drawString(midLeft3,0,62+midLeftAscent);
        String midLeft4 = "编码："+goods.getGoodsCode();
        g2.drawString(midLeft4,0,82+midLeftAscent);

        //右边价格部分
        String price = goods.getGoodsPrice()+"元";
        g2.fillRect(oldWidth/2,20,oldWidth/2,40);
        g2.setColor(Color.white);
        g2.fillRect(oldWidth/2,20,oldWidth/2,40);
        g2.setFont(new Font("幼圆",Font.BOLD,36));
        FontDesignMetrics midRightMetrics = FontDesignMetrics.getMetrics(new Font("幼圆", Font.BOLD, 36));
        g2.setColor(Color.black);
        int midRight1Width = midRightMetrics.stringWidth(price);
        int midRight1Ascent = oldWidth/2+(oldHeight/2-midRight1Width)/2;
        g2.drawString(price,midRight1Ascent,22+midRightMetrics.getAscent());

//        //截取价格部分的整数和小数部分
//        String price = goods.getGoodsPrice();
//        String integerPrice = price.substring(0, price.lastIndexOf(".")+1 );
//        String doublePrice = price.substring(price.lastIndexOf(".") + 1);
//        //右边价格整数部分
//        g2.fillRect(oldWidth/2,20,oldWidth/4,40);
//        g2.setColor(Color.white);
//        g2.fillRect(oldWidth/2,20,oldWidth/4,40);
//        g2.setFont(new Font("幼圆",Font.BOLD,36));
//        FontDesignMetrics midRightMetrics = FontDesignMetrics.getMetrics(new Font("幼圆", Font.BOLD, 36));
//        g2.setColor(Color.black);
//        String midRight1 = integerPrice;
//        int midRight1Width = midRightMetrics.stringWidth(midRight1);
//        int midRight1Ascent = oldWidth/2+(oldHeight/2-midRight1Width)/2;
//        g2.drawString(midRight1,midRight1Ascent,20+midRightMetrics.getAscent());
//
//        //右边价格小数和单位部分
//        g2.fillRect(3*oldWidth/4,20,oldWidth-oldWidth/2-oldWidth/4,40);
//        g2.setColor(Color.white);
//        g2.fillRect(3*oldWidth/4,20,oldWidth-oldWidth/2-oldWidth/4,40);
//        g2.setFont(new Font("宋体",Font.PLAIN,20));
//        FontDesignMetrics midRight1Metrics = FontDesignMetrics.getMetrics(new Font("宋体", Font.PLAIN, 24));
//        int midRight1MetricsHeight = midRight1Metrics.getHeight();
//        g2.setColor(Color.black);
//        String midRight2 = doublePrice;
//        String midRight3 = "元";
//        g2.drawString(midRight2,3*oldWidth/4,20+midRight1Metrics.getAscent());
//        g2.drawString(midRight3,3*oldWidth/4+midRight1Metrics.stringWidth(midRight2),midRight1Metrics.getAscent()+midRight1MetricsHeight-3);

        //右边的编码和条形码部分（条形码还没画）
        g2.fillRect(oldWidth/2,60,oldWidth/2,40);
        g2.setColor(Color.white);
        g2.fillRect(oldWidth/2,60,oldWidth/2,40);
        g2.setFont(new Font("宋体",Font.PLAIN,18));
        g2.setColor(Color.black);
        String midRightDown = goods.getGoodsBarcode();
        FontDesignMetrics midRightDownMetrics = FontDesignMetrics.getMetrics(new Font("宋体", Font.PLAIN, 18));
        int stringWidth = midRightDownMetrics.stringWidth(midRightDown);
        g2.drawString(midRightDown,oldWidth/2,70+midRightDownMetrics.getAscent());

        //最下面的监督和举报电话
        g2.fillRect(0,102,oldWidth,20);
        g2.setColor(Color.white);
        g2.fillRect(0,102,oldWidth,20);
        g2.setFont(new Font("宋体",Font.PLAIN,14));
        g2.setColor(Color.black);
        String down = "举报监督电话：12358 ，"+goods.getGoodsTell();
        FontDesignMetrics downMetrics = FontDesignMetrics.getMetrics(new Font("宋体", Font.PLAIN, 14));
        int downWidth = downMetrics.stringWidth(down);
        g2.drawString(down,(oldWidth-downWidth)/2,104+downMetrics.getAscent());

        FileUtil fileUtil = new FileUtil();
        String fileName = fileUtil.creatFileName()+".bmp";

        String fileClassPath = path+fileName;
        ImageIO.write(bi,"bmp",new FileOutputStream(fileClassPath));//保存图片 bmp表示保存格式
        return fileClassPath;
    }

}
