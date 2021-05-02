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