package com.atguigu.spring5.dao;

import com.atguigu.spring5.entity.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

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

    @Override
    public Integer selectCount() {
        String sql = "select count(*) from t_book";
        Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
        return count;
    }

    @Override
    public Book selectById(String bookId) {
        String sql = "select * from t_book where bookId = ?";
        /*return jdbcTemplate.queryForObject(sql, new RowMapper<Book>() {
            @Override
            public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
                Book book = new Book();
                book.setBookId(rs.getString("bookId"));
                book.setBookName(rs.getString("bookName"));
                book.setBookStatus(rs.getString("bookStatus"));
                return book;
            }
        }, bookId);*/
        return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Book.class), bookId);
    }

    @Override
    public List<Book> selectAll() {
        String sql = "select * from t_book";
        /*return jdbcTemplate.query(sql, new RowMapper<Book>() {
            @Override
            public Book mapRow(ResultSet rs, int rowNum) throws SQLException {
                Book book = new Book();
                book.setBookId(rs.getString("bookId"));
                book.setBookName(rs.getString("bookName"));
                book.setBookStatus(rs.getString("bookStatus"));
                return book;
            }
        });*/
        return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
    }
}
