package com.atguigu.spring5.dao;

/**
 * Created by shucheng on 2021/7/30 20:55
 */
public interface AccountDao {

    /**
     * 少钱
     */
    void reduceMoney();

    /**
     * 多钱
     */
    void addMoney();

}
