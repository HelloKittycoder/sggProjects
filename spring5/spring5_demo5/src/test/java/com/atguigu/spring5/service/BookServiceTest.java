package com.atguigu.spring5.service;

import com.atguigu.spring5.dao.BookDao;
import com.atguigu.spring5.entity.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.ArrayList;
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

    @Test
    public void batchInsert() {
        List<Object[]> args = new ArrayList<>();
        args.add(new String[]{"1", "data1", "s1"});
        args.add(new String[]{"2", "data2", "s2"});
        args.add(new String[]{"3", "data3", "s3"});
        int[] updateArr = bookDao.batchUpdate("insert into t_book values(?, ?, ?)", args);
        System.out.println(updateArr);
    }

    @Test
    public void batchUpdate() {
        List<Object[]> args = new ArrayList<>();
        args.add(new String[]{"data1_1", "s1_1", "1"});
        args.add(new String[]{"data2_1", "s2_1", "2"});
        args.add(new String[]{"data3_1", "s3_1", "3"});
        int[] updateArr = bookDao.batchUpdate("update t_book set bookName = ?, bookStatus = ? where bookId = ?", args);
        System.out.println(updateArr);
    }

    @Test
    public void batchDelete() {
        List<Object[]> args = new ArrayList<>();
        args.add(new String[]{"1"});
        args.add(new String[]{"2"});
        args.add(new String[]{"3"});
        int[] updateArr = bookDao.batchUpdate("delete from t_book where bookId = ?", args);
        System.out.println(updateArr);
    }
}