package com.atguigu.spring5;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by shucheng on 2021/4/25 9:38
 */
public class TestBean {

    private static final Logger LOGGER = LoggerFactory.getLogger(TestBean.class);

    @Test
    public void testAdd() {
        // 1 加载spring配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
        // 2 获取配置创建的对象
        User user = context.getBean("user", User.class);
        System.out.println(user);
        LOGGER.info("准备调用add方法");
        user.add();
    }
}
