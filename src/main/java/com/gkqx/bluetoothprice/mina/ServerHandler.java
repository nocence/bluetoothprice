package com.gkqx.bluetoothprice.mina;

/**
* @ClassName ServerHandler
* @Description mina框架的一些方法
* @Author Innocence
* @Date 2019/4/26 002616:36
* @Version 1.0
**/

import com.gkqx.bluetoothprice.cache.ImagesCachePool;
import com.gkqx.bluetoothprice.model.Images;
import com.gkqx.bluetoothprice.model.Tags;
import com.gkqx.bluetoothprice.model.Wifis;
import com.gkqx.bluetoothprice.service.TagsService;
import com.gkqx.bluetoothprice.service.WifisService;
import com.gkqx.bluetoothprice.util.byteUtil.ByteUtil;
import org.apache.mina.core.buffer.IoBuffer;
import org.apache.mina.core.service.IoHandlerAdapter;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.core.session.IoSession;
import org.apache.mina.transport.socket.SocketSessionConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.net.InetSocketAddress;
import java.util.Queue;

import static com.gkqx.bluetoothprice.common.socketComon.SocketCommon.*;


@Component
public class ServerHandler extends IoHandlerAdapter {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /* 在本类中调用其他service需要使用以下方法*/

    //1.声明service类
    @Autowired
    private TagsService tagsService;

    @Autowired
    private WifisService wifisService;

    private static ServerHandler serverHandler ;

    //2通过@PostConstruct实现初始化bean之前进行的操作
    @PostConstruct
    public void init() {
        serverHandler = this;
        serverHandler.tagsService = this.tagsService;
        serverHandler.wifisService = this.wifisService;
        //3.调用时需要加前缀 如 serverHandler.tbBoxService
    }

    @Override
    public void sessionCreated(IoSession session) throws Exception { //用户连接到服务器
        SocketSessionConfig cfg = (SocketSessionConfig) session.getConfig();
        cfg.setSoLinger(0);
        //将连接的用户session会话进行保存,以获取的用户ip为key
        String hostAddress = ((InetSocketAddress) session.getRemoteAddress()).getAddress().getHostAddress();
        SessionMap sessionMap = SessionMap.newInstance();
        sessionMap.addSession(hostAddress,session);
        logger.info("session:"+session);
        logger.info("[服务建立]" + hostAddress);
    }

