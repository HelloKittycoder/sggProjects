package com.atguigu.spring5.service.impl;

import com.atguigu.spring5.service.UserBalanceService;
import com.atguigu.spring5.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * Created by shucheng on 2021/7/31 11:17
 */
@Service
public class UserBalanceServiceImpl implements UserBalanceService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserService userService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void addUserAndBalance() {
        logger.info("进入addUserAndBalance方法");
        // System.out.println("111===" + TransactionSynchronizationManager.isActualTransactionActive());
        // userService是spring代理的对象，所以会触发spring的事务管理
        userService.addUser();
        // this只是普通的对象，就算addBalance上加了@Transactional，也不会触发spring的事务管理
        this.addBalance();
        logger.info("离开addUserAndBalance方法");
    }

    @Override
    public void addBalance() {
        logger.info("调用addBalance方法");
    }
}
