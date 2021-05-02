### 10 IOC操作Bean管理（xml注入其他类型属性）
1.字面量  
（1）null值  
```xml
<!-- null值 -->
<property name="address">
    <null/>
</property>
```
（2）属性值包含特殊符号  
```xml
<!-- 属性值包含特殊符号
 1.把<>进行转义 &lt;&gt;
 2.把带特殊符号内容写到CDATA -->
<property name="address">
    <!--<value>&lt;&lt;南京&gt;&gt;</value>-->
    <value><![CDATA[<<南京>>]]></value>
</property>
```
2.注入属性-外部bean  
（1）创建两个类service类和dao类  
（2）在service调用dao里面的方法  
（3）在spring配置文件中进行配置  
```java
public class UserService {
    // 创建UserDao类型属性，生成set方法
    private UserDao userDao;
    public void setUserDao(UserDao userDao) {
        this.userDao = userDao;
    }
    public void add() {
        System.out.println("service add...");
        userDao.update();
    }
}
```
```xml
<!-- 1.service和dao对象创建 -->
<bean id="userService" class="com.atguigu.spring5.service.UserService">
    <!-- 注入userDao对象
     name属性：类里面属性名称
     ref属性：创建userDao对象bean标签id值
     -->
    <property name="userDao" ref="userDaoImpl"/>
</bean>
<bean id="userDaoImpl" class="com.atguigu.spring5.dao.UserDaoImpl"></bean>
```