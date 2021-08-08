package com.atguigu.spring5;

import com.atguigu.spring5.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * Created by shucheng on 2021/8/8 10:53
 */
@SpringJUnitConfig(locations = "classpath:bean1.xml")
public class SpringJUnit5Test_02 {

    @Autowired
    private UserService userService;

    @Test
    public void test() {
        userService.addUser();
    }
}
