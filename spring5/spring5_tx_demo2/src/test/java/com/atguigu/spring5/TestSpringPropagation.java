package com.atguigu.spring5;

import com.atguigu.spring5.service.TransactionTestService;
import com.atguigu.spring5.service.UserBalanceService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * 测试spring的事务传播
 * 测试事务传播不需要借助数据库，这个是spring中引入的概念
 * 参考链接：
 * https://stackoverflow.com/questions/23364572/spring-propagation-requires-new
 * https://www.cnblogs.com/dennyzhangdd/p/9602670.html
 * https://www.cnblogs.com/dennyzhangdd/p/9708499.html
 * https://blog.csdn.net/qq_33404395/article/details/83377382
 *
 * Created by shucheng on 2021/7/31 10:54
 */
@RunWith(SpringRunner.class)
@ContextConfiguration({"/bean1.xml", "/transaction-config.xml"})
public class TestSpringPropagation {

    @Autowired
    private TransactionTestService transactionTestService;

    // 在一个类里测（这种情况下要注意把spring代理的对象注入进来，如果直接this调用，是不会触发spring的事务管理的）
    @Test
    public void test() {
        transactionTestService.test1();
    }

    @Autowired
    private UserBalanceService userBalanceService;

    // 在不同的类里测
    @Test
    public void testAddUserAndBalance() {
        userBalanceService.addUserAndBalance();
    }
}
