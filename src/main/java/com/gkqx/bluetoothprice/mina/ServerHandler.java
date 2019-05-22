package com.gkqx.bluetoothprice.mina;

/**
* @ClassName ServerHandler
* @Description mina框架的一些方法
* @Author Innocence
* @Date 2019/4/26 002616:36
* @Version 1.0
**/
import javax.annotation.PostConstruct;

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
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.net.InetSocketAddress;
import java.util.regex.Pattern;

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
    @Async
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
                }else {
                    goodsId = goodsInitId;
                }
                tags.setGoodsId(goodsId);
                //获取消息里的像素信息
                String picpx = stringHex.substring(SIGN_LENGTH*3+MAC_DATA_LENGTH+PID_DATA_LENGTH);
                tags.setPicPx(picpx);
                //type默认设置为1，表示在使用
                tags.setType(1);
                //将tags信息存入数据库
                Integer integer = serverHandler.tagsService.insertTag(tags);
                if(integer != 0 || integer != null){
                    logger.info("价签："+macAddress+"已入库");
                }else{
                    logger.info("价签："+macAddress+"入库失败");
                }
            }else {
                logger.info("价签："+macAddress+"已存在");
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

        if(stringHex.equals("OK")&& flag==true){// 收到OK报文 持续发送图片
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
                //如果session存了值，要清空，否则上面的超时判断会一直为false
                if (session.getAttribute("secondTime")!=null)session.removeAttribute("secondTime");
                SessionMap sessionMap = SessionMap.newInstance();
                // 从缓存池获取对应会话待发送图片
                Images sendImg = ImagesCachePool.getImages(session.getId());
                // 获取实际发送数据大小
                byte[] snedBytes = ByteUtil.queueOutByte(sendImg.getImgQueue(), sendImg.getSize());
                if( snedBytes.length > 0) {
                    sessionMap.sendMsgToOne(sendImg.getWifiIP(), IoBuffer.wrap(snedBytes));
                    session.setAttribute("secondTime",System.currentTimeMillis());
                }else {// 图片发送完成
                    // 清除缓存
                    ImagesCachePool.removeImages( sendImg.getSessionID() );
                    // 发送成功之后存入成功标识符，给发请请求的controller判定是否发送成功
                    session.setAttribute("successCode","succeed");
                    // 返回报文
                    sessionMap.sendMsgToOne(sendImg.getWifiIP(), IoBuffer.wrap("serverOK".getBytes()));
                }
            }else {
                session.setAttribute("successCode","failed");
            }
        }else{
            session.setAttribute("successCode","failed");
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

