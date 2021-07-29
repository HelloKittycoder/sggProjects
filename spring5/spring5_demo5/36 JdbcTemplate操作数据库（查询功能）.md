#### JdbcTemplate操作数据库（查询功能）
1.查询返回某个值  
（1）场景：查询表里面有多少条记录，返回的是某个值  
（2）使用JdbcTemplate实现查询返回某个值的代码  
queryForObject(String sql, Class<T> requiredType)  
sql：sql语句；requiredType：返回类型
```java
@Override
public Integer selectCount() {
    String sql = "select count(*) from t_book";
    Integer count = jdbcTemplate.queryForObject(sql, Integer.class);
    return count;
}
```
2.查询返回对象  
（1）场景：查询图书详情  
（2）使用JdbcTemplate实现查询对象  
queryForObject(String sql, RowMapper<T> rowMapper, Object... args)  
sql：sql语句；rowMapper：是接口，针对不同类型数据，使用这个接口的实现类完成数据的封装；args：sql参数
```java
@Override
public Book selectById(String bookId) {
    String sql = "select * from t_book where bookId = ?";
    return jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Book.class), bookId);
}
```
3.查询返回集合  
（1）场景：查询图书列表  
（2）使用JdbcTemplate实现查询返回集合  
query(String sql, RowMapper<T> rowMapper, Object... args)  
sql：sql语句；rowMapper：是接口，针对不同类型数据，使用这个接口的实现类完成数据的封装；args：sql参数
```java
@Override
public List<Book> selectAll() {
    String sql = "select * from t_book";
    return jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
}
```