package com.gkqx.bluetoothprice.mina;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.mina.core.session.IoSession;

import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName SessionMap
 * @Description 单例工具类，保存所有mina客户端连接
 * @Author Innocence
 * @Date 2019/4/28 002813:50
 * @Version 1.0
 **/
public class SessionMap {

    private final static Log log = LogFactory.getLog(SessionMap.class);

    private static SessionMap sessionMap = null;

    private Map<String, IoSession> map = new HashMap<String, IoSession>();

    //构造私有化单例
    private SessionMap(){}
    /**
    * 获取唯一实例
    * @author Innocence
    * @date 2019/4/28 002813:55
    * @param
    */
    public static SessionMap newInstance(){
        log.debug("SessionMap单例获取");
        if(sessionMap == null ){
            sessionMap = new SessionMap();
        }
        return sessionMap;
    }

    /**
    * 保存session会话
    * @author Innocence
    * @date 2019/4/28 002813:57
    * @param key, session
    * @return void
    */
    public void addSession(String key, IoSession session){
        log.debug("保存会话到SessionMap单例---key=" + key);
        this.map.put(key, session);
    }

    public IoSession getSession(String key){
        log.debug("获取会话从SessionMap单例---key=" + key);
        return this.map.get(key);
    }
    /**
    * 发送消息到客户端
    * @author Innocence
    * @date 2019/4/28 002814:03
    * @param keys, message
    * @return void
    */
    public void sendMessage(String[] keys, Object message){
        for(String key : keys){
            IoSession session = getSession(key);

            log.debug("反向发送消息到客户端Session---key=" + key + "----------消息=" + message);
            if(session == null)return;
            session.write(message);

        }
    }
    /**
    * 测试：服务端向指定的客户端发送消息
    * @author Innocence
    * @date 2019/4/28 002815:35
    * @param key, message
    * @return void
    */
    public void sendMsgToOne(String key,Object message){
        IoSession session = getSession(key);
        if(session==null)return;
        session.write(message);
    }

}
