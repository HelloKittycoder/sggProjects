#### JdbcTemplate操作数据库（添加）
1.对应数据库创建实体类  
```java
public class Book {
    private String bookId;
    private String bookName;
    private String bookStatus;
    
    // 以下省略setter和getter方法
}
```
2.编写service和dao  
（1）在dao层进行数据库添加操作  
（2）调用JdbcTemplate对象里的update方法实现添加操作  
```java
@Repository
public class BookDaoImpl implements BookDao {

    // 注入JdbcTemplate
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void addBook(Book book) {
        String sql = "insert into t_book(bookId, bookName, bookStatus) values(?, ?, ?)";
        Object[] args = {book.getBookId(), book.getBookName(), book.getBookStatus()};
        jdbcTemplate.update(sql, args);
    }
}
```
3.测试类  
```java
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:bean1.xml")
public class BookServiceTest {

    @Autowired
    private BookService bookService;

    @Test
    public void addBook() {
        Book book = new Book("1", "java", "a");
        bookService.addBook(book);
        System.out.println("添加成功");
    }
}
```