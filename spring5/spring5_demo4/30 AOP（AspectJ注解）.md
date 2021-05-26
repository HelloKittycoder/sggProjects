#### AOP（AspectJ注解）
1.创建类，在类里面定义方法  
```java
public class User {
    public void add() {
        System.out.println("add......");
    }
}
```
2.创建增强类（编写增强逻辑）  
（1）在增强类里面，创建方法，让不同方法代表不同通知类型  
```java
// 增强的类
public class UserProxy {
    public void before() { // 前置通知
        System.out.println("before......");
    }
}
```
3.进行通知的配置  
（1）在spring配置文件中，开启注解扫描  
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:context="http://www.springframework.org/schema/context"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:aop="http://www.springframework.org/schema/aop"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
            http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd
            http://www.springframework.org/schema/aop http://www.springframework.org/schema/aop/spring-aop.xsd">

    <!-- 开启组件扫描 -->
    <context:component-scan base-package="com.atguigu.spring5.aopanno"/>
</beans>
```
（2）使用注解创建User和UserProxy对象  
```java
// 被增强的类
@Component
public class User {}
// 增强的类
@Component
public class UserProxy {}
```
（3）在增强类上面添加注解@Aspect  
```java
// 增强的类
@Component
@Aspect // 生成代理对象
public class UserProxy {}
```
（4）在spring配置文件中开启生成代理对象  
```xml
<!-- 开启Aspect生成代理对象 -->
<aop:aspectj-autoproxy/>
```
4.配置不同类型的通知  
（1）在增强类的里面，在座位通知方法上面添加通知类型注解，使用切入点表达式配置  
```java
// 增强的类
@Component
@Aspect // 生成代理对象
public class UserProxy {
    // 前置通知
    @Before(value = "execution(* com.atguigu.spring5.aopanno.User.add(..))")
    public void before() { // 前置通知
        System.out.println("before......");
    }

    // 后置通知
    @AfterReturning(value = "execution(* com.atguigu.spring5.aopanno.User.add(..))")
    public void afterReturning() {
        System.out.println("afterReturning......");
    }

    // 最终通知
    @After(value = "execution(* com.atguigu.spring5.aopanno.User.add(..))")
    public void after() {
        System.out.println("after......");
    }

    // 异常通知
    @AfterThrowing(value = "execution(* com.atguigu.spring5.aopanno.User.add(..))")
    public void afterThrowing() {
        System.out.println("afterThrowing......");
    }

    // 环绕通知
    @Around(value = "execution(* com.atguigu.spring5.aopanno.User.add(..))")
    public void around(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        System.out.println("环绕之前......");
        // 被增强的方法执行
        proceedingJoinPoint.proceed();
        System.out.println("环绕之后......");
    }
}
```

