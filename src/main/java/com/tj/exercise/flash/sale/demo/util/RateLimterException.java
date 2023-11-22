package com.tj.exercise.flash.sale.demo.util;

/**
 * @Author: tj
 * @Date: 2023/11/22 11:15
 * 自定义异常，当程序抛出此异常时，说明自定义的限流政策起了作用
 */
public class RateLimterException extends Exception {

    // 默认构造方法
    public RateLimterException() {
        super();
    }

    // 带有详细信息的构造方法
    public RateLimterException(String message) {
        super(message);
    }

    // 带有详细信息和原因的构造方法
    public RateLimterException(String message, Throwable cause) {
        super(message, cause);
    }
}
