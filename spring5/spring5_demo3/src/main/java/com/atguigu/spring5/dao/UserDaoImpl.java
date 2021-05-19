package com.atguigu.spring5.dao;

import org.springframework.stereotype.Repository;

/**
 * Created by shucheng on 2021/5/18 12:28
 */
@Repository(value = "userDaoImpl1")
// @Repository
public class UserDaoImpl implements UserDao {
    @Override
    public void add() {
        System.out.println("dao add...");
    }
}
