package com.atguigu.spring5.service.impl;

import com.atguigu.spring5.service.UserService;
import org.springframework.stereotype.Service;

/**
 * Created by shucheng on 2021/8/8 10:40
 */
@Service
public class UserServiceImpl implements UserService {
    @Override
    public void addUser() {
        System.out.println("调用UserServiceImpl#addUser方法");
    }
}
