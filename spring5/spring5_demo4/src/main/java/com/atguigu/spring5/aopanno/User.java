package com.atguigu.spring5.aopanno;

import org.springframework.stereotype.Component;

/**
 * 被增强的类
 * Created by shucheng on 2021/5/25 12:06
 */
@Component
public class User {
    public void add() {
        // int i = 1 / 0;
        System.out.println("add......");
    }
}
