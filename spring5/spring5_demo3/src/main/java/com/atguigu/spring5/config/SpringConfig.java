package com.atguigu.spring5.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by shucheng on 2021/5/20 9:04
 */
@Configuration // 作为配置类，替代xml配置文件
@ComponentScan(basePackages = {"com.atguigu"})
public class SpringConfig {
}
