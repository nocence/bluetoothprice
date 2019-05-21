package com.gkqx.bluetoothprice.util.pathUtil;

import javax.servlet.http.HttpServletRequest;

/**
 * @ClassName PathUtil
 * @Description 指定文件存放位置
 * @Author Innocence
 * @Date 2019/4/23 002311:23
 * @Version 1.0
 **/
public class PathUtil {
    /**
    * 存在项目目录下，重启项目或者清理Tomcat会把文件清空
    * @author Innocence
    * @date 2019/4/23 002311:24
    * @param
    * @return
    */
    public String interimPath(HttpServletRequest request){
        return request.getSession().getServletContext().getRealPath("/");
    }
    /**
    * 存在本地指定文件目录下
    * @author Innocence
    * @date 2019/4/23 002311:28
    * @param
    * @return
    */
    public String foreverPath(String path){
        return path;
    }
}
