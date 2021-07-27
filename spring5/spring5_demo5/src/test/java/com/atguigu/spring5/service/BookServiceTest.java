package com.atguigu.spring5.service;

import com.atguigu.spring5.entity.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import static org.junit.Assert.*;

/**
 * Created by shucheng on 2021/7/27 9:27
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:bean1.xml")
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    public void addBook() {
        Book book = new Book("1", "java", "a");
        bookService.addBook(book);
        System.out.println("添加成功");
    }
}