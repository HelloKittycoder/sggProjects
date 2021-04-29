package com.atguigu.spring5;

/**
 * 使用有参数构造注入
 * Created by shucheng on 2021/4/28 12:24
 */
public class Orders {
    // 属性
    private String oname = "";
    private String address;
    // 有参数构造
    public Orders(String oname, String address) {
        this.oname = oname;
        this.address = address;
    }

    public void ordersTest() {
        System.out.println(oname + "::" + address);
    }
}
