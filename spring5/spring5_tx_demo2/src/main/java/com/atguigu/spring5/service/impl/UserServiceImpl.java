package com.atguigu.spring5.service.impl;

import com.atguigu.spring5.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * Created by shucheng on 2021/7/31 11:17
 */
@Service
public class UserServiceImpl implements UserService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void addUser() {
        logger.info("进入addUser方法");
        System.out.println("222===" + TransactionSynchronizationManager.isActualTransactionActive());
        logger.info("离开addUser方法");
    }
}