    @Override
    public void messageReceived(IoSession session, Object message)throws Exception {//接收消息
        String hostAddress = ((InetSocketAddress) session.getRemoteAddress()).getAddress().getHostAddress();
        ByteUtil util = new ByteUtil();
        IoBuffer bbuf = (IoBuffer) message;
        byte[] byten = new byte[bbuf.limit()];
        bbuf.get(byten, bbuf.position(), bbuf.limit());
        String temp=util.bytesToHexString(byten);
        temp = temp.trim();

        //收到消息之后，先将消息转化成字符串
        //判断消息内容，如果包含与硬件端约定的标识符，则调用service进行对应的操作
        String stringHex = util.toStringHex(temp);
        if(stringHex.contains(MAC) && stringHex.contains(PID) && stringHex.contains(PIX)){
            //判断价签信息是否已经存在
            //获取消息里面的mac地址
            String macAddress = stringHex.substring(SIGN_LENGTH, SIGN_LENGTH+MAC_DATA_LENGTH);
            Tags tags = new Tags();
            tags.setMacAddress(macAddress);
            Tags oneTagByMacAddress = serverHandler.tagsService.getOneTagByMacAddress(tags);
            if (oneTagByMacAddress==null){
                //根据ip获取wifi实体
                Wifis wifis = new Wifis();
                wifis.setWifiIp(hostAddress);
                Wifis oneByWifisIp = serverHandler.wifisService.getOneByWifisIp(wifis);
                //将获取到的wifiid赋值给tags实体
                tags.setWifiId(oneByWifisIp.getWifiId());
                //获取消息里的商品id
                String goodsInitId = stringHex.substring(SIGN_LENGTH*2+MAC_DATA_LENGTH, SIGN_LENGTH*2+MAC_DATA_LENGTH+PID_DATA_LENGTH);
                String goodsId ;
                //价签第一次连上的时候可能没有商品id，所以要判定,i2==0,说明没有商品id的信息内容
                if(goodsInitId.equals(INIT_PID)){
                    goodsId = null;
                    //没有商品时， 默认价签没有使用
                    tags.setType(0);
                }else {
                    goodsId = goodsInitId;
                    //有商品时默认价签在使用
                    tags.setType(1);
                }
                tags.setGoodsId(goodsId);
                //获取消息里的像素信息
                String picpx = stringHex.substring(SIGN_LENGTH*3+MAC_DATA_LENGTH+PID_DATA_LENGTH);
                tags.setPicPx(picpx);
                //将tags信息存入数据库
                Integer integer = serverHandler.tagsService.insertTag(tags);
                if(integer != 0 || integer != null){
                    logger.info("价签："+macAddress+"已入库");
                    session.write(IoBuffer.wrap(SUCCESS_RETURN.getBytes()));
                }else{
                    logger.info("价签："+macAddress+"入库失败");
                    session.write(IoBuffer.wrap(FEILED_RETURN.getBytes()));
                }
            }else {
                logger.info("价签："+macAddress+"已存在");
                session.write(IoBuffer.wrap(FEILED_RETURN.getBytes()));
            }
        }

        //定义布尔值用来判定发送图片后第一次接收到响应是否超时
        boolean flag = true;
        if(session.getAttribute("beginTime")!=null){
            long nowTime = System.currentTimeMillis();
            long beginTime = (long) session.getAttribute("beginTime");
            if ((nowTime-beginTime)>1000*10)flag=false;
            //判定之后把session移除，否则beginTime一直存在，当发送总时长超过规定时间，剩下的数据就发送不了
            session.removeAttribute("beginTime");
        }
        //主要是判定关于图片单点发送时，硬软件端的通信
        if(stringHex.equals(SUCCESS_RETURN)&& flag==true){// 收到OK报文 持续发送图片
            if (session.getAttribute("successCode")!=null)session.removeAttribute("successCode");
            System.out.println("收到客户端OK消息，继续发送图片......");
            //这里判定从第二次收到响应之后，有没有超时
            long lastTime = System.currentTimeMillis();
            boolean isSend = false;
            if (session.getAttribute("secondTime")!=null){
                long secondTime = (Long)session.getAttribute("secondTime");
                if ((lastTime-secondTime)>1000*10){
                    isSend=false;
                }else{
                    isSend=true;
                }
            }
            if (isSend==true ||session.getAttribute("secondTime")==null){
                SessionMap sessionMap = SessionMap.newInstance();
                //单点发送
                // 从缓存池获取对应会话待发送图片
                Images sendImg = ImagesCachePool.getImages(session.getId());
                // 获取实际发送数据大小
                byte[] sendBytes = null;
                if (sendImg!=null) {
                    sendBytes = util.queueOutByte(sendImg.getImgQueue(), sendImg.getSize());
                }

                if( sendBytes.length > 0) {
                    sessionMap.sendMsgToOne(sendImg.getWifiIP(), IoBuffer.wrap(sendBytes));
                    //如果session存了值，要清空，否则上面的超时判断会一直为false
                    if (session.getAttribute("secondTime")!=null)session.removeAttribute("secondTime");
                    session.setAttribute("secondTime",System.currentTimeMillis());
                }else {// 图片发送完成
                    // 清除缓存
                    ImagesCachePool.removeImages(sendImg.getSessionID());
                    // 发送成功之后存入成功标识符，给发请请求的controller判定是否发送成功
                    session.setAttribute("successCode", "succeed");
                }
            }else {
                if (session.getAttribute("secondTime")!=null)session.removeAttribute("secondTime");
                session.setAttribute("successCode","failed");
            }
        }else if(stringHex.equals(FEILED_RETURN) || flag==false){
            //收到ERROR消息或者超时，暂停发送，清除缓存并返回失败标识
            //因为还可能收到其他的无需处理的请求，所以这里的判定写准确，以免被其他消息干扰
            Images sendImg = ImagesCachePool.getImages(session.getId());
            ImagesCachePool.removeImages( sendImg.getSessionID() );
            if (session.getAttribute("secondTime")!=null)session.removeAttribute("secondTime");
            session.setAttribute("successCode","failed");
        }else if(stringHex.contains(IMG_END)){
            System.out.println("图片发送完成："+stringHex);
            //图片发送完成
            Images sendImg = ImagesCachePool.getImages(session.getId());
            //发送完成后修改价签状态
            String goodid = stringHex.substring(6, 21);
            String mac = stringHex.substring(22);
            Tags tags = new Tags();
            tags.setGoodsId(goodid);
            tags.setMacAddress(mac);
            tags.setType(1);
            serverHandler.tagsService.updateTagOfGoodsId(tags);
            ImagesCachePool.removeImages( sendImg.getSessionID());
            session.setAttribute("successCode","succeed");
            //将二次验证超时的session清除，否则第二次发送图片就会false
            if (session.getAttribute("secondTime")!=null)session.removeAttribute("secondTime");
        }
        //群发图片的消息处理
        if (stringHex.equals(ALL_SUCCESS_RETURN) && flag==true){// 收到OK报文 持续发送图片
            if (session.getAttribute("successCode")!=null)session.removeAttribute("successCode");
            System.out.println("收到客户端OK消息，继续发送图片......");
            //这里判定从第二次收到响应之后，有没有超时
            long lastTime = System.currentTimeMillis();
            boolean isSend = false;
            if (session.getAttribute("secondTime")!=null){
                long secondTime = (Long)session.getAttribute("secondTime");
                if ((lastTime-secondTime)>1000*10){
                    isSend=false;
                }else{
                    isSend=true;
                }
            }
            if (isSend == true || session.getAttribute("secondTime")==null){
                SessionMap sessionMap = SessionMap.newInstance();
                // 从缓存池获取对应会话待发送图片,如果缓存池里有数据，直接发。
                // 如果没有，则去拿缓存队列里的数据
                Images sendImg = ImagesCachePool.getImages(session.getId());
                if (sendImg == null){
                    Queue<Images> imagesQueue = ImagesCachePool.getImagesQueue(IMG_CACHE);
                    Images getQueueImages = imagesQueue.poll();
                    if (getQueueImages!=null){
                        ImagesCachePool.addImages(getQueueImages.getSessionID(),getQueueImages);
                        byte[] queueOutByte = util.queueOutByte(getQueueImages.getImgQueue(), getQueueImages.getSize());
                        sessionMap.sendMsgToOne(getQueueImages.getWifiIP(),IoBuffer.wrap(queueOutByte));
                    }
                    //如果session存了值，要清空，否则上面的超时判断会一直为false
                    if (session.getAttribute("secondTime")!=null)session.removeAttribute("secondTime");
                    session.setAttribute("secondTime",System.currentTimeMillis());

                }else{
                    byte[] outByte = util.queueOutByte(sendImg.getImgQueue(), sendImg.getSize());
                    if (outByte.length>0){
                        sessionMap.sendMsgToOne(sendImg.getWifiIP(), IoBuffer.wrap(outByte));
                        if (session.getAttribute("secondTime")!=null)session.removeAttribute("secondTime");
                        session.setAttribute("secondTime",System.currentTimeMillis());
                    }else{
                        // 清除缓存
                        ImagesCachePool.removeImages(sendImg.getSessionID());
                    }
                }
            }else{
                if (session.getAttribute("secondTime")!=null)session.removeAttribute("secondTime");
                SessionMap sessionMap = SessionMap.newInstance();
                //收到ERROR消息或者超时，暂停当前图片发送，清除当前缓存并发下一张
                if (ImagesCachePool.getImages(session.getId())!=null){
                    ImagesCachePool.removeImages(session.getId());
                }
                //拿到缓存队列的下一张图片并发送
                if (ImagesCachePool.getImagesQueue(IMG_CACHE) != null){
                    Queue<Images> imagesQueue = ImagesCachePool.getImagesQueue(IMG_CACHE);
                    Images getQueueImages = imagesQueue.poll();
                    if (getQueueImages!=null){
                        ImagesCachePool.addImages(getQueueImages.getSessionID(),getQueueImages);
                        byte[] queueOutByte = util.queueOutByte(getQueueImages.getImgQueue(), getQueueImages.getSize());
                        sessionMap.sendMsgToOne(getQueueImages.getWifiIP(),IoBuffer.wrap(queueOutByte));
                    }
                    //如果session存了值，要清空，否则上面的超时判断会一直为false
                    if (session.getAttribute("secondTime")!=null)session.removeAttribute("secondTime");
                    session.setAttribute("secondTime",System.currentTimeMillis());
                }
            }
        }else if (stringHex.equals(ALL_FEILED_RETURN) || flag == false){
            SessionMap sessionMap = SessionMap.newInstance();
            //收到ERROR消息或者超时，暂停当前图片发送，清除当前缓存并发下一张
            if (ImagesCachePool.getImages(session.getId())!=null){
                ImagesCachePool.removeImages(session.getId());
            }
            //拿到缓存队列的下一张图片并发送
            if (ImagesCachePool.getImagesQueue(IMG_CACHE) != null){
                Queue<Images> imagesQueue = ImagesCachePool.getImagesQueue(IMG_CACHE);
                Images getQueueImages = imagesQueue.poll();
                if (getQueueImages!=null){
                    ImagesCachePool.addImages(getQueueImages.getSessionID(),getQueueImages);
                    byte[] queueOutByte = util.queueOutByte(getQueueImages.getImgQueue(), getQueueImages.getSize());
                    sessionMap.sendMsgToOne(getQueueImages.getWifiIP(),IoBuffer.wrap(queueOutByte));
                }
                //如果session存了值，要清空，否则上面的超时判断会一直为false
                if (session.getAttribute("secondTime")!=null)session.removeAttribute("secondTime");
                session.setAttribute("secondTime",System.currentTimeMillis());
            }
        }else if (stringHex.equals(ALL_IMG_END)){
            //图片发送完成
            SessionMap sessionMap = SessionMap.newInstance();
            //收到ERROR消息或者超时，暂停当前图片发送，清除当前缓存并发下一张
            if (ImagesCachePool.getImages(session.getId())!=null){
                Images sendImg = ImagesCachePool.getImages(session.getId());
//                byte[] queueOutByte = util.queueOutByte(sendImg.getImgQueue(), sendImg.getSize());
//                byte[] subByte = util.subByte(queueOutByte, 4, 28);
//                String subStr = util.toStringHex(util.bytesToHexString(subByte));
//                Tags tags = new Tags();
//                tags.setGoodsId(subStr.substring(0,16));
//                tags.setMacAddress(subStr.substring(16));
//                tags.setType(1);
//                serverHandler.tagsService.updateTagOfGoodsId(tags);
                ImagesCachePool.removeImages(sendImg.getSessionID());
            }
            //拿到缓存队列的下一张图片并发送
            if (ImagesCachePool.getImagesQueue(IMG_CACHE) != null){
                Queue<Images> imagesQueue = ImagesCachePool.getImagesQueue(IMG_CACHE);
                Images getQueueImages = imagesQueue.poll();
                if (getQueueImages!=null){
                    ImagesCachePool.addImages(getQueueImages.getSessionID(),getQueueImages);
                    byte[] queueOutByte = util.queueOutByte(getQueueImages.getImgQueue(), getQueueImages.getSize());
                    sessionMap.sendMsgToOne(getQueueImages.getWifiIP(),IoBuffer.wrap(queueOutByte));
                }else {
                    ImagesCachePool.removeImagesQueue(IMG_CACHE);
                }
                //如果session存了值，要清空，否则上面的超时判断会一直为false
                if (session.getAttribute("secondTime")!=null)session.removeAttribute("secondTime");
                session.setAttribute("secondTime",System.currentTimeMillis());
            }
        }

        logger.info("[收到消息,来自："+hostAddress+"]" + stringHex);

    }

    @Override
    public void sessionClosed(IoSession session) throws Exception {   //用户从服务器断开
        logger.info("[服务断开]" + ((InetSocketAddress) session.getRemoteAddress()).getAddress().getHostAddress());

    }
    @Override
    public void messageSent(IoSession session, Object message){ //发送消息结束
        logger.info("[发送消息结束]" + ((InetSocketAddress) session.getRemoteAddress()).getAddress().getHostAddress() + "message" + message);
    }


    @Override
    public void sessionIdle(IoSession session, IdleStatus status)throws Exception {//重连
        logger.info("[服务重连]" + ((InetSocketAddress) session.getRemoteAddress()).getAddress().getHostAddress() + "status" + status.toString());
    }


    @Override
    public void exceptionCaught(IoSession session, Throwable cause)throws Exception {//连接发生异常
        cause.printStackTrace();
        logger.info("[服务异常]" + ((InetSocketAddress) session.getRemoteAddress()).getAddress().getHostAddress());
    }

    @Override
    public void sessionOpened(IoSession session) throws Exception {
        logger.info("[连接被打开]" + ((InetSocketAddress) session.getRemoteAddress()).getAddress().getHostAddress());
    }

}

