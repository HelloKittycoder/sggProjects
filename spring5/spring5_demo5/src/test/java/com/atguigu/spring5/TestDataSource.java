package com.atguigu.spring5;

import com.atguigu.spring5.service.BookService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * Created by shucheng on 2021/7/12 22:52
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:bean1.xml")
public class TestDataSource {

    @Autowired
    private BookService bookService;

    @Test
    public void test() {
        System.out.println(bookService);
    }
}
