package com.tj.exercise.flash.sale.demo.controller;

import com.tj.exercise.flash.sale.demo.service.Impl.SaleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Author: tj
 * @Date: 2023/5/15 22:24
 */
@RestController
@RequestMapping("/operation")
public class OperationController {
    @Autowired
    private SaleService saleService;

    @GetMapping("/setnx/{key}/{val}")
    public boolean setNx(@PathVariable String key, @PathVariable String val) {
       return saleService.setnx(key,val);
    }

    @GetMapping("/delnx/{key}/{val}")
    public int delnx(@PathVariable String key, @PathVariable String val) {
        return saleService.delnx(key, val);
    }
}
