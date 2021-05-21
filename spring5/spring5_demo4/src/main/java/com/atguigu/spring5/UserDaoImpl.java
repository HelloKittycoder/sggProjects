package com.atguigu.spring5;

/**
 * Created by shucheng on 2021/5/21 8:56
 */
public class UserDaoImpl implements UserDao {
    @Override
    public int add(int a, int b) {
        System.out.println("add方法执行了...");
        return a + b;
    }

    @Override
    public String update(String id) {
        System.out.println("update方法执行了...");
        return id;
    }
}
