package com.tj.exercise.flash.sale.demo.controller;

import com.tj.exercise.flash.sale.demo.entity.SaleTicket;
import com.tj.exercise.flash.sale.demo.service.Impl.SaleCustomService;
import com.tj.exercise.flash.sale.demo.util.RateLimterException;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author: tj
 * @Date: 2023/11/2 10:27
 */
@RestController
@Slf4j
@RequestMapping("/saleCustom")
public class FlashSaleCustomController {
    @Autowired
    private SaleCustomService saleCustomService;
    @PostMapping("/sale")
    public String sale(@RequestBody SaleTicket saleTicket){
        String saleResult = "";
        try {
            saleResult = saleCustomService.buySceneTicket(saleTicket);
            return saleResult;
        }
        catch(Exception ex){
            if(ex.getMessage().equalsIgnoreCase("请稍后再尝试访问")){
                return "qps已满";
            }
        }
        return saleResult;


    }

}
