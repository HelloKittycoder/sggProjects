package com.atguigu.spring5.collectiontype;

import java.util.List;

/**
 * Created by shucheng on 2021/5/6 22:30
 */
public class Book {

    private List<String> list;

    public void setList(List<String> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "Book{" +
                "list=" + list +
                '}';
    }
}
