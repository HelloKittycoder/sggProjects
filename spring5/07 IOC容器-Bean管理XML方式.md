### IOC操作Bean管理（基于xml方式）
1.基于xml方式创建对象  
```xml
<!-- 配置User对象创建 -->
<bean id="user" class="com.atguigu.spring5.User"></bean>
```
（1）在spring配置文件中，使用bean标签，标签里面添加对应属性，就可以实现对象创建  
（2）bean标签有很多属性，介绍常用的属性  
\* id属性：唯一标识  
\* class属性：类全路径（包类路径）  
（3）创建对象时，默认也是执行无参数构造方法完成对象创建  

2.基于xml方式注入属性  
DI：依赖注入，就是注入属性  

3.第一种注入方式：使用set方法进行注入  
（1）创建类，定义属性和对应的set方法  
```java
public class Book {
    // 创建属性
    private String bname;
    private String bauthor;
    // 创建属性对应的set方法
    public void setBname(String bname) {
        this.bname = bname;
    }
    public void setBauthor(String bauthor) {
        this.bauthor = bauthor;
    }
}
```
（2）在spring配置文件中配置对象创建，配置属性注入
```xml
<!-- 2.set方法注入属性 -->
<bean id="book" class="com.atguigu.spring5.Book">
    <!-- 使用property完成属性注入
     name：类里面属性名称
     value：向属性注入的值 -->
    <property name="bname" value="易筋经"/>
    <property name="bauthor" value="达摩老祖"/>
</bean>
```