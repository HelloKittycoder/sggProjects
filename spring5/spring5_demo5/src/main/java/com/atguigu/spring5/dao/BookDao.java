package com.atguigu.spring5.dao;

import com.atguigu.spring5.entity.Book;

/**
 * Created by shucheng on 2021/7/12 23:01
 */
public interface BookDao {

    void addBook(Book book);

    void updateBook(Book book);

    void deleteBook(String bookId);
}
