package com.atguigu.spring5.dao;

import com.atguigu.spring5.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

/**
 * Created by shucheng on 2021/7/12 23:01
 */
@Repository
public class BookDaoImpl implements BookDao {

    // 注入JdbcTemplate
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addBook(Book book) {
        String sql = "insert into t_book(bookId, bookName, bookStatus) values(?, ?, ?)";
        Object[] args = {book.getBookId(), book.getBookName(), book.getBookStatus()};
        int update = jdbcTemplate.update(sql, args);
        System.out.println("新增行数：" + update);
    }

    @Override
    public void updateBook(Book book) {
        String sql = "update t_book set bookName = ?, bookStatus = ? where bookId = ?";
        Object[] args = {book.getBookName(), book.getBookStatus(), book.getBookId()};
        int update = jdbcTemplate.update(sql, args);
        System.out.println("修改行数：" + update);
    }

    @Override
    public void deleteBook(String bookId) {
        String sql = "delete from t_book where bookId = ?";
        int update = jdbcTemplate.update(sql, bookId);
        System.out.println("删除行数：" + update);
    }
}
