package com.tj.exercise.flash.sale.demo.annotation;

import org.springframework.core.annotation.AliasFor;

import java.lang.annotation.*;
import java.util.concurrent.TimeUnit;

/**
 * @Author: tj
 * @Date: 2023/11/22 10:58
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface RateLimit {


    @AliasFor("limitPerSecond")
    double value() default 10;

    /**
     * 每秒限制数量
     */
    @AliasFor("value")
    double limitPerSecond() default 10;

    /**
     * 是否阻塞等待
     */
    boolean blockWait() default false;

    /**
     * 等待时间
     */
    long time() default 0;

    /**
     * 等待时间单位
     */
    TimeUnit timeUnit() default TimeUnit.SECONDS;
}

