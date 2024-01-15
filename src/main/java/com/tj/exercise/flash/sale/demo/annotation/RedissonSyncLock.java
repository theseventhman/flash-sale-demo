package com.tj.exercise.flash.sale.demo.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Author: tj
 * @Date: 2024/1/15 11:46
 */
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.METHOD})
public @interface RedissonSyncLock {

    String pref();

    /**
     * 锁后缀， 一般前缀根据业务来定，后缀是具体的场景
     */
    String keyEL();

    /**
     *  等待时长 【 需要区分是互斥还是阻塞，互斥默认0就可以 】
     * @return
     */
    int waitSec() default  0;
}
