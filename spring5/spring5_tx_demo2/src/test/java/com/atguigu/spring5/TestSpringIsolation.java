package com.atguigu.spring5;

import com.atguigu.BaseTest;
import com.atguigu.spring5.dao.AccountDao;
import com.atguigu.spring5.entity.Account;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

/**
 * 测试spring的事务隔离级别
 * Created by shucheng on 2021/7/31 22:52
 */
@RunWith(SpringRunner.class)
@ContextConfiguration({"/bean1.xml", "/transaction-config.xml", "/transaction-template-config.xml"})
public class TestSpringIsolation extends BaseTest {

    private Logger logger = LoggerFactory.getLogger(getClass());
    // 测试 Isolation.READ_UNCOMMITTED 这种情况
    @Autowired
    private TransactionTemplate transactionTemplate;

    @Autowired
    private AccountDao accountDao;

    @Before
    public void setUp() {
        executeSqlScript("classpath:/sql/initDB.sql", false);
    }

    @Test
    public void test() {
        transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_UNCOMMITTED);

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                    @Override
                    protected void doInTransactionWithoutResult(TransactionStatus status) {
                        Account account = accountDao.findAccountById(1);
                        System.out.println("lucy账户上的钱是：" + account.getMoney());
                    }
                });
                System.out.println(Thread.currentThread().getName() + "正在执行...");
            }
        }, "t2");

        Thread t1 = new TestThread.ThreadJoinDemo("t1", t2);
        t1.start();
        t2.start();
        /*logger.info("111");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("222");*/
    }

    class ThreadJoinDemo extends Thread {
        private Thread anotherThread;

        public ThreadJoinDemo(String name, Thread anotherThread) {
            this.anotherThread = anotherThread;
            super.setName(name);
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "正在执行");
            System.out.println(Thread.currentThread().getName() + "等待" + anotherThread.getName());
            try {
                accountDao.reduceMoney();
                anotherThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println(Thread.currentThread().getName() + "继续执行");
        }
    }
}
