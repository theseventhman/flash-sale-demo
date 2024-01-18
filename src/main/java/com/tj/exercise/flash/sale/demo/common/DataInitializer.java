package com.tj.exercise.flash.sale.demo.common;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tj.exercise.flash.sale.demo.constant.RedisConstant;
import com.tj.exercise.flash.sale.demo.entity.SaleTicket;
import com.tj.exercise.flash.sale.demo.entity.SceneInfo;
import com.tj.exercise.flash.sale.demo.entity.TicketInfo;
import com.tj.exercise.flash.sale.demo.mapper.SceneInfoMapper;
import com.tj.exercise.flash.sale.demo.mapper.TicketInfoMapper;
import com.tj.exercise.flash.sale.demo.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tj
 * @Date: 2023/11/2 10:39
 */
@Slf4j
@Component
public class DataInitializer implements ApplicationRunner {
    @Autowired
    private SceneInfoMapper ticketInfoMapper;
    @Autowired
    private RedissonClient redissonClient;
    @Override
    public void run(ApplicationArguments args) throws Exception {

        // 使用 jedisPool 进行操作
        try  {
            // 使用Jedis对象进行Redis操作
            // ...

            if(!redissonClient.getBucket(RedisConstant.TICKETCOUNT).isExists()) {
                redissonClient.getBucket(RedisConstant.TICKETCOUNT).set("1000");
            }
            if(!redissonClient.getBucket(RedisConstant.EXISTTICKET).isExists()) {
                // 如果秒杀的数据还没有加载到redis中，把秒杀数据加载到redis中
                List<SceneInfo> ticketInfos = ticketInfoMapper.selectList(new QueryWrapper<>());
                for (SceneInfo ticketInfo : ticketInfos) {

                    redissonClient.getBucket(String.valueOf(ticketInfo.getSceneId())).set(ticketInfo.getFlashSaleTicketCount());
                }
                redissonClient.getBucket(RedisConstant.EXISTTICKET).set("1");
            }
        }
        catch(Exception ex){
              log.error("出错了",ex);
        }
    }
}
