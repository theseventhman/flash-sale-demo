package com.tj.exercise.flash.sale.demo.util;

import lombok.extern.slf4j.Slf4j;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

import java.security.AlgorithmConstraints;
import java.util.Collections;

/**
 * @Author: tj
 * @Date: 2023/12/29 19:48
 */
@Slf4j
public class RedisLimit {
    private static final int FAIL_CODE = 0;

    private static Integer limit = 5;

    public static Boolean limit(){
        Object result = null;
        JedisPool jedisPool = RedisUtil.getJedisPool();
        try (Jedis jedis = jedisPool.getResource()) {
            // 使用Jedis对象进行Redis操作
            // ...
            jedis.auth("123456");
            //解析lua文件
            String script = ScriptUtil.getScript("limit.lua");
            //请求限流
            String key = String.valueOf(System.currentTimeMillis()/ 1000);
            // 计数限流
            result = jedis.eval(script, Collections.singletonList(key),Collections.singletonList(String.valueOf(limit)));
            if(FAIL_CODE !=(Long)result){
                log.info("成功获取令牌，key:{}",key);
                return true;
            }
        }
        catch (Exception ex){
            log.error("初始化限流功能失败",ex);
        }

         return false;

    }
}
