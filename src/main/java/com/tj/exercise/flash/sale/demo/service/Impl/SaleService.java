package com.tj.exercise.flash.sale.demo.service.Impl;

import com.tj.exercise.flash.sale.demo.util.JedisUtils;
import com.tj.exercise.flash.sale.demo.util.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

/**
 * @Author: tj
 * @Date: 2023/5/9 22:50
 */
@Service
@Slf4j
public class SaleService {
    public boolean setnx(String key, String val) {
            JedisPool jedisPool = RedisUtil.getJedisPool();
            // 使用 jedisPool 进行操作
            try (Jedis jedis = jedisPool.getResource()) {
                // 使用Jedis对象进行Redis操作
                // ...
                jedis.auth("123456");
                String setResult = jedis.set(key,val,"NX","PX",1000 * 60);
                if(!ObjectUtils.isEmpty(setResult)) {
                    return setResult.equalsIgnoreCase("ok");
                }
                else{
                    return false;
                }
            } catch (Exception e) {
                log.error("出错了",e+"key:"+key+"val:"+val);
            }
            return false;
        }

    public int delnx(String key, String val){
        JedisPool jedisPool = RedisUtil.getJedisPool();
        try (Jedis jedis = jedisPool.getResource()) {
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
        }
        return 0;
    }

    /**
     * 扣减库存
     * @param key
     * @param val
     * @return
     **         -3:库存未初始化
     *         -2:库存不足
     *         -1:不限库存
     *         大于等于0:剩余库存（扣减之后剩余的库存）
     *          redis缓存的库存(value)是-1表示不限库存，直接返回-1
     */
    public int substractStock(String key, String val){
        JedisPool jedisPool = RedisUtil.getJedisPool();
        try (Jedis jedis = jedisPool.getResource()) {
            if(jedis == null){
                return 0;
            }
            jedis.auth("123456");
//if redis.call('get','orderkey')=='1111' then return redis.call('del','orderkey') else return 0 end
            StringBuilder sb = new StringBuilder();
            sb.append("if (redis.call('exists','").append(key).append("')").append("==1)").
                    append(" then ").
                    append("    local stock = tonumber(redis.call('get', '").append(key).append("'));");
            sb.append("    local num = tonumber(").append(val).append(");");
            sb.append("    if (stock == -1) then");
            sb.append("        return -1;");
            sb.append("    end;");
            sb.append("    if (stock >= ").append(val).append(") then");
            sb.append("        return redis.call('incrby','").append(key).append("', 0 - ").append(val).append(");");
            sb.append("    end;");
            sb.append("    return -2;");
            sb.append("end;");
            sb.append("return -3;");

            return Integer.valueOf(jedis.eval(sb.toString()).toString());
        } catch (Exception ex) {
        }
        return 0;
    }

    public boolean setnxWhenUpdate(String key, String val) {
        JedisPool jedisPool = RedisUtil.getJedisPool();
        // 使用 jedisPool 进行操作
        try (Jedis jedis = jedisPool.getResource()) {
            // 使用Jedis对象进行Redis操作
            // ...
            jedis.auth("123456");
            String setResult = jedis.set(key,val,"XX");
            if(!ObjectUtils.isEmpty(setResult)) {
                return setResult.equalsIgnoreCase("ok");
            }
            else{
                return false;
            }
        } catch (Exception e) {
            log.error("出错了",e+"key:"+key+"val:"+val);
        }
        return false;
    }

}

