package com.tj.exercise.flash.sale.demo.common;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tj.exercise.flash.sale.demo.entity.SaleTicket;
import com.tj.exercise.flash.sale.demo.entity.SceneOrder;
import com.tj.exercise.flash.sale.demo.entity.TicketInfo;
import com.tj.exercise.flash.sale.demo.entity.UserOrderInfo;
import com.tj.exercise.flash.sale.demo.mapper.SaleTicketConvertMapper;
import com.tj.exercise.flash.sale.demo.mapper.SceneOrderMapper;
import com.tj.exercise.flash.sale.demo.mapper.TicketInfoMapper;
import com.tj.exercise.flash.sale.demo.mapper.UserOrderInfoMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @Author: tj
 * @Date: 2023/11/21 10:41
 */
@Slf4j
@Component
public class SaleCustomReceiver {
    @Autowired
    private UserOrderInfoMapper userOrderInfoMapper;
   @Autowired
    private TicketInfoMapper ticketInfoMapper;

    @RabbitListener(bindings = @QueueBinding(value = @Queue("saleCustomQueue"), exchange = @Exchange("saleCustom"), key = "saleTicket"))
    public void process(SaleTicket saleTicket){
        UserOrderInfo userOrderInfo = SaleTicketConvertMapper.INSTANCE.toUserOrderInfo(saleTicket);
        userOrderInfo.setOrderStatus(1);
        userOrderInfo.setTicketNum("1");
        //标识下来源是秒杀
        userOrderInfo.setSource("1");
        //插入订单
        userOrderInfoMapper.insert(userOrderInfo);
        //调微信回调接口
        //微信回调成功后减库存，把订单状态更新为已完成
        LambdaQueryWrapper<TicketInfo> ticketInfoQueryWrapper = new LambdaQueryWrapper<TicketInfo>();
        ticketInfoQueryWrapper.eq(TicketInfo::getSceneId,saleTicket.getSceneId());
        List<TicketInfo> ticketInfos = ticketInfoMapper.selectList(ticketInfoQueryWrapper);
        if(!CollectionUtils.isEmpty(ticketInfos)){
            TicketInfo updatedTicketInfo = ticketInfos.get(0);
            updatedTicketInfo.setTicketNum(updatedTicketInfo.getTicketNum()-1);
            ticketInfoMapper.updateById(updatedTicketInfo);
        }

    }
}
