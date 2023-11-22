package com.tj.exercise.flash.sale.demo.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tj.exercise.flash.sale.demo.entity.UserInfo;
import org.apache.ibatis.annotations.Mapper;

/**
 * @Author: tj
 * @Date: 2023/11/3 10:30
 */
@Mapper
public interface UserInfoMapper  extends BaseMapper<UserInfo> {
}
