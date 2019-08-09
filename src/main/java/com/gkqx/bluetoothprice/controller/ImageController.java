package com.gkqx.bluetoothprice.controller;

import com.gkqx.bluetoothprice.cache.ImagesCachePool;
import com.gkqx.bluetoothprice.common.resultCommon.ResultCommon;
import com.gkqx.bluetoothprice.dto.Result;
import com.gkqx.bluetoothprice.mina.SessionMap;
import com.gkqx.bluetoothprice.model.*;
import com.gkqx.bluetoothprice.service.*;
import com.gkqx.bluetoothprice.util.byteUtil.ByteUtil;
import com.gkqx.bluetoothprice.util.connectionUtil.DuplicateRemoveUtil;
import com.gkqx.bluetoothprice.util.fileUtil.FileUtil;
import com.gkqx.bluetoothprice.util.imgUtil.DrawImg;
import com.gkqx.bluetoothprice.util.socketUtil.AllMsg;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.session.IoSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

import static com.gkqx.bluetoothprice.common.socketComon.SocketCommon.*;

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

    @Autowired
    private TagsService tagsService;


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
            imgPath = drawImg . DrawInfo(goods, path);
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
    * 根据WiFiIP获取其管理的所有使用中的价签
    * @author Innocence
    * @date 2019/5/22 002214:51
    * @param
    * @return
    */
    @RequestMapping("getAllTags")
    @ResponseBody
    public Result getAllTags(String wifiIp){
        Result res = new Result();
        List<Tags> allTags = tagsService.getAllTags(wifiIp);
        if (!allTags.isEmpty()){
            res.setCode(ResultCommon.SUCCESS_CODE);
            res.setList(allTags);
        }else {
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
        byte[] hex = msg.hex(image.getImgPath(),image.getGoodsId(),imageToWifi.getMacAddress(),POINT_IMG_SIGN);

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
            res.setCode(ResultCommon.SUCCESS_CODE);
        }else {
            res.setCode(ResultCommon.FAILED_CODE);
        }
        return res;
    }

    /**
    * 监听mina返回的状态值，判断是否发送成功
    * @author Innocence
    * @date 2019/5/23 002310:24
    * @param
    * @return
    */
    @RequestMapping("check")
    @ResponseBody
    public Result checkHeart(ImageToWifi imageToWifi){
        Result res = new Result();
        SessionMap sessionMap = SessionMap.newInstance();
        IoSession session = sessionMap.getSession(imageToWifi.getWifiIp());
        if (session.getAttribute("beginTime")!=null){
            long beginTime =(Long)session.getAttribute("beginTime");
            long nowTime = System.currentTimeMillis();
            if((nowTime- beginTime)>1000*2){
                res.setCode(ResultCommon.FAILED_CODE);
            }
        }else if(session.getAttribute("successCode")!=null){
            String successCode = (String)session.getAttribute("successCode");
            if (successCode.equals("succeed")){
                res.setCode(ResultCommon.SUCCESS_CODE);
            }else if (successCode.equals("failed")){
                res.setCode(ResultCommon.FAILED_CODE);
            }
        }else if(session.getAttribute("successCode")==null){
            return null;
        }
        return res;
    }

    /**
    * 根据文件名删除文件（Attention：有可能出现文件删除了，但是数据库没删掉，此时在遍历图片时就会报错）
    * @author Innocence
    * @date 2019/6/18 001815:53
    * @param
    * @return com.gkqx.bluetoothprice.dto.Result
    */
    @RequestMapping("deleteOne")
    @ResponseBody
    public Result deleteImageByImageName(ImageToWifi imageToWifi,Images images){
        Result res = new Result();
        //根据图片名查询图片路径
        String imageName = imageToWifi.getImageName();
        System.out.println("删除图片时获取的图片名"+imageName);
        images.setImgName(imageName);
        Images gotImage = imagesService.getImage(images);
        //删除文件
        FileUtil fileUtil = new FileUtil();
        boolean isDelete = fileUtil.deleteFile(gotImage.getImgPath());
        if(isDelete ==true){
            //删除数据库记录
            Integer integer = imagesService.deleteImageByImageName(images);
            if (integer!=null){
                res.setCode(ResultCommon.SUCCESS_CODE);
            }else{
                res.setCode(ResultCommon.FAILED_CODE);
            }
        }else {
            res.setCode(ResultCommon.FAILED_CODE);
        }
        return res;
    }

    /**
    * 批量发送图片
    * @author Innocence
    * @date 2019/6/21 002111:16
    * @param sendAllWifi, images
    * @return com.gkqx.bluetoothprice.dto.Result
    */
    @RequestMapping("sendAll")
    @ResponseBody
    public Result sendAllImages(@RequestBody ImageToWifi[] sendAllWifi, Images images) throws IOException {
        System.out.println("群发请求");
        Result res = new Result();
        AllMsg msg = new AllMsg();
        //第一步，根据传入的数据里面指定的ip获取session,存入list
        ArrayList<IoSession> ioSessions = new ArrayList<>();
        ArrayList<String> getIps = new ArrayList<>();
        for (int i = 0;i<sendAllWifi.length;i++){
            String wifiIp = sendAllWifi[i].getWifiIp();
            getIps.add(wifiIp);
        }
        //利用LinkedHashSet去除重复数据
        DuplicateRemoveUtil removeUtil = new DuplicateRemoveUtil();
        removeUtil.getSingle(getIps);
        SessionMap sessionMap = SessionMap.newInstance();
        for (int i = 0;i<getIps.size();i++){
            IoSession session = sessionMap.getSession(getIps.get(i));
            if (session!=null){
                ioSessions.add(session);
            }
        }
        //第二步，将要发往同一ip的图片组装进一个缓存
        Queue<Images> imagesQueue = new LinkedList<>();
        for(int i = 0;i<ioSessions.size();i++){
            for (int j = 0;j<sendAllWifi.length;j++){
                //组装单张图片
                String imageName = sendAllWifi[j].getImageName();
                images.setImgName(imageName);
                Images image = imagesService.getImage(images);
                byte[] hex = msg.hex(image.getImgPath(), image.getGoodsId(), sendAllWifi[j].getMacAddress(),CLUSTER_IMG_SIGN);
                //如果list里面的session与sessionmap里面根据WiFiIP获取的session相等且不为空，则对应组装图片
                if (sessionMap.getSession(sendAllWifi[j].getWifiIp()).equals(ioSessions.get(i))
                        && sessionMap.getSession(sendAllWifi[j].getWifiIp())!= null){
                    // 组装待发送图片
                    Images sendImg = new Images(ioSessions.get(i).getId(), ByteUtil.bytes2Queue(hex), sendAllWifi[j].getWifiIp());
                    //将图片对象装进队列
                    imagesQueue.offer(sendImg);
                }
            }
        }
        System.out.println("缓存数组长度："+imagesQueue.size());
        //先拿出第一个元素准备发送
        Images pollImage = imagesQueue.poll();
        //将拿出来的元素存入缓存池
        ImagesCachePool.addImages(pollImage.getSessionID(),pollImage);
        //将剩下的队列存入缓存池
        ImagesCachePool.addImagesQueue(IMG_CACHE,imagesQueue);
        //获取实际发送的数据
        byte[] outByte = ByteUtil.queueOutByte(pollImage.getImgQueue(), pollImage.getSize());
        sessionMap.sendMsgToOne(pollImage.getWifiIP(),IoBuffer.wrap(outByte));
        sessionMap.getSession(pollImage.getWifiIP()).setAttribute("beginTime",System.currentTimeMillis());
        res.setCode(ResultCommon.SUCCESS_CODE);
        return res;
    }

    /**
    * 批量发送时的心跳检测
    * @author Innocence
    * @date 2019/6/24 002416:30
    * @param sendAllWifi
    * @return com.gkqx.bluetoothprice.dto.Result
    */

    @RequestMapping("checkAll")
    @ResponseBody
    public Result checkAll(@RequestBody ImageToWifi[] sendAllWifi){
        Result res = new Result();
        SessionMap sessionMap = SessionMap.newInstance();
        int failedTotal = 0;
        int sucessTotal = 0;
        for (ImageToWifi itw:sendAllWifi) {
            IoSession session = sessionMap.getSession(itw.getWifiIp());
            if (session.getAttribute("beginTime")!=null){
                long beginTime =(Long)session.getAttribute("beginTime");
                long nowTime = System.currentTimeMillis();
                if((nowTime- beginTime)>1000*2){
                    failedTotal++;
                }
            }else if(session.getAttribute("successCode")!=null){
                String successCode = (String)session.getAttribute("successCode");
                if (successCode.equals("succeed")){
                    sucessTotal++;
                }else if (successCode.equals("failed")){
                    failedTotal++;
                }
            }else if(session.getAttribute("successCode")==null){
                return null;
            }
            res.setCode(ResultCommon.SUCCESS_CODE);
        }
        res.setMsg("成功发送"+sucessTotal+"张图，失败"+failedTotal+"张！");
        return res;
    }

}
