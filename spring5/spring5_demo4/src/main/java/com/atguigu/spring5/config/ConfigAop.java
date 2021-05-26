package com.atguigu.spring5.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.ImportResource;

/**
 * Created by shucheng on 2021/5/26 23:46
 */
@Configuration
@ImportResource(value = "classpath:bean2.xml")
@ComponentScan(basePackages = {"com.atguigu"})
@EnableAspectJAutoProxy
public class ConfigAop {
}
