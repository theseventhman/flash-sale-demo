package com.tj.exercise.flash.sale.demo.service.Impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tj.exercise.flash.sale.demo.annotation.RateLimit;
import com.tj.exercise.flash.sale.demo.constant.RedisConstant;
import com.tj.exercise.flash.sale.demo.entity.SaleTicket;
import com.tj.exercise.flash.sale.demo.entity.SceneOrder;
import com.tj.exercise.flash.sale.demo.entity.TicketInfo;
import com.tj.exercise.flash.sale.demo.mapper.SceneOrderMapper;
import com.tj.exercise.flash.sale.demo.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.redisson.api.RedissonClient;
import org.redisson.client.RedisClient;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tj
 * @Date: 2023/11/2 10:30
 */
@Service
@Slf4j
public class SaleCustomService {
    @Autowired
    private SaleService saleService;


    @Autowired
    private AmqpTemplate amqpTemplate;

    @Autowired
    private RedissonClient redissonClient;



    //@RateLimit
    public String buySceneTicket(SaleTicket saleTicket) {
        if(redissonClient.getBucket(RedisConstant.TICKETCOUNT).isExists()) {
            String redisKey = saleTicket.getSceneId().toString();
                try {
                    if (saleService.getDistributeLock(redisKey+"fx",redisKey)) {
                        amqpTemplate.convertAndSend("saleCustom", "saleTicket", saleTicket);
                    }
                }
                catch (Exception ex){
                    log.error("出错了",ex);
                    return  "fail";
                }
            }
            else{
                log.info("redis中没有存秒杀用的数据");
                return "error";
            }

        return "success";
    }
}
