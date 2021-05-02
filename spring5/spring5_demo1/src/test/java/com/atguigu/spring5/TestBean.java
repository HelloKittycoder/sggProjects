package com.atguigu.spring5;

import com.atguigu.spring5.bean.Emp;
import com.atguigu.spring5.service.UserService;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * Created by shucheng on 2021/5/2 12:26
 */
public class TestBean {

    @Test
    public void testBean1() {
        // 1 加载spring配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("bean2.xml"); // 这里会创建对象
        // 2 获取配置创建的对象
        UserService userService = context.getBean("userService", UserService.class);
        System.out.println(userService);
        userService.add();
    }

    @Test
    public void testBean2() {
        // 1 加载spring配置文件
        // bean3.xml 测试内部bean；bean4.xml 测试级联赋值
        ApplicationContext context = new ClassPathXmlApplicationContext("bean4.xml"); // 这里会创建对象
        // 2 获取配置创建的对象
        Emp emp = context.getBean("emp", Emp.class);
        System.out.println(emp);
    }
}
