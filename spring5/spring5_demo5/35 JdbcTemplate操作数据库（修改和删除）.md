#### JdbcTemplate操作数据库（修改和删除）
1.修改
```java
@Override
public void updateBook(Book book) {
    String sql = "update t_book set bookName = ?, bookStatus = ? where bookId = ?";
    Object[] args = {book.getBookName(), book.getBookStatus(), book.getBookId()};
    int update = jdbcTemplate.update(sql, args);
    System.out.println("修改行数：" + update);
}
```
2.删除
```java
@Override
public void deleteBook(String bookId) {
    String sql = "delete from t_book where bookId = ?";
    int update = jdbcTemplate.update(sql, bookId);
    System.out.println("删除行数：" + update);
}
```