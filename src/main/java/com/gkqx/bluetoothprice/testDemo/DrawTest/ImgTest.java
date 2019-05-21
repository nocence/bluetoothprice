package com.gkqx.bluetoothprice.testDemo.DrawTest;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @ClassName ImgTest
 * @Description TODD
 * @Author Innocence
 * @Date 2019/4/16 001615:30
 * @Version 1.0
 **/
public class ImgTest {
    public static void main(String[] args) throws IOException {
        File file = new File("F:/GoogleDownload/downImg.png");
        FileOutputStream fos = new FileOutputStream("F:/GoogleDownload/after.jpg");
        ImgTest.thumbnail(file,250,122,fos);
        //转黑白
        BufferedImage img = ImageIO.read(new File("F:/GoogleDownload/after.jpg"));

        final int width = img.getWidth();
        final int height = img.getHeight();

        for (int i = 0; i < width; i++) {

            for (int j = 0; j < height; j++) {

                int rgb = img.getRGB(i, j);
                img.setRGB(i, j, grayRGB(Integer.toHexString(rgb)));
            }

        }

        ImageIO.write(img, "jpg", new File("F:/GoogleDownload/afterOne.jpg"));
    }
    /**
     * 按照 宽高 比例压缩
     *
     * @param img
     * @param width
     * @param height
     * @param out
     * @throws IOException
     */
    public static void thumbnail_w_h(File img, int width, int height,
                                     OutputStream out) throws IOException {
        BufferedImage bi = ImageIO.read(img);
        double srcWidth = bi.getWidth(); // 源图宽度
        double srcHeight = bi.getHeight(); // 源图高度

        double scale = 1;

        if (width > 0) {
            scale = width / srcWidth;
        }
        if (height > 0) {
            scale = height / srcHeight;
        }
        if (width > 0 && height > 0) {
            scale = height / srcHeight < width / srcWidth ? height / srcHeight
                    : width / srcWidth;
        }

        thumbnail(img, (int) (srcWidth * scale), (int) (srcHeight * scale), out);

    }
    /**
     * 按照固定宽高原图压缩
     *
     * @param img
     * @param width
     * @param height
     * @param out
     * @throws IOException
     */
    public static void thumbnail(File img, int width, int height,
                                 OutputStream out) throws IOException {
        BufferedImage BI = ImageIO.read(img);
        Image image = BI.getScaledInstance(width, height, Image.SCALE_SMOOTH);

        BufferedImage tag = new BufferedImage(width, height,
                BufferedImage.TYPE_INT_RGB);
        Graphics g = tag.getGraphics();
        g.setColor(Color.RED);
        g.drawImage(image, 0, 0, null); // 绘制处理后的图
        g.dispose();
        ImageIO.write(tag, "jpg", out);
    }
    /**
    * 彩色图片转黑白
    * @author Innocence
    * @date 2019/4/16 001617:04
    * @param
    * @return
    */
    private static int grayRGB(String argb) {
        //ARGB前两位是透明度,后面开始是RGB
        int r = Integer.parseInt(argb.substring(2, 4), 16);
        int g = Integer.parseInt(argb.substring(4, 6), 16);
        int b = Integer.parseInt(argb.substring(6, 8), 16);
        //平均值
        String average = Integer.toHexString((r + g + b) / 3);

        if (average.length() == 1) {
            average = "0" + average;
        }
        //RGB都变成平均值
        return Integer.parseInt(average + average + average, 16);
    }
}
