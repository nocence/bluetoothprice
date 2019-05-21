//package com.gkqx.bluetoothprice.controller;
//
//import com.gkqx.bluetoothprice.dto.Result;
//import com.gkqx.bluetoothprice.mina.SessionMap;
//import com.gkqx.bluetoothprice.model.ImgInfo;
//import com.gkqx.bluetoothprice.serviceImpl.ImgInfoServiceImpl;
//import com.gkqx.bluetoothprice.util.imgUtil.DrawImg;
//import com.gkqx.bluetoothprice.util.pathUtil.PathUtil;
//import com.gkqx.bluetoothprice.util.socketUtil.AllMsg;
//import org.apache.mina.core.buffer.IoBuffer;
//import org.apache.mina.core.session.IoSession;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.ResponseBody;
//import org.springframework.web.bind.annotation.RestController;
//
//import javax.servlet.http.HttpServletRequest;
//import java.io.FileNotFoundException;
//import java.io.IOException;
//
///**
// * @ClassName ImgControl
// * @Description 处理前台传入数据生成图片
// * @Author Innocence
// * @Date 2019/4/22 002215:26
// * @Version 1.0
// **/
//@RestController
//@RequestMapping("img")
//public class ImgControl {
//
//    @Autowired
//    private ImgInfoServiceImpl imgInfoService;
//
//    @RequestMapping("imginfo")
//    @ResponseBody
//    public Result creatImg(ImgInfo imgInfo, HttpServletRequest request) throws FileNotFoundException {
//        String foreverPath = new PathUtil().foreverPath("F:/LymTools/bluetoothprice/src/main/resources/static/images/");
//        System.out.println(foreverPath);
//        System.out.println("接收到前端发送的图片内容信息");
//        Result res = new Result();
//        if(imgInfo!=null){
//            DrawImg drawImg = new DrawImg();
//            String imgPath = null;
//
//            try {
//                imgPath = drawImg.DrawInfo(imgInfo,foreverPath);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//            res.setCode(200);
//            res.setMsg(imgPath);
//            //将生成的图片名字和路径存入数据库
//            String name = imgPath.substring(imgPath.lastIndexOf("/")+1);
//            System.out.println(name);
//            imgInfo.setName(name);
//            imgInfo.setPath(imgPath);
//            imgInfoService.insertImgInfo(imgInfo);
//
//            return res;
//        }else{
//            res.setCode(400);
//            return res;
//        }
//
//    }
//    @RequestMapping("sendinfo")
//    @ResponseBody
//    public Result sendImg(String filePath) throws FileNotFoundException {
//        Result res = new Result();
//        //这里的filepath后期根据前台的数据从数据库取出
//        filePath = "F:/LymTools/bluetoothprice/src/main/resources/static/images/dc35adc4e6da44b8a1dff012fe50631c.bmp";
//        byte[] bytes = new AllMsg().hex(filePath);
//        SessionMap sessionMap = SessionMap.newInstance();
//
//        IoSession session = sessionMap.getSession("192.168.0.122");
//        if(session!=null){
//            sessionMap.sendMsgToOne("192.168.0.122", IoBuffer.wrap(bytes));
//            res.setCode(200);
//        }else{
//            res.setCode(400);
//        }
//        return res;
//    }
//}
