package com.tj.exercise.flash.sale.demo.constant;

/**
 * @Author: tj
 * @Date: 2023/11/8 11:32
 */
public class RedisConstant {
    public static final String TICKETCOUNT = "ticketCount";
    public static final String SALETICKETS = "saleTickets";
    public static final String EXISTTICKET="existTickets";
    /**
     * 锁前缀，用于秒杀时生成分布式锁
     */
    public static final String FLASHSALEKEYSUFFIX ="flash_sale_scene_";
}
