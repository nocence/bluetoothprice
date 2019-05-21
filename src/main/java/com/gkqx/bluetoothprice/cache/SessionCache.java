package com.gkqx.bluetoothprice.cache;

import org.apache.mina.core.session.IoSession;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

/**
 * @ClassName SessionCache
 * @Description 用于缓存用户的session
 * @Author Innocence
 * @Date 2019/4/10 001011:32
 * @Version 1.0
 **/
public class SessionCache {
    private static List<IoSession> sessionList=new ArrayList<IoSession>();

    public static List<IoSession> getSessionList() {
        return sessionList;
    }

    public static void setSessionList(List<IoSession> sessionList) {
        SessionCache.sessionList = sessionList;
    }
}
