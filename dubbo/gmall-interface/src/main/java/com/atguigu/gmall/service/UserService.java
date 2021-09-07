package com.atguigu.gmall.service;

import com.atguigu.gmall.bean.UserAddress;

import java.util.List;

/**
 * 用户服务
 * Created by shucheng on 2021/9/5 22:26
 */
public interface UserService {

    /**
     * 按照用户id返回所有的收货地址
     * @param userId
     * @return
     */
    List<UserAddress> getUserAddressList(String userId);
}
