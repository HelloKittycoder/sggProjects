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
3.注入属性-内部bean  
（1）一对多关系：部门和员工  
一个部门有多个员工，一个员工属于一个部门  
部门是一，员工是多  
（2）在实体类之间表示一对多关系，员工表示所属部门，使用对象类型属性进行表示  
```java
// 部门类
public class Dept {
    private String dname;
    public void setDname(String dname) {
        this.dname = dname;
    }
}
// 员工类
public class Emp {
    private String ename;
    private String gender;
    // 员工属于某一个部门，使用对象形式表示
    private Dept dept;
    public void setEname(String ename) {
        this.ename = ename;
    }
    public void setGender(String gender) {
        this.gender = gender;
    }
    public void setDept(Dept dept) {
        this.dept = dept;
    }
}
```
（3）在spring配置文件中进行配置
```xml
<bean id="emp" class="com.atguigu.spring5.bean.Emp">
    <!-- 设置两个普通属性 -->
    <property name="ename" value="lucy"/>
    <property name="gender" value="女"/>
    <!-- 设置对象类型属性 -->
    <property name="dept">
        <!-- 这里id写不写都行 -->
        <bean id="dept" class="com.atguigu.spring5.bean.Dept">
            <property name="dname" value="安保部"/>
        </bean>
    </property>
</bean>
```
lsc备注：内部bean没有办法重复使用，没法注入到别的bean里，用applicationContext也获取不到  
4.注入属性-级联赋值  
（1）写法一：  
```xml
<bean id="emp" class="com.atguigu.spring5.bean.Emp">
    <!-- 设置两个普通属性 -->
    <property name="ename" value="lucy"/>
    <property name="gender" value="女"/>
    <property name="dept" ref="dept"/>
</bean>
<bean id="dept" class="com.atguigu.spring5.bean.Dept">
    <property name="dname" value="财务部"/>
</bean>
```
（2）写法二：  
```xml
<bean id="emp" class="com.atguigu.spring5.bean.Emp">
    <!-- 设置两个普通属性 -->
    <property name="ename" value="lucy"/>
    <property name="gender" value="女"/>
    <property name="dept" ref="dept"/>
    <property name="dept.dname" value="技术部"/>
</bean>
<bean id="dept" class="com.atguigu.spring5.bean.Dept"></bean>
```
这种写法需要生成dept的get方法才能使用