package com.atguigu.spring5;

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
}
