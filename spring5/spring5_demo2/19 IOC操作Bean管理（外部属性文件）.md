#### 19 IOC操作Bean管理（外部属性文件）
1.直接配置数据库信息  
（1）配置druid连接池  
（2）引入druid连接池依赖jar包  
2.引入外部属性文件配置数据库连接池  
（1）创建外部属性文件，properties格式文件，写数据库信息
```properties
prop.driverClassName=com.mysql.jdbc.Driver
prop.url=jdbc:mysql://localhost:3306/userDb
prop.username=root
prop.password=123456
```
（2）把外部properties文件引入spring配置文件中  
* 引入context名称空间  
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:util="http://www.springframework.org/schema/util"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
            http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util.xsd
            http://www.springframework.org/schema/context http://www.springframework.org/schema/context/spring-context.xsd">
```
* 在spring配置文件使用标签引入外部属性文件
```xml
<!-- 引入外部属性文件 -->
<context:property-placeholder location="classpath:jdbc.properties"/>
<!-- 配置连接池 -->
<bean id="dataSource" class="com.alibaba.druid.pool.DruidDataSource">
    <property name="driverClassName" value="${prop.driverClassName}"/>
    <property name="url" value="${prop.url}"></property>
    <property name="username" value="${prop.username}"></property>
    <property name="password" value="${prop.password}"></property>
</bean>
```