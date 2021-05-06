#### IOC操作Bean管理（xml注入集合属性）
1.注入数组类型属性  
2.注入List类型属性  
3.注入Map类型属性  
（1）创建类，定义数组、list、map、set类型属性，生成对应set方法  
```java
public class Student {
    // 1 数组类型属性
    private String[] courses;
    // 2 list集合类型属性
    private List<String> list;
    // 3 map集合类型属性
    private Map<String, String> map;
    // 4 set集合类型属性
    private Set<String> set;

    public void setCourses(String[] courses) {
        this.courses = courses;
    }
    public void setList(List<String> list) {
        this.list = list;
    }
    public void setMap(Map<String, String> map) {
        this.map = map;
    }
    public void setSet(Set<String> set) {
        this.set = set;
    }
}
```
（2）在spring配置文件中进行配置  
```xml
<!-- 1.集合类型属性注入 -->
<bean id="student" class="com.atguigu.spring5.collectiontype.Student">
    <!-- 数组类型属性注入 -->
    <property name="courses">
        <array>
            <value>java课程</value>
            <value>数据库课程</value>
        </array>
    </property>
    <!-- list类型属性注入 -->
    <property name="list">
        <list>
            <value>张三</value>
            <value>小三</value>
        </list>
    </property>
    <!-- map类型属性注入 -->
    <property name="map">
        <map>
            <entry key="JAVA" value="java"/>
            <entry key="PHP" value="php"/>
        </map>
    </property>
    <!-- set类型注入 -->
    <property name="set">
        <set>
            <value>Oracle</value>
            <value>MySQL</value>
        </set>
    </property>
</bean>
```
4.在集合里面设置对象类型值
```xml
<!-- 创建多个course对象 -->
<bean id="course1" class="com.atguigu.spring5.collectiontype.Course">
    <property name="cname" value="Spring5框架"/>
</bean>
<bean id="course2" class="com.atguigu.spring5.collectiontype.Course">
    <property name="cname" value="MyBatis框架"/>
</bean>

<!-- 注入list集合类型，值是对象 -->
<property name="courseList">
    <list>
        <ref bean="course1"/>
        <ref bean="course2"/>
    </list>
</property>
```
5.把集合注入部分提取出来  
（1）在spring配置文件中引入名称空间 util  
```xml
<?xml version="1.0" encoding="UTF-8"?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:p="http://www.springframework.org/schema/p"
       xmlns:util="http://www.springframework.org/schema/util"
       xsi:schemaLocation="http://www.springframework.org/schema/beans http://www.springframework.org/schema/beans/spring-beans.xsd
            http://www.springframework.org/schema/util http://www.springframework.org/schema/util/spring-util.xsd">
```
（2）使用util标签完成list集合注入提取  
```xml
<!-- 1.提取list集合类型属性注入 -->
<util:list id="bookList">
    <value>易筋经</value>
    <value>九阴真经</value>
    <value>九阳神功</value>
</util:list>
<!-- 2.提取list集合类型属性注入使用 -->
<bean id="book" class="com.atguigu.spring5.collectiontype.Book">
    <property name="list" ref="bookList"/>
</bean>
```