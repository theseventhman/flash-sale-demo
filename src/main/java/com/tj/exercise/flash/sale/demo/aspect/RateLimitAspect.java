package com.tj.exercise.flash.sale.demo.aspect;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.util.concurrent.RateLimiter;
import com.tj.exercise.flash.sale.demo.annotation.RateLimit;

import com.tj.exercise.flash.sale.demo.util.RateLimterException;
import lombok.extern.slf4j.Slf4j;

import org.aopalliance.intercept.Joinpoint;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.stereotype.Component;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @Author: tj
 * @Date: 2023/11/22 11:00
 */
@Slf4j
@Aspect
@Component
public class RateLimitAspect {

    private static final ConcurrentHashMap<String, RateLimiter> LIMITER_MAP = new ConcurrentHashMap<>();

    /**
     * 配置 RateLimit 注解切入点
     */
    @Pointcut("@within(com.tj.exercise.flash.sale.demo.annotation.RateLimit) " +
            "|| @annotation(com.tj.exercise.flash.sale.demo.annotation.RateLimit)")
    public void pointCut() {

    }

    @Before("pointCut()")
    public void rateLimit(JoinPoint point) throws RateLimterException {
        // 获取方法上的注解
        MethodSignature methodSignature = (MethodSignature) point.getSignature();
        // 获取注解
        RateLimit rateLimit = methodSignature.getMethod().getAnnotation(RateLimit.class);
        // 获取 RateLimiter
        RateLimiter rateLimiter =
                LIMITER_MAP.computeIfAbsent(methodSignature.toLongString(), m -> RateLimiter.create(rateLimit.limitPerSecond()));
        // 判断是否需要阻塞等待
        if (!rateLimit.blockWait()) {
            boolean acquireOk = false;
            if (rateLimit.time() != 0) {
                acquireOk = rateLimiter.tryAcquire(rateLimit.time(), rateLimit.timeUnit());
            } else {
                acquireOk = rateLimiter.tryAcquire();
            }
            if (acquireOk) {
                return;
            }
            throw new RuntimeException("请稍后再尝试访问");
        }
        rateLimiter.acquire();



    }
}

