package com.tj.exercise.flash.sale.demo.util;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @Author: tj
 * @Date: 2023/4/7 11:17
 */
public class RedisUtil {
    private static volatile JedisPool jedisPool = null;

    private RedisUtil() {}

    public static JedisPool getJedisPool() {
        if (jedisPool == null) {
            synchronized (RedisUtil.class) {
                if (jedisPool == null) {
                    JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
                    jedisPoolConfig.setMaxTotal(1000);
                    jedisPoolConfig.setMaxIdle(100);
                    jedisPoolConfig.setMinIdle(50);
//                    jedisPoolConfig.setTestOnBorrow(true);
//                    jedisPoolConfig.setTestOnReturn(true);
//                    jedisPoolConfig.setTestWhileIdle(true);
//                    jedisPoolConfig.setMinEvictableIdleTimeMillis(60000);
//                    jedisPoolConfig.setTimeBetweenEvictionRunsMillis(30000);
//                    jedisPoolConfig.setNumTestsPerEvictionRun(-1);
                    jedisPool = new JedisPool(jedisPoolConfig, "127.0.0.1", 6379);
                }
            }
        }
        return jedisPool;
    }
    public boolean setnx(String key, String val) {
        Jedis jedis = null;
        try {
            jedis = getJedisPool().getResource();
            if (jedis == null) {
                return false;
            }
            return jedis.set(key, val, "NX", "PX", 1000 * 60).
                    equalsIgnoreCase("ok");
        } catch (Exception ex) {
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return false;
    }

    public int delnx(String key, String val){
        Jedis jedis = null;
        try{
            jedis =getJedisPool().getResource();
            if(jedis == null){
                return 0;
            }
//if redis.call('get','orderkey')=='1111' then return redis.call('del','orderkey') else return 0 end
            StringBuilder sbScript = new StringBuilder();
            sbScript.append("if redis.call('get','").append(key).append("')").append("=='").append(val).append("'").
                    append(" then ").
                    append("    return redis.call('del','").append(key).append("')").
                    append(" else ").
                    append("    return 0").
                    append(" end");

            return Integer.valueOf(jedis.eval(sbScript.toString()).toString());
        } catch (Exception ex) {
        } finally {
            if (jedis != null) {
                jedis.close();
            }
        }
        return 0;
    }
}
