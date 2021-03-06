package com.atguigu.spring5;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

/**
 * Created by shucheng on 2021/4/25 9:38
 */
public class TestSpring5 {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestSpring5.class);

    @Test
    public void testBeanFactory() {
        // 1 加载spring配置文件
        Resource resource = new ClassPathResource("bean1.xml");
        BeanFactory context = new XmlBeanFactory(resource); // 这里不会创建对象
        LOGGER.info("准备获取user");
        // 2 获取配置创建的对象（这一步会创建对象）
        User user = context.getBean("user", User.class);
        System.out.println(user);
        LOGGER.info("准备调用add方法");
        user.add();
    }

    @Test
    public void testApplicationContext() {
        // 1 加载spring配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml"); // 这里会创建对象
        LOGGER.info("准备获取user");
        // 2 获取配置创建的对象
        User user = context.getBean("user", User.class);
        System.out.println(user);
        LOGGER.info("准备调用add方法");
        user.add();
    }

    @Test
    public void testBook1() {
        // 1 加载spring配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml"); // 这里会创建对象
        // 2 获取配置创建的对象
        Book book = context.getBean("book", Book.class);
        System.out.println(book);
        book.testDemo();
    }

    @Test
    public void testOrders() {
        // 1 加载spring配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml"); // 这里会创建对象
        // 2 获取配置创建的对象
        Orders orders = context.getBean("orders", Orders.class);
        System.out.println(orders);
        orders.ordersTest();
    }

    @Test
    public void testBook2() {
        // 1 加载spring配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml"); // 这里会创建对象
        // 2 获取配置创建的对象
        Book book = context.getBean("book2", Book.class);
        System.out.println(book);
        book.testDemo();
    }
}
