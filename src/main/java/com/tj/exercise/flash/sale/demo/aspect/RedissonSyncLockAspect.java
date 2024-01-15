package com.tj.exercise.flash.sale.demo.aspect;

import com.tj.exercise.flash.sale.demo.annotation.RedissonSyncLock;
import com.tj.exercise.flash.sale.demo.util.SpelUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.redisson.Redisson;
import org.redisson.api.RLock;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

/**
 * @Author: tj
 * @Date: 2024/1/15 11:49
 */
@Slf4j
@Aspect
@Component
@RequiredArgsConstructor
public class RedissonSyncLockAspect {
    private final Redisson redisson;

    @Around(value = "@annotation( com.tj.exercise.flash.sale.demo.annotation.RedissonSyncLock)")
    public Object around(ProceedingJoinPoint joinPoint) throws  Throwable{
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        RedissonSyncLock redissonSyncLock = signature.getMethod().getAnnotation(RedissonSyncLock.class);
        Object[] args = joinPoint.getArgs();
        String key =  SpelUtil.parseSpel(signature.getMethod(),args,redissonSyncLock.keyEL());
        RLock lock = null;
        try{
            if(StringUtils.isNotBlank(key) && !StringUtils.equals(key,"null"))
            {
                lock = redisson.getLock(redissonSyncLock.pref().concat(key));
                if(lock.tryLock(redissonSyncLock.waitSec(), TimeUnit.SECONDS)){
                    return joinPoint.proceed();
                }
            }
            log.info("RedissonSyncLockAspect 上锁失败 【}",key);
        } finally {
            if(lock != null && lock.isLocked() && lock.isHeldByCurrentThread()){
                lock.unlock();
            }
        }
        return null;
    }
}
