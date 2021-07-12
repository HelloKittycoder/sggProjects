#### JdbcTemplate（概述和准备工作）
1.什么是JdbcTemplate  
（1）Spring框架对JDBC做的封装，使用JdbcTemplate可以方便的进行数据库操作  
2.准备工作  
（1）引入相关jar包  
druid、spring-jdbc、spring-tx  
（2）在spring配置文件配置数据库连接池  
```xml
<bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource"
      destroy-method="close">
    <property name="url" value="jdbc:mysql:///sgg_spring5_user_db?useUnicode=true&amp;characterEncoding=UTF-8"/>
    <property name="username" value="root"/>
    <property name="password" value="123456"/>
    <property name="driverClassName" value="com.mysql.jdbc.Driver"/>
</bean>
```
（3）配置JdbcTemplate对象，注入DataSource  
```xml
<!-- JdbcTemplate对象 -->
<bean id="jdbcTemplate" class="org.springframework.jdbc.core.JdbcTemplate">
    <!-- 注入dataSource -->
    <property name="dataSource" ref="dataSource"/>
</bean>
```
（4）创建service类、dao类，在dao注入jdbcTemplate对象  
* 配置文件
```xml
<!-- 组件扫描 -->
<context:component-scan base-package="com.atguigu"/>
```
* Service
```java
@Service
public class BookService {
    // 注入dao
    @Autowired
    private BookDao bookDao;
}
```
* Dao
```java
@Repository
public class BookDaoImpl implements BookDao {
    // 注入JdbcTemplate
    @Autowired
    private JdbcTemplate jdbcTemplate;
}
```