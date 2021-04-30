### 10 IOC操作Bean管理（xml注入其他类型属性）
1.字面量  
（1）null值  
```xml
<!-- null值 -->
<property name="address">
    <null/>
</property>
```
（2）属性值包含特殊符号  
```xml
<!-- 属性值包含特殊符号
 1.把<>进行转义 &lt;&gt;
 2.把带特殊符号内容写到CDATA -->
<property name="address">
    <!--<value>&lt;&lt;南京&gt;&gt;</value>-->
    <value><![CDATA[<<南京>>]]></value>
</property>
```