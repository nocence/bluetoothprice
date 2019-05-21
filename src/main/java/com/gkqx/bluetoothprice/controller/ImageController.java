package com.gkqx.bluetoothprice.controller;

import com.gkqx.bluetoothprice.cache.ImagesCachePool;
import com.gkqx.bluetoothprice.cache.SessionCache;
import com.gkqx.bluetoothprice.common.resultCommon.ResultCommon;
import com.gkqx.bluetoothprice.dto.Result;
import com.gkqx.bluetoothprice.mina.ServerHandler;
import com.gkqx.bluetoothprice.mina.SessionMap;
import com.gkqx.bluetoothprice.model.*;
import com.gkqx.bluetoothprice.service.GoodsService;
import com.gkqx.bluetoothprice.service.ImagesService;
import com.gkqx.bluetoothprice.service.StoreService;
import com.gkqx.bluetoothprice.service.WifisService;
import com.gkqx.bluetoothprice.util.byteUtil.ByteUtil;
import com.gkqx.bluetoothprice.util.fileUtil.FileUtil;
import com.gkqx.bluetoothprice.util.imgUtil.DrawImg;
import com.gkqx.bluetoothprice.util.socketUtil.AllMsg;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * @ClassName ImageController
 * @Description 图片相关操作
 * @Author Innocence
 * @Date 2019/5/8 000809:35
 * @Version 1.0
 **/
@RestController
@RequestMapping("image")
public class ImageController {

    @Autowired
    private ImagesService imagesService;

    @Autowired
    private GoodsService goodsService;

    @Autowired
    private StoreService storeService;

    @Autowired
    private WifisService wifisService;
    private java.lang.Object Object;

    /**
    * 处理生成图片的请求
    * @author Innocence
    * @date 2019/5/8 000815:51
    * @param goods, request
    * @return com.gkqx.bluetoothprice.dto.Result
    */
    @RequestMapping("creatImg")
    @ResponseBody
    public Result creatImageByGoods(Goods goods, HttpServletRequest request){
        Result res = new Result();
        System.out.println("生成图片的请求到达");
        //项目路径
        String path = request.getSession().getServletContext().getRealPath("/");
        System.out.println("项目路径："+path);
        DrawImg drawImg = new DrawImg();
        String imgPath = null;
        try {
            imgPath = drawImg .DrawInfo(goods, path);
            //将图片信息存入数据库
            //获取图片名称
            String imgName = imgPath.substring(imgPath.lastIndexOf("\\") + 1);
            System.out.println("图片名："+imgName);
            Images images = new Images();
            //根据商品名和规格获取商品实体类
            Goods oneGoodsByNameAndSpces = goodsService.getOneGoodsByNameAndSpces(goods);
            images.setGoodsId(oneGoodsByNameAndSpces.getGoodsId());
            images.setImgName(imgName);
            images.setImgPath(imgPath);
            Integer imgId = imagesService.insertImageInfomation(images);
            if(imgId!=null){
                res.setCode(ResultCommon.SUCCESS_CODE);
                res.setMsg(imgPath);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return res;
    }

    /**
    * 处理前端遍历所有图片的请求
    * @author Innocence
    * @date 2019/5/8 000815:52
    * @param goods, images
    * @return com.gkqx.bluetoothprice.dto.Result
    */
    @RequestMapping("showAllImages")
    @ResponseBody
    public Result showAllInahes(Goods goods,Images images){
        Result res = new Result();
        System.out.println("查询所有图片请求到达");
        List<GoodsImages> allImages = imagesService.getAllImages(images, goods);
        FileUtil util = new FileUtil();
        //将图片进行base64编码
        for (GoodsImages i:allImages) {
            String baseImg = util.getBaseImg(i.getImageUrl());
            i.setImageUrl(baseImg);
        }
        res.setCode(ResultCommon.SUCCESS_CODE);
        res.setList(allImages);
        return res;
    }

    /**
    * 获取所有门店
    * @author Innocence
    * @date 2019/5/10 001011:06
    * @param
    * @return
    */
    @RequestMapping("getAllStores")
    @ResponseBody
    public Result getAllStores(Store store){
        Result res = new Result();
        List<Store> allStores = storeService.getAllStores(store);
        if(!allStores.isEmpty()){
            res.setCode(ResultCommon.SUCCESS_CODE);
            res.setList(allStores);
        }else{
            res.setCode(ResultCommon.FAILED_CODE);
        }
        return res;
    }

    /**
    * 获取门店下的wifi
    * @author Innocence
    * @date 2019/5/10 001011:08
    * @param
    * @return
    */
    @RequestMapping("getAllWifis")
    @ResponseBody
    public Result getAllWifis(StoreWifis storeWifis){
        Result res = new Result();
        List<StoreWifis> allWifis = wifisService.getAllWifis(storeWifis);
        if(!allWifis.isEmpty()){
            res.setCode(ResultCommon.SUCCESS_CODE);
            res.setList(allWifis);
        }else{
            res.setCode(ResultCommon.FAILED_CODE);
        }
        return res;
    }
    /**
    * 发送图片到指定的wifi
    * @author Innocence
    * @date 2019/5/10 001011:21
    * @param
    * @return
    */
    @Async
    @RequestMapping("sendImage")
    @ResponseBody
    public Result sendImage(ImageToWifi imageToWifi,Images images) throws FileNotFoundException {
        Result res = new Result();
        //根据图片名查询图片路径
        String imageName = imageToWifi.getImageName();
        images.setImgName(imageName);
        Images image = imagesService.getImage(images);
        System.out.println("获取图片路径："+image.getImgPath());
        AllMsg msg = new AllMsg();
        byte[] hex = msg.hex(image.getImgPath());

        SessionMap sessionMap = SessionMap.newInstance();
        IoSession session = sessionMap.getSession(imageToWifi.getWifiIp());
        if(session!=null){
            // 组装待发送图片
            Images sendImg = new Images(session.getId(), ByteUtil.bytes2Queue(hex), imageToWifi.getWifiIp());
            // 图片加入发送图片池
            ImagesCachePool.addImages(session.getId(), sendImg);
            // 获取实际发送数据
            byte[] snedBytes = ByteUtil.queueOutByte(sendImg.getImgQueue(), sendImg.getSize());
            // 发送图片
            sessionMap.sendMsgToOne(imageToWifi.getWifiIp(), IoBuffer.wrap(snedBytes));
            // 将发送时的时间存入session，便于判断响应超时
            session.setAttribute("beginTime",System.currentTimeMillis());
            // 获取mina监听的返回值，判定是否发送成功
            Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    if(session.getAttribute("successCode")!=null){
                        String successCode = (String)session.getAttribute("successCode");
                        if (successCode.equals("succeed")){
                            res.setCode(ResultCommon.SUCCESS_CODE);
                            timer.cancel();
                        }else if (successCode.equals("failed")){
                            res.setCode(ResultCommon.FAILED_CODE);
                            timer.cancel();
                        }
                    }
                }
            },100,1000);
        }else {
            res.setCode(ResultCommon.FAILED_CODE);
        }
        System.out.println("发送图片的成败标识符："+res.getCode());
        return res;
    }
}
