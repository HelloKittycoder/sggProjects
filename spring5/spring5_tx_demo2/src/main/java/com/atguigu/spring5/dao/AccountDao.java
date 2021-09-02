package com.atguigu.spring5.dao;

import com.atguigu.spring5.entity.Account;

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

    /**
     * 查询账户信息
     * @param id
     * @return
     */
    Account findAccountById(int id);

}
