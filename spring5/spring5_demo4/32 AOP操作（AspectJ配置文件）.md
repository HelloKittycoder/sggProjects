#### AOP（AspectJ配置文件）
1.创建两个类，增强类和被增强类，创建方法  
2.在spring配置文件中创建两个类对象  
```xml
<!-- 创建对象 -->
<bean id="book" class="com.atguigu.spring5.aopxml.Book"></bean>
<bean id="bookProxy" class="com.atguigu.spring5.aopxml.BookProxy"></bean>
```
3.在spring配置文件中配置切入点  
```xml
<!-- 配置aop增强 -->
<aop:config>
    <!-- 切入点 -->
    <aop:pointcut id="p" expression="execution(* com.atguigu.spring5.aopxml.Book.buy(..))"/>
    <!-- 配置切面 -->
    <aop:aspect ref="bookProxy">
        <!-- 增强作用在具体的方法上 -->
        <aop:before method="before" pointcut-ref="p"/>
    </aop:aspect>
</aop:config>
```