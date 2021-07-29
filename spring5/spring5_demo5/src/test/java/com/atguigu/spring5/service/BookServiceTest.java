package com.atguigu.spring5.service;

import com.atguigu.spring5.dao.BookDao;
import com.atguigu.spring5.entity.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by shucheng on 2021/7/27 9:27
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:bean1.xml")
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Autowired
    private BookDao bookDao;

    @Test
    public void addBook() {
        Book book = new Book("1", "java", "a");
        bookService.addBook(book);
        System.out.println("添加成功");
    }

    @Test
    public void updateBook() {
        Book book = new Book("1", "C++", "b");
        bookService.updateBook(book);
        System.out.println("修改成功");
    }

    @Test
    public void deleteBook() {
        bookService.deleteBook("1");
        System.out.println("删除成功");
    }

    // 下面为了方便测试，就不再另外写bookService方法了，而是直接调用bookDao里的方法
    @Test
    public void selectCount() {
        Integer count = bookDao.selectCount();
        System.out.println(count);
    }

    @Test
    public void selectById() {
        Book book = bookDao.selectById("1");
        System.out.println(book);
    }

    @Test
    public void selectAll() {
        List<Book> bookList = bookDao.selectAll();
        System.out.println(bookList);
    }
}