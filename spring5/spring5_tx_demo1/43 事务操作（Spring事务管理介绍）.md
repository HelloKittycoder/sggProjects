#### 事务操作（Spring事务管理介绍）
1.事务一般添加到JavaEE三层结构里Service层（业务逻辑层）  
如果要加其他层，也能加，但一般不会这么做  
2.在Spring进行事务管理操作  
有两种方式：声明式事务管理和编程式事务管理  
一般用的比较多的是声明式事务管理（lsc注：课程里只讲了声明式事务管理）
3.声明式事务管理  
（1）基于注解方式  
（2）基于xml配置文件方式  
4.在Spring进行声明式事务管理，底层用的是AOP  
5.Spring事务管理API  
PlatformTransactionManager接口，常用的实现类是DataSourceTransactionManager