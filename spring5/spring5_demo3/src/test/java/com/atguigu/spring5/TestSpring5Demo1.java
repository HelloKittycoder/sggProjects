package com.atguigu.spring5;

import com.atguigu.spring5.controller.MyController;
import com.atguigu.spring5.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by shucheng on 2021/5/14 9:59
 */
public class TestSpring5Demo1 {

    @Test
    public void testService1() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml");
        UserService userService = context.getBean("userService", UserService.class);
        System.out.println(userService);
        userService.add();
    }

    /**
     * 测试include-filter和exclude-filter
     * 用bean2.xml的include-filter部分能找到MyController
     * 用bean2.xml的exclude-filter部分不能找到MyController
     */
    @Test
    public void testController2() {
        ApplicationContext context = new ClassPathXmlApplicationContext("bean2.xml");
        MyController myController = context.getBean("myController", MyController.class);
        System.out.println(myController);
    }
}
