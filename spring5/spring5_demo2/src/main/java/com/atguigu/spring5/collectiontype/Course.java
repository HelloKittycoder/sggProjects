package com.atguigu.spring5.collectiontype;

/**
 * 课程类
 * Created by shucheng on 2021/5/6 9:06
 */
public class Course {

    private String cname; // 课程名称

    public void setCname(String cname) {
        this.cname = cname;
    }

    @Override
    public String toString() {
        return "Course{" +
                "cname='" + cname + '\'' +
                '}';
    }
}
