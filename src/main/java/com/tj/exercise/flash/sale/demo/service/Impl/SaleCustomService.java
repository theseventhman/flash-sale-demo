package com.tj.exercise.flash.sale.demo.service.Impl;

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

    //@RateLimit
    public String buySceneTicket(SaleTicket saleTicket) {

        JedisPool jedisPool = RedisUtil.getJedisPool();
        // 使用 jedisPool 进行操作
        try (Jedis jedis = jedisPool.getResource()) {
            // 使用Jedis对象进行Redis操作
            // ...
            jedis.auth("123456");
            if(jedis.exists(RedisConstant.TICKETCOUNT)) {
                ObjectMapper objectMapper = new ObjectMapper();
                String saleTicketVal = objectMapper.writeValueAsString(saleTicket);
                String redisKey =  saleTicket.getUserId().toString()+saleTicket.getSceneId();
                try {

                    if (saleService.setnx(redisKey, String.valueOf(saleTicket.getUserId()))) {
                        //如果获取到了锁，ticketCount -1
                        int stock = saleService.substractStock(String.valueOf(saleTicket.getSceneId()),"1");
                        log.info("库存还剩下{}个",stock);
                        if(stock ==-2){
                            //告诉客户端库存不足，秒杀已结束
                            return "full";
                    }
                        if(stock >=0){
                            log.info("用户{}拿到锁...",saleTicket.getUserId());
                            amqpTemplate.convertAndSend("saleCustom", "saleTicket", saleTicket);

                        }

                    }
                }
                catch (Exception ex){
                    log.error("出错了",ex);
                    return  "fail";
                }

                finally {
                    saleService.delnx(redisKey, String.valueOf(saleTicket.getUserId()));
                    log.info("用户{}释放锁...",saleTicket.getUserId());
                }
            }
            else{
                log.info("redis中没有存秒杀用的数据");
                return "error";
            }


        }
        catch (Exception ex){
            log.error("出错了",ex);
            return  "fail";
        }
        return "success";
    }
}
