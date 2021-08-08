package com.atguigu.spring5;

import com.atguigu.spring5.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by shucheng on 2021/8/8 10:40
 */
@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:bean1.xml")
public class SpringJUnit4Test {

    @Autowired
    private UserService userService;

    @Test
    public void test() {
        userService.addUser();
    }
}
