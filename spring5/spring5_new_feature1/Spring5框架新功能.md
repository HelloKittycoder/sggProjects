#### Spring5框架新功能
1.整个Spring5框架的代码基于Java8，运行时兼容JDK9，许多不建议使用的类和方法在代码库中删除  
2.Spring5框架自带了通用的日志封装  
参考：https://www.cnblogs.com/wudb/p/11511266.html（课程里讲的那个是错的，不用看）  
课程里讲的其实是slf4j和其他日志框架的整合，跟spring自带的日志封装没啥关系  
3.Spring5框架核心容器支持@Nullable注解（了解即可）  
@Nullable注解可以用在方法、属性、参数上，表示方法返回可以为空、属性值可以为空、参数值可以为空  
4.Spring5核心容器支持函数式风格创建对象GenericApplicationContext  
```java
@Test
public void testGenericApplicationContext() {
    //1 创建 GenericApplicationContext 对象
    GenericApplicationContext context = new GenericApplicationContext();
    //2 调用 context 的方法进行对象注册
    context.refresh();
    context.registerBean("user1", User.class, User::new); // 这里写new User()也可以
    //3 获取在 spring 注册的对象（如果上面注册bean的时候没用beanName，下面获取如果要用beanName的话，就写全类名）
    User user = (User) context.getBean("user1");
    System.out.println(user);
}
```
5.Spring5支持整合JUnit5  
不管是整合JUnit4还是JUnit5，为了方便使用spring的特性，首先要引入：  
```xml
<dependency>
    <groupId>org.springframework</groupId>
    <artifactId>spring-test</artifactId>
    <version>5.2.6.RELEASE</version>
    <scope>test</scope>
</dependency>
```
（1）整合JUnit4  
第一步 引入依赖
```xml
<dependency>
    <groupId>junit</groupId>
    <artifactId>junit</artifactId>
    <version>4.12</version>
    <scope>test</scope>
</dependency>
```
第二步 编写测试代码
```java
@RunWith(SpringRunner.class)
@ContextConfiguration("classpath:bean1.xml")
public class SpringJUnit4Test {

    @Autowired
    private UserService userService;

    @Test
    public void test() {
        userService.addUser();
    }
}
```
（2）整合JUnit5  
第一步 引入依赖
```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter</artifactId>
    <version>5.6.2</version>
    <scope>test</scope>
</dependency>
```
第二步 编写测试代码  
写法一：
```java
@ExtendWith(SpringExtension.class)
@ContextConfiguration("classpath:bean1.xml")
public class SpringJUnit5Test_01 {

    @Autowired
    private UserService userService;

    @Test
    public void test() {
        userService.addUser();
    }
}
```
写法二：
```java
@SpringJUnitConfig(locations = "classpath:bean1.xml")
public class SpringJUnit5Test_02 {

    @Autowired
    private UserService userService;

    @Test
    public void test() {
        userService.addUser();
    }
}
```
