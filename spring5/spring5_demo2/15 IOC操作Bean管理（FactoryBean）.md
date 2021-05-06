#### 15 IOC操作Bean管理（FactoryBean）
1.Spring有两种类型bean，一种是普通bean，另外一种是工厂bean（FactoryBean）  
2.普通bean：在配置文件中定义bean类型就是返回类型  
3.工厂bean：在配置文件定义bean类型可以和返回类型不一样  
第一步 创建类，让这个类作为工厂bean，实现接口FactoryBean  
第二步 实现接口里面的方法，在实现的方法中定义返回的bean类型  
```java
public class MyBean implements FactoryBean<Course> {
    // 定义返回bean
    @Override
    public Course getObject() throws Exception {
        Course course = new Course();
        course.setCname("abc");
        return course;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

    @Override
    public boolean isSingleton() {
        return false;
    }
}
```
```xml
<bean id="myBean" class="com.atguigu.spring5.factorybean.MyBean">
</bean>
```
```java
@Test
public void test3() {
    // 1 加载spring配置文件
    ApplicationContext context = new ClassPathXmlApplicationContext("bean3.xml"); // 这里会创建对象
    // 2 获取配置创建的对象
    Course course = context.getBean("myBean", Course.class);
    System.out.println(course);
}
```