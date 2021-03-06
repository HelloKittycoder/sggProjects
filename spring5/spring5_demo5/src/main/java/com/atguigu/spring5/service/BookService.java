package com.atguigu.spring5.service;

import com.atguigu.spring5.dao.BookDao;
import com.atguigu.spring5.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by shucheng on 2021/7/12 23:00
 */
@Service
public class BookService {

    // 注入dao
    @Autowired
    private BookDao bookDao;

    void addBook(Book book) {
        bookDao.addBook(book);
    }

    void updateBook(Book book) {
        bookDao.updateBook(book);
    }

    void deleteBook(String bookId) {
        bookDao.deleteBook(bookId);
    }
}
