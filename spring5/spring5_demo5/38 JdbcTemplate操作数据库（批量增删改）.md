#### JdbcTemplate操作数据库（批量增删改）
batchUpdate(sql, args)  
sql：sql语句；args：List<Object[]>类型，sql参数
1.批量新增
```java
@Test
public void batchInsert() {
    List<Object[]> args = new ArrayList<>();
    args.add(new String[]{"1", "data1", "s1"});
    args.add(new String[]{"2", "data2", "s2"});
    args.add(new String[]{"3", "data3", "s3"});
    int[] updateArr = bookDao.batchUpdate("insert into t_book values(?, ?, ?)", args);
    System.out.println(updateArr);
}
```
2.批量修改
```java
@Test
public void batchUpdate() {
    List<Object[]> args = new ArrayList<>();
    args.add(new String[]{"data1_1", "s1_1", "1"});
    args.add(new String[]{"data2_1", "s2_1", "2"});
    args.add(new String[]{"data3_1", "s3_1", "3"});
    int[] updateArr = bookDao.batchUpdate("update t_book set bookName = ?, bookStatus = ? where bookId = ?", args);
    System.out.println(updateArr);
}
```
3.批量删除
```java
@Test
public void batchDelete() {
    List<Object[]> args = new ArrayList<>();
    args.add(new String[]{"1"});
    args.add(new String[]{"2"});
    args.add(new String[]{"3"});
    int[] updateArr = bookDao.batchUpdate("delete from t_book where bookId = ?", args);
    System.out.println(updateArr);
}
```