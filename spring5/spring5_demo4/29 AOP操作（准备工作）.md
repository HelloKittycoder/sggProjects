#### AOP（准备工作）
1.Spring框架一般都是基于AspectJ实现AOP操作  
（1）AspectJ不是Spring的组成部分，而是一个独立的AOP框架，一般会将AspectJ和Spring框架一起使用，进行AOP操作  
2.基于AspectJ实现AOP操作  
（1）基于xml配置文件实现  
（2）基于注解方式实现  
3.在项目工程里引入AOP相关依赖  
```xml
<dependency>
    <groupId>org.aspectj</groupId>
    <artifactId>aspectjweaver</artifactId>
</dependency>
```
4.切点表达式  
（1）切点表达式作用：知道对哪个类里面的哪个方法进行增强  
（2）语法结构：execution([权限修饰符] [返回类型] [类全路径] \[方法名称\]([参数列表]))  
e.g.1 对 com.atguigu.dao.BookDao 类里面的add进行增强  
```java
execution(* com.atguigu.dao.BookDao.add(..))
```
e.g.2 对 com.atguigu.dao.BookDao 类里面的所有方法进行增强  
```java
execution(* com.atguigu.dao.BookDao.*(..))
```
e.g.3 对 com.atguigu.dao 包里面的所有类，类里面的所有方法进行增强  
```java
execution(* com.atguigu.dao.*.*(..))
```