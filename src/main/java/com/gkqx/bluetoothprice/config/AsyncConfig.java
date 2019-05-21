package com.gkqx.bluetoothprice.config;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import java.util.concurrent.Executor;

/**
 * @ClassName AsyncConfig
 * @Description springboot多线程配置
 * @Author Innocence
 * @Date 2019/5/17 001709:43
 * @Version 1.0
 **/
@Configuration
@EnableAsync
public class AsyncConfig  implements AsyncConfigurer {

    @Bean
    public Executor getAsyncExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(5);// 最小线程数
        taskExecutor.setMaxPoolSize(10);// 最大线程数
        taskExecutor.setQueueCapacity(25);// 等待队列

        taskExecutor.initialize();

        return taskExecutor;
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return AsyncConfigurer.super.getAsyncUncaughtExceptionHandler();
    }
}
