package com.atguigu.spring5.bean;

/**
 * 部门类
 * Created by shucheng on 2021/5/2 13:47
 */
public class Dept {

    private String dname;

    public void setDname(String dname) {
        this.dname = dname;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "dname='" + dname + '\'' +
                '}';
    }
}
