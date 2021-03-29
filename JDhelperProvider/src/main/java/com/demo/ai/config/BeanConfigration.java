package com.demo.ai.config;

import com.demo.ai.util.NamedThreadFactory;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.redisson.Redisson;
import org.redisson.config.Config;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.request.RequestContextListener;

import java.io.Serializable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
@Configuration
public class BeanConfigration{
    @Value("${spring.redis.host}")
    private String redis_host;

    @Value("${spring.redis.port}")
    private String redis_port;

    @Value("${spring.redis.password}")
    private String password;
    Logger log = LoggerFactory.getLogger(BeanConfigration.class);

    /**
     * 单机模式
     * @return
     */
    @Bean
    public Redisson createRedisson() {
        log.info("host:{},port:{}",redis_host,redis_port);
        Config config = new Config();
        config.useSingleServer().setAddress("redis://"+redis_host+":"+redis_port).setDatabase(0).setPassword(password);
        return (Redisson) Redisson.create(config);
    }
    @Bean
    public RequestContextListener requestContextListener(){
        return new RequestContextListener();
    }

    /*    @Bean
    public RedissonClient redissonClient() {
        Config config = new Config();
        config.useClusterServers()
                .setScanInterval(2000)
                .addNodeAddress("redis://72.19.12.227:6379");

        RedissonClient redisson = Redisson.create(config);

        return redisson;
    }*/
    @Bean
    public RedisTemplate<String, Serializable> redisTemplate(LettuceConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, Serializable> template = new RedisTemplate<>();
        template.setKeySerializer(new StringRedisSerializer());

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);

        template.setHashKeySerializer(new StringRedisSerializer());
        template.setHashValueSerializer(new GenericJackson2JsonRedisSerializer(objectMapper));

        template.setValueSerializer(new GenericJackson2JsonRedisSerializer(objectMapper));
        template.setConnectionFactory(redisConnectionFactory);
        template.afterPropertiesSet();
        return template;
    }
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
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
