package com.atguigu.spring5.service;

import org.springframework.stereotype.Component;

/**
 * 在注解里value属性值可以省略不写，
 * 默认值是类名称，首字母小写
 * UserService -- userService
 * Created by shucheng on 2021/5/14 9:51
 */
@Component(value = "userService") // <bean id="userService" class=""/>
public class UserService {
    public void add() {
        System.out.println("service add...");
    }
}
