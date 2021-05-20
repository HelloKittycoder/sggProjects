#### IOC操作Bean管理（基于注解方式）
1.什么是注解  
（1）注解是代码特殊标记，格式：@注解名称（属性名称=属性值,属性名称=属性值...）  
（2）使用注解，注解作用在类上面，方法上面，属性上面  
（3）使用注解的目的：简化xml配置  
2.Spring针对Bean管理中创建对象提供注解  
（1）@Component  
（2）@Service  
（3）@Controller  
（4）@Respository  
\*上面四个注解功能是一样的，都可以用来创建bean实例  
3.基于注解方式实现对象创建  
第一步 开启注解扫描  
```xml
<!-- 开启组件扫描：
     1.如果扫描多个包，多个包使用逗号隔开
     2.扫描包上层目录 -->
<context:component-scan base-package="com.atguigu"/>
```
第二步 创建类，在类上面添加创建对象组件  
```java
/**
 * 在注解里value属性值可以省略不写，
 * 默认值是类名称，首字母小写
 * UserService -- userService
 */
@Component(value = "userService") // <bean id="userService" class=""/>
public class UserService {
    public void add() {
        System.out.println("service add...");
    }
}
```
4.开启组件扫描细节配置  
```xml
<!-- 示例1
    use-default-filters="false"，表示现在不使用默认filter，自己配置filter
    context:include-filter，设置扫描哪些内容
-->
<context:component-scan base-package="com.atguigu" use-default-filters="false">
    <context:include-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
</context:component-scan>

<!-- 示例2
    下面配置扫描包所有内容
    context:exclude-filter，设置哪些内容不进行扫描
-->
<context:component-scan base-package="com.atguigu">
    <context:exclude-filter type="annotation" expression="org.springframework.stereotype.Controller"/>
</context:component-scan>
```
5.基于注解方式实现属性注入  
（1）@Autowired：根据属性类型进行自动装配  
第一步 创建service和dao对象，在service和dao类添加创建对象注解  
第二步 在service注入dao对象，在service类添加dao类型属性，在属性上面使用注解  
```java
@Service
public class UserService {

    // 定义dao类型属性
    // 不需要添加set方法
    // 添加注入属性注解
    @Autowired
    private UserDao userDao;

    public void add() {
        System.out.println("service add...");
        userDao.add();
    }
}
```
（2）@Qualifier：根据属性名称进行注入  
```java
// 定义dao类型属性
// 不需要添加set方法
// 添加注入属性注解
@Autowired // 根据类型注入
@Qualifier(value = "userDaoImpl1") // 根据名称进行注入
private UserDao userDao;
```
（3）@Resource：可以根据类型注入，可以根据名称注入  
```java
// @Resource // 根据类型进行注入
@Resource(name = "userDaoImpl1") // 根据名称进行注入
private UserDao userDao;
```
（4）@Value：注入普通类型属性  
```java
@Value(value = "abc")
private String name;
```