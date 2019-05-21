package com.gkqx.bluetoothprice.util.fileUtil;

import org.springframework.web.multipart.MultipartFile;
import sun.misc.BASE64Encoder;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * @ClassName FileUtil
 * @Description 文件操作工具
 * @Author Innocence
 * @Date 2019/4/19 001915:45
 * @Version 1.0
 **/
public class FileUtil {
    /**
    * 根据当前系统时间生成字符串作为文件名字
    * @author Innocence
    * @date 2019/4/19 001915:46
    * @param
    * @return
    */
    public String creatFileName(){
        String uuid = UUID.randomUUID().toString().replaceAll("-", "");
        return uuid;
    }

    public String saveFile(String originalFilename, String projectPath, MultipartFile file) {
        try {
            // 获取文件后缀
            String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
            // 生成uuid的文件名
            String fileName = UUID.randomUUID().toString();
            // 文件名加后缀
            fileName = fileName + suffix;
            // 项目名+/
            String path = projectPath + File.separator;
            // 根据年月日生成文件夹
            Date date = new Date();
            String year = new SimpleDateFormat("yyyy").format(date);
            String month = new SimpleDateFormat("MM").format(date);
            String day = new SimpleDateFormat("dd").format(date);
            // 文件夹路径

            path = path + "uploaded" + "/" + year + "/" + month + "/" + day;
            path = path.replace("\\", "/");
            // 创建文件夹
            File f = new File(path);
            if (!f.exists()) {
                f.mkdirs();
            }
            // 文件全路径
            String url = path + "/" + fileName;
            File desFile = new File(url);
            // 保存文件到本地
            file.transferTo(desFile);
            String access = "uploaded" + "/" + year + "/" + month + "/" + day + "/" + fileName;

            return access;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    //图片文件的base64编码
    public String getBaseImg(String imgPath){

        InputStream in = null;
        byte[] data = null;
        try {
            in = new FileInputStream(imgPath);
            data = new byte[in.available()];
            in.read(data);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //进行Base64编码
        BASE64Encoder encoder = new BASE64Encoder();
        return encoder.encode(data);
    }
}
