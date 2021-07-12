package com.atguigu.spring5.dao;

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
}
