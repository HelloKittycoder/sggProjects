package com.atguigu.spring5;

import com.atguigu.spring5.po.User;
import org.junit.Test;
import org.springframework.context.support.GenericApplicationContext;

/**
 * Created by shucheng on 2021/8/8 10:32
 */
public class TestSpring5 {

    @Test
    public void testGenericApplicationContext() {
        //1 创建 GenericApplicationContext 对象
        GenericApplicationContext context = new GenericApplicationContext();
        //2 调用 context 的方法进行对象注册
        context.refresh();
        context.registerBean("user1", User.class, User::new); // 这里写new User()也可以
        //3 获取在 spring 注册的对象（如果上面注册bean的时候没用beanName，下面获取如果要用beanName的话，就写全类名）
        User user = (User) context.getBean("user1");
        System.out.println(user);
    }
}
