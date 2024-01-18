package com.tj.exercise.flash.sale.demo.config;

import org.redisson.Redisson;
import org.redisson.api.RedissonClient;
import org.redisson.config.Config;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: tj
 * @Date: 2024/1/15 10:10
 */
@Configuration
public class RedissonConfiguration {
    @Value("${redisson.address}")
    private String redisAddress;

    @Value("${redisson.password}")
    private String redisPassWord;

    @Value("${redisson.port}") // 添加端口配置
    private int redisPort;

    @Bean
    public RedissonClient redissonClient(){
        Config config = new Config();
        config.useSingleServer()
                .setAddress(redisAddress + ":" + redisPort)
                .setPassword(redisPassWord);
        return Redisson.create(config);

    }


}
