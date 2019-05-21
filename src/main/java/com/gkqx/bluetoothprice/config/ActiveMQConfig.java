//package com.gkqx.bluetoothprice.config;
//
//import org.apache.activemq.ActiveMQConnectionFactory;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
//import org.springframework.jms.config.JmsListenerContainerFactory;
//
//
//@Configuration
//public class ActiveMQConfig {
//
//    @Value("${spring.activemq.user}")
//    private String userName;
//
//    @Value("${spring.activemq.password}")
//    private String passWord;
//
//    @Value("${spring.activemq.broker-url}")
//    private String brokerUrl;
//
//    @Bean
//    public ActiveMQConnectionFactory connectionFactory(){
//        return new ActiveMQConnectionFactory(userName,passWord,brokerUrl);
//    }
//
//    @Bean
//    public JmsListenerContainerFactory<?> jmsListenerContainerQueue(ActiveMQConnectionFactory connectionFactory){
//        DefaultJmsListenerContainerFactory bean = new DefaultJmsListenerContainerFactory();
//        bean.setConnectionFactory(connectionFactory);
//        return bean;
//
//    }
//}
