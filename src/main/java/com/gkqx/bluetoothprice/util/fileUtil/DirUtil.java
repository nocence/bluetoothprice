package com.gkqx.bluetoothprice.util.fileUtil;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @ClassName DirUtil
 * @Description 文件夹操作工具
 * @Author Innocence
 * @Date 2019/4/19 001915:18
 * @Version 1.0
 **/
public class DirUtil {
    /**
    * 在本地指定位置创建文件夹
    * @author Innocence
    * @date 2019/4/19 001915:19
    * @param
    * @return dirName 文件夹路径
    */
    public String  creatDir(String projectPath){
        String dirName= projectPath + File.separator;
        File dir = new File(dirName);
        // 根据年月日生成文件夹
        Date date = new Date();
        String year = new SimpleDateFormat("yyyy").format(date);
        String month = new SimpleDateFormat("MM").format(date);
        String day = new SimpleDateFormat("dd").format(date);
        // 文件夹路径
        dirName = dirName + "created" + "/" + year + "/" + month + "/" + day;

        if(dir.exists()){
            System.out.println("创建文件夹"+dirName+"失败，文件目录已存在！");
            return dirName;
        }
        if(!dirName.endsWith(File.separator)){
            dirName = dirName + File.separator;
        }
        //创建目录
        if(dir.mkdirs()){
            return dirName;
        }else{
            return "error";
        }
    }
}
