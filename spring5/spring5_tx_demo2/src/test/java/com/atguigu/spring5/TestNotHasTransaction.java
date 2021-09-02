package com.atguigu.spring5;

import com.atguigu.BaseTest;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.ContextConfiguration;

import static org.junit.Assert.assertTrue;

/**
 * 测试没有事务的情况
 * Created by shucheng on 2021/7/31 8:44
 */
@ContextConfiguration({"/bean1.xml"})
public class TestNotHasTransaction extends BaseTest {

    @Before
    public void setUp() {
        executeSqlScript("classpath:/sql/initDB.sql", false);
    }

    @Test(expected = ArithmeticException.class)
    public void transferMoney() {
        try {
            accountDao.reduceMoney();
            int i = 1 / 0;
            accountDao.addMoney();
        } catch (Exception e) {
            // 验证出现数据不一致的情况
            assertTrue(checkAccountMoney(1, 900));
            assertTrue(checkAccountMoney(2, 1000));
            throw e;
        }
    }
}
