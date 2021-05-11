package com.atguigu.spring5.autowire;

/**
 * Created by shucheng on 2021/5/11 9:02
 */
public class Emp {

    private Dept dept;

    public void setDept(Dept dept) {
        this.dept = dept;
    }

    @Override
    public String toString() {
        return "Emp{" +
                "dept=" + dept +
                '}';
    }
}
