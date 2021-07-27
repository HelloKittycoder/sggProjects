package com.atguigu.spring5.entity;

/**
 * Created by shucheng on 2021/7/27 9:14
 */
public class Book {
    private String bookId;
    private String bookName;
    private String bookStatus;

    public Book() {
    }

    public Book(String bookId, String bookName, String bookStatus) {
        this.bookId = bookId;
        this.bookName = bookName;
        this.bookStatus = bookStatus;
    }

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookStatus() {
        return bookStatus;
    }

    public void setBookStatus(String bookStatus) {
        this.bookStatus = bookStatus;
    }
}
