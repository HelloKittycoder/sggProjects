package com.atguigu.spring5.service.impl;

import com.atguigu.spring5.service.TransactionTestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.transaction.support.TransactionSynchronizationManager;

/**
 * TransactionAspectSupport.currentTransactionStatus() 判断当前事务的情况
 * TransactionSynchronizationManager.isActualTransactionActive() 判断当前是否处在事务中
 *
 * Created by shucheng on 2021/7/31 10:52
 */
@Service
public class TransactionTestServiceImpl implements TransactionTestService {

    private Logger logger = LoggerFactory.getLogger(TransactionTestService.class);
    @Autowired
    private TransactionTestService transactionTestService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void test1() {
        logger.info("进入test1方法");
        // System.out.println(TransactionAspectSupport.currentTransactionStatus());
        // System.out.println("111===" + TransactionSynchronizationManager.isActualTransactionActive());
        // 下面如果写 this.test2() 看不到效果
        transactionTestService.test2();
        logger.info("离开test1方法");
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    // @Transactional(propagation = Propagation.NESTED)
    public void test2() {
        logger.info("进入test2方法");
        // System.out.println(TransactionAspectSupport.currentTransactionStatus());
        // System.out.println("222===" + TransactionSynchronizationManager.isActualTransactionActive());
        logger.info("离开test2方法");
    }
}
