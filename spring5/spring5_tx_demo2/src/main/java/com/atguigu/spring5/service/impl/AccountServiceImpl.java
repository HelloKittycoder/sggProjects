package com.atguigu.spring5.service.impl;

import com.atguigu.spring5.dao.AccountDao;
import com.atguigu.spring5.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.stereotype.Service;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * Created by shucheng on 2021/7/30 21:01
 */
@Service
public class AccountServiceImpl implements AccountService {

    /*@Autowired
    private DataSourceTransactionManager transactionManager;*/

    @Autowired
    private AccountDao accountDao;

    @Override
    public void transferMoney() {
        // 判断当前是否处于事务中
        System.out.println(TransactionSynchronizationManager.isActualTransactionActive());
        accountDao.reduceMoney();
        System.out.println(1/ 0);
        accountDao.addMoney();
    }
}
