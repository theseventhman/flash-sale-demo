package com.tj.exercise.flash.sale.demo.mapper;

import com.tj.exercise.flash.sale.demo.entity.SaleTicket;
import com.tj.exercise.flash.sale.demo.entity.UserOrderInfo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

/**
 * @Author: tj
 * @Date: 2023/11/22 10:03
 */
@Mapper
public interface SaleTicketConvertMapper {
    SaleTicketConvertMapper INSTANCE = Mappers.getMapper(SaleTicketConvertMapper.class);

    UserOrderInfo toUserOrderInfo(SaleTicket saleTicket);
}
