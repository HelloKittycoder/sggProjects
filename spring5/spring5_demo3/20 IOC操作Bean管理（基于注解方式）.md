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