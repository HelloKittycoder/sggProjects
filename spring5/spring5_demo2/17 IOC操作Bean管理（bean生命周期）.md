#### 17 IOC操作Bean管理（bean生命周期）
1.生命周期  
从对象创建到对象销毁的过程  
2.bean的生命周期  
（1）通过构造器创建bean实例（无参数构造）  
（2）为bean的属性设置值和其他bean的引用（调用set方法）  
（3）调用bean的初始化方法（需要配置初始化方法）  
（4）bean可以使用了（对象获取到了）  
（5）当容器关闭时，调用bean的销毁方法（需要配置销毁的方法）  
3.演示bean的生命周期  
```java
public class Orders {
    private String oname;
    public void setOname(String oname) {
        this.oname = oname;
        System.out.println("第二步 调用set方法设置属性值");
    }
    // 无参数构造
    public Orders() {
        System.out.println("第一步 执行无参数构造创建bean实例");
    }
    // 创建执行的初始化方法
    public void initMethod() {
        System.out.println("第三步 执行初始化的方法");
    }
    // 创建执行的销毁方法
    public void destroyMethod() {
        System.out.println("第五步 执行销毁的方法");
    }
}
```
```xml
<bean id="orders" class="com.atguigu.spring5.bean.Orders" init-method="initMethod" destroy-method="destroyMethod">
    <property name="oname" value="手机"></property>
</bean>
```
```java
// 测试bean的生命周期
@Test
public void testBean4() {
    // 1 加载spring配置文件
    // ApplicationContext context = new ClassPathXmlApplicationContext("bean4.xml"); // 这里会创建对象
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("bean4.xml"); // 这里会创建对象
    // 2 获取配置创建的对象
    System.out.println("第四步 获取创建的bean实例对象");
    Orders orders = context.getBean("orders", Orders.class);
    System.out.println(orders);
    // 手动让bean实例销毁
    context.close();
}
```
第一步 执行无参数构造创建bean实例  
第二步 调用set方法设置属性值  
第三步 执行初始化的方法  
第四步 获取创建好的bean实例对象  
第五步 执行销毁的方法  
4.bean的后置处理器，bean生命周期有七步  
（1）通过构造器创建bean实例（无参数构造）  
（2）为bean的属性设置值和对其他bean的引用（调用set方法）  
（3）把bean实例传递bean给bean后置处理器的方法postProcessBeforeInitialization  
（4）调用bean的初始化的方法（需要进行配置初始化方法）  
（5）把bean实例传递bean给bean后置处理器的方法postProcessBeforeInitialization  
（6）bean可以使用了（对象获取到了）  
（7）当容器关闭时，调用bean的销毁方法（需要配置销毁方法）  
5.演示添加后置处理器的效果  
（1）创建类，实现接口BeanPostProcessor，创建后置处理器  
```java
public class MyBeanPost implements BeanPostProcessor {
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("在初始化之前执行的方法");
        return bean;
    }
    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        System.out.println("在初始化之后执行的方法");
        return bean;
    }
}
```
```xml
<!-- 配置后置处理器 -->
<bean id="myBeanPost" class="com.atguigu.spring5.bean.MyBeanPost"></bean>
```
