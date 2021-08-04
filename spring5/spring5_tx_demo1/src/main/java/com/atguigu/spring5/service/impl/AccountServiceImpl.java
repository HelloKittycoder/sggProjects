package com.atguigu.spring5.service.impl;

import com.atguigu.spring5.dao.AccountDao;
import com.atguigu.spring5.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by shucheng on 2021/8/1 23:34
 */
@Service
@Transactional
public class AccountServiceImpl implements AccountService {

    @Autowired
    private AccountDao accountDao;

    //转账的方法
    @Override
    public void transferMoney() {
        // lucy转100给mary
        // lucy少100
        accountDao.reduceMoney();
        System.out.println(1/ 0);
        // mary多100
        accountDao.addMoney();
    }
}
