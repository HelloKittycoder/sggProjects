package com.atguigu.spring5;

import com.atguigu.spring5.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

/**
 * Created by shucheng on 2021/8/8 10:44
 */
@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:bean1.xml")
public class SpringJUnit5Test_01 {

    @Autowired
    private UserService userService;

    @Test
    public void test() {
        userService.addUser();
    }
}
