#### 事务操作（基于xml的声明式事务）
1.在spring配置文件中进行如下配置  
（1）配置事务管理器  
（2）配置通知  
（3）配置切入点，引入通知  
```java
<!-- 创建事务管理器 -->
<bean id="transactionManager" class="org.springframework.jdbc.datasource.DataSourceTransactionManager">
    <!-- 注入数据源 -->
    <property name="dataSource" ref="dataSource"/>
</bean>

<!-- 配置事务 -->
<tx:advice id="txadvice">
    <tx:attributes>
        <!-- 指定方法名的匹配规则，在能匹配上的所有方法上添加事务 -->
        <tx:method name="transferMoney" propagation="REQUIRED"/>
        <!-- 下面匹配的是以transferMoney开头的方法名 -->
        <!--<tx:method name="transferMoney*" propagation="REQUIRED"/>-->
    </tx:attributes>
</tx:advice>

<!-- 配置切入点，引入通知 -->
<aop:config>
    <!-- 配置切入点 -->
    <aop:pointcut id="pt" expression="execution(* com.atguigu.spring5.service.AccountService.*(..))"/>
    <!-- 引入通知 -->
    <aop:advisor advice-ref="txadvice" pointcut-ref="pt"/>
</aop:config>
```
2.测试事务是否起作用  
见41搭建好事务操作环境后，运行AccountServiceTest2#transferMoney，如果t_account表里两条记录的money还是1000，  
说明事务起作用了  