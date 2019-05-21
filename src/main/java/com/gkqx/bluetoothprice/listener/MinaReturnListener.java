package com.gkqx.bluetoothprice.listener;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @ClassName MinaReturnListener
 * @Description 用于服务端发送数据时，监听mina返回的消息，根据消息决定是否继续发送剩下的数据
 * @Author Innocence
 * @Date 2019/5/16 001614:56
 * @Version 1.0
 **/
@WebListener
public class MinaReturnListener implements ServletContextListener {
    @Override
    public void contextDestroyed(ServletContextEvent arg0) {
        System.out.println("【监听器 MinaReturnListener】 已销毁");
    }
    @Override
    public void contextInitialized(ServletContextEvent arg0) {
        System.out.println("【监听器 MinaReturnListener】MinaReturnListener contextInitialized method ");
        System.out.println("在监听器中做一些预处理..........");

    }
}
