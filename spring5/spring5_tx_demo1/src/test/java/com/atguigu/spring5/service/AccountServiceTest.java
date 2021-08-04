package com.atguigu.spring5.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by shucheng on 2021/8/4 21:52
 */
@RunWith(SpringRunner.class)
@ContextConfiguration({"/bean1.xml"})
public class AccountServiceTest {

    @Autowired
    private AccountService accountService;

    @Test(expected = ArithmeticException.class)
    public void transferMoney() {
        accountService.transferMoney();
    }
}