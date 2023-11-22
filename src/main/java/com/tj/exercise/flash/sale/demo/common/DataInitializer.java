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
    @Override
    public void run(ApplicationArguments args) throws Exception {
        JedisPool jedisPool = RedisUtil.getJedisPool();
        // 使用 jedisPool 进行操作
        try (Jedis jedis = jedisPool.getResource()) {
            // 使用Jedis对象进行Redis操作
            // ...
            jedis.auth("123456");
            if(!jedis.exists(RedisConstant.TICKETCOUNT)) {
                jedis.set(RedisConstant.TICKETCOUNT, "1000");
            }
            if(!jedis.exists(RedisConstant.EXISTTICKET)) {
                // 如果秒杀的数据还没有加载到redis中，把秒杀数据加载到redis中
                List<SceneInfo> ticketInfos = ticketInfoMapper.selectList(new QueryWrapper<>());
                for (SceneInfo ticketInfo : ticketInfos) {

                    jedis.set(String.valueOf(ticketInfo.getSceneId()),ticketInfo.getFlashSaleTicketCount().toString());
                }
                jedis.set(RedisConstant.EXISTTICKET,"1");
            }
        }
        catch(Exception ex){
              log.info("出错了",ex);
        }
    }
}
