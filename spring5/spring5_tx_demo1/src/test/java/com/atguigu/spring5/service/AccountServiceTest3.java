package com.atguigu.spring5.service;

import com.atguigu.spring5.config.TxConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by shucheng on 2021/8/5 21:47
 */
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = TxConfig.class)
public class AccountServiceTest3 {

    @Autowired
    private AccountService accountService;

    @Test(expected = ArithmeticException.class)
    public void transferMoney() {
        accountService.transferMoney();
    }
}
