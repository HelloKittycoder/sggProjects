#### 16 IOC操作Bean管理（bean作用域）
1.在Spring里面，设置创建bean实例是单实例还是多实例  
2.在Spring里面，默认情况下，bean是单实例对象  
```java
@Test
public void testCollection2() {
    // 1 加载spring配置文件
    ApplicationContext context = new ClassPathXmlApplicationContext("bean2.xml"); // 这里会创建对象
    // 2 获取配置创建的对象
    Book book1 = context.getBean("book", Book.class);
    Book book2 = context.getBean("book", Book.class);
    assertEquals(book1, book2); // 单例
}
```
3.如何设置单实例还是多实例  
（1）在spring配置文件bean标签里面有属性（scope）用于设置单实例还是多实例  
（2）scope属性值  
第一个值 默认值，singleton，表示是单实例对象  
第二个值，prototype，表示是多实例对象  
```xml
<!-- 2.提取list集合类型属性注入使用 -->
<bean id="book" class="com.atguigu.spring5.collectiontype.Book" scope="prototype">
    <property name="list" ref="bookList"/>
</bean>
```
（3）singleton和prototype区别  
第一 singleton 单实例，prototype 多实例  
第二 设置scope值是singleton时，加载spring配置文件时就会创建单实例对象  
设置scope是prototype时，不是在加载spring配置文件时创建对象，而是在调用getBean方法时创建多实例对象  