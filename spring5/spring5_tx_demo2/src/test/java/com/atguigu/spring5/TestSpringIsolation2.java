package com.atguigu.spring5;

import com.atguigu.spring5.dao.AccountDao;
import com.atguigu.spring5.entity.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.beans.factory.support.DefaultListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanDefinitionReader;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.init.ScriptUtils;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

import java.sql.SQLException;

/**
 * 测试spring的事务隔离级别
 * Created by shucheng on 2021/7/31 22:52
 */
public class TestSpringIsolation2 {

    private static final Logger logger = LoggerFactory.getLogger(TestSpringIsolation2.class);

    // 测试 Isolation.READ_UNCOMMITTED 这种情况
    @Autowired
    private static TransactionTemplate transactionTemplate;

    @Autowired
    private static AccountDao accountDao;

    @Autowired
    private static JdbcTemplate jdbcTemplate;

    /*@Before
    public void setUp() {
        executeSqlScript("classpath:/sql/initDB.sql", false);
    }*/

    /**
     * 本来是想用单元测试来写（TestSpringIsolation），但是多线程会自动停掉，故暂时放弃这种方式，
     * 改用main方法来写（TestSpringIsolation2）
     * 说明：这里是模拟两个线程操作同一条记录，不同的事务隔离级别下查询结果的差别
     * t1是修改线程，t2是查询线程
     * t1在修改lucy账户上的钱，已经改好，不过事务没结束，此时t2来查询lucy账户上的钱
     * 如果隔离级别是READ_UNCOMMITTED，则看到的是t1改过的
     * 如果隔离级别是READ_COMMITTED，则看不到t1改过的
     *
     * 时间线
     * t1 事务开始---------------lucy的钱被修改-----------------------------------------------事务结束
     * t2 事务开始------------------------------------进入查询-----------------事务结束
     *
     *
     * @param args
     */
    // 忘了join怎么用可以看看TestThread
    public static void main(String[] args) {
        // 加载spring配置文件，从配置中获取bean，同时给某些bean的嵌套属性进行注入
        DefaultListableBeanFactory beanFactory = new DefaultListableBeanFactory();
        XmlBeanDefinitionReader beanDefinitionReader = new XmlBeanDefinitionReader(beanFactory);
        String[] resources = {"/bean1.xml", "/transaction-config.xml", "/transaction-template-config.xml"};
        for (String resource : resources) {
            beanDefinitionReader.loadBeanDefinitions(new ClassPathResource(resource));
        }
        transactionTemplate = beanFactory.getBean(TransactionTemplate.class);
        accountDao = beanFactory.getBean(AccountDao.class);
        jdbcTemplate = beanFactory.getBean(JdbcTemplate.class);
        // 下面这步是通过setJdbcTempate方法注入进去的
        beanFactory.autowireBeanProperties(accountDao, AutowireCapableBeanFactory.AUTOWIRE_BY_NAME, true);

        // 执行初始化sql
        Resource resource = new ClassPathResource("sql/initDB.sql");
        try {
            ScriptUtils.executeSqlScript(jdbcTemplate.getDataSource().getConnection(), resource);
        } catch (SQLException e) {
            e.printStackTrace();
        }

        transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_UNCOMMITTED);

        Account account = accountDao.findAccountById(1);
        System.out.println("未执行操作前====lucy账户上的钱是：" + account.getMoney()); // 1000

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
                        System.out.println(status.isNewTransaction());
                        Account account = accountDao.findAccountById(1);
                        /**
                         * ISOLATION_READ_UNCOMMITTED，下面显示 900
                         * ISOLATION_READ_COMMITTED，下面显示 1000
                         */
                        System.out.println("t2线程==lucy账户上的钱是：" + account.getMoney());
                    }
                });
                System.out.println(Thread.currentThread().getName() + "正在执行...");
            }
        }, "t2");

        Thread t1 = new ThreadJoinDemo("t1", t2);
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

    static class ThreadJoinDemo extends Thread {
        private Thread anotherThread;

        public ThreadJoinDemo(String name, Thread anotherThread) {
            this.anotherThread = anotherThread;
            super.setName(name);
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "正在执行");
            System.out.println(Thread.currentThread().getName() + "等待" + anotherThread.getName() + accountDao);
            transactionTemplate.execute(new TransactionCallbackWithoutResult() {
                @Override
                protected void doInTransactionWithoutResult(TransactionStatus status) {
                    accountDao.reduceMoney();
                    System.out.println("执行了少钱方法");
                    try {
                        anotherThread.join();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            System.out.println(Thread.currentThread().getName() + "继续执行");
        }
    }
}
