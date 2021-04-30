package com.atguigu.spring5;

/**
 * 演示使用set方法进行属性注入
 * Created by shucheng on 2021/4/28 9:20
 */
public class Book {
    // 创建属性
    private String bname;
    private String bauthor;
    private String address;

    // 创建属性对应的set方法
    public void setBname(String bname) {
        this.bname = bname;
    }

    public void setBauthor(String bauthor) {
        this.bauthor = bauthor;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void testDemo() {
        System.out.println(bname + "::" + bauthor + "::" + address);
    }
}
