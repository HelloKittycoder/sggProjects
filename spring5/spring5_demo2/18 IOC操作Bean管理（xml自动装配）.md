#### 18 IOC操作Bean管理（xml自动装配）
1.什么是自动装配  
（1）根据指定装配规则（属性名称或属性类型），Spring自动将匹配的属性值进行注入  
2.演示自动装配  
（1）根据属性名称自动注入  
```xml
<!-- 实现自动装配
bean标签属性autowire，配置自动装配
autowire属性常用的两个值：
byName 根据属性名称注入，注入值bean的id和类属性名称一样
byType 根据属性类型注入
 -->
<bean id="emp" class="com.atguigu.spring5.autowire.Emp" autowire="byName">
    <!--<property name="dept" ref="dept"/>-->
</bean>
<bean id="dept" class="com.atguigu.spring5.autowire.Dept"></bean>
```
（2）根据属性类型自动注入  
```xml
<!-- 实现自动装配
bean标签属性autowire，配置自动装配
autowire属性常用的两个值：
byName 根据属性名称注入，注入值bean的id和类属性名称一样
byType 根据属性类型注入
 -->
<bean id="emp" class="com.atguigu.spring5.autowire.Emp" autowire="byType">
    <!--<property name="dept" ref="dept"/>-->
</bean>
<bean id="dept" class="com.atguigu.spring5.autowire.Dept"></bean>
```