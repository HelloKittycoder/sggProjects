package com.atguigu.spring5;

import com.atguigu.spring5.autowire.Emp;
import com.atguigu.spring5.bean.Orders;
import com.atguigu.spring5.collectiontype.Book;
import com.atguigu.spring5.collectiontype.Course;
import com.atguigu.spring5.collectiontype.Student;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import static org.junit.Assert.assertNotEquals;

/**
 * Created by shucheng on 2021/5/2 20:57
 */
public class TestSpring5Demo1 {

    @Test
    public void testCollection() {
        // 1 加载spring配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("bean1.xml"); // 这里会创建对象
        // 2 获取配置创建的对象
        Student student = context.getBean("student", Student.class);
        System.out.println(student);
    }

    @Test
    public void testCollection2() {
        // 1 加载spring配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("bean2.xml"); // 这里会创建对象
        // 2 获取配置创建的对象
        Book book1 = context.getBean("book", Book.class);
        Book book2 = context.getBean("book", Book.class);
        // assertEquals(book1, book2); // 单例
        assertNotEquals(book1, book2); // 多例
    }

    // 测试工厂bean
    @Test
    public void test3() {
        // 1 加载spring配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("bean3.xml"); // 这里会创建对象
        // 2 获取配置创建的对象
        Course course = context.getBean("myBean", Course.class);
        System.out.println(course);
    }

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

    // 测试xml自动装配
    @Test
    public void test5() {
        // 1 加载spring配置文件
        ApplicationContext context = new ClassPathXmlApplicationContext("bean5.xml"); // 这里会创建对象
        // 2 获取配置创建的对象
        Emp emp = context.getBean("emp", Emp.class);
        System.out.println(emp);
    }
}
