package com.atguigu;

import com.atguigu.spring5.dao.AccountDao;
import com.atguigu.spring5.entity.Account;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ResourceDatabasePopulator;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by shucheng on 2021/7/31 9:45
 */
@Ignore // 这个类是被继承的，这个类不需要运行单元测试
@RunWith(SpringRunner.class)
public class BaseTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private String sqlScriptEncoding;

    @Autowired
    protected AccountDao accountDao;
    @Autowired
    protected ApplicationContext applicationContext;

    protected void executeSqlScript(String sqlResourcePath, boolean continueOnError) throws DataAccessException {
        Resource resource = this.applicationContext.getResource(sqlResourcePath);
        new ResourceDatabasePopulator(continueOnError, false, this.sqlScriptEncoding, resource).execute(jdbcTemplate.getDataSource());
    }

    protected boolean checkAccountMoney(int id, int money) {
        Account account = accountDao.findAccountById(id);
        return account.getMoney() == money;
    }
}
