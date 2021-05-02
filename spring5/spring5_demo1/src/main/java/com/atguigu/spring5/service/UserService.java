package com.atguigu.spring5.service;

import com.atguigu.spring5.dao.UserDao;

/**
 * Created by shucheng on 2021/5/2 11:36
 */
public class UserService {

    // 创建UserDao类型属性，生成set方法
    private UserDao userDao;

    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }

    public void add() {
        System.out.println("service add...");
        userDao.update();
    }
}
