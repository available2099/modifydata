package com.demo.ai.config;

import com.demo.ai.util.NamedThreadFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Configuration
public class BeanConfigration{
    private int getAvailableProcessors(){
        int availableProcessors = Runtime.getRuntime().availableProcessors();
        availableProcessors = availableProcessors * 4;// 暂时定为cpu核心的4倍，因为有其他可能导致cpu空闲的地方。给其他程序留点生机
        if (availableProcessors < 8) {
            availableProcessors = 8;
        }
        return availableProcessors;
    }

    @Bean("query.scheduler")
    public ExecutorService queryExecutor(){
        int availableProcessors = getAvailableProcessors();
        int nQueryThreads = 0;
        return Executors.newFixedThreadPool(nQueryThreads > 0 ? nQueryThreads : availableProcessors, new NamedThreadFactory("query-"));
    }
    @Bean("user.scheduler")
    public ExecutorService userExecutor(){
        int availableProcessors = getAvailableProcessors();
        int nUserThreads =0;
        return Executors.newFixedThreadPool(nUserThreads > 0 ? nUserThreads : availableProcessors, new NamedThreadFactory("user-"));
    }
}
