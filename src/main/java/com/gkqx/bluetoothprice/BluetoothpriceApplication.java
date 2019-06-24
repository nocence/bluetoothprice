package com.gkqx.bluetoothprice;

import com.gkqx.bluetoothprice.mina.ServerHandler;
import org.apache.mina.core.service.IoAcceptor;
import org.apache.mina.core.session.IdleStatus;
import org.apache.mina.transport.socket.nio.NioSocketAcceptor;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatConnectorCustomizer;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.server.ConfigurableServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableAsync;

import java.net.InetSocketAddress;

import static com.gkqx.bluetoothprice.common.socketComon.SocketCommon.SERVER_PORT;
/*实现对多线程的支持*/
@EnableAsync
@SpringBootApplication
/*该注解方便后期的servlet,filter,listener直接通过注解注册*/
@ServletComponentScan
@MapperScan("com.gkqx.bluetoothprice.mapper")
public class BluetoothpriceApplication {

    public static void main(String[] args) {
        SpringApplication.run(BluetoothpriceApplication.class, args);
    }

    @Bean
    public IoAcceptor ioAcceptor() throws Exception {
        IoAcceptor acceptor=new NioSocketAcceptor();
        acceptor.setHandler(new ServerHandler());
        acceptor.getSessionConfig().setReadBufferSize(2048);
        acceptor.getSessionConfig().setIdleTime(IdleStatus.BOTH_IDLE, 100);
        acceptor.bind(new InetSocketAddress(SERVER_PORT));
        System.out.println("服务器在端口：" + SERVER_PORT + "已经启动");
        return acceptor;
    }
    @Bean
    public ConfigurableServletWebServerFactory webServerFactory() {
        TomcatServletWebServerFactory factory = new TomcatServletWebServerFactory();
        factory.addConnectorCustomizers((TomcatConnectorCustomizer) connector -> connector.setProperty("relaxedQueryChars", "|{}[]\\"));
        return factory;
    }
}
