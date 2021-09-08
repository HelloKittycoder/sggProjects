package com.atguigu.gmall;

import com.alibaba.dubbo.config.spring.context.annotation.EnableDubbo;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 1.导入依赖：
 *  1）导入dubbo-starter
 *  2）导入dubbo的其他依赖
 * Created by shucheng on 2021/9/7 23:02
 */
@EnableDubbo // 开启基于注解的dubbo功能
@SpringBootApplication
public class BootUserServiceProviderApplication {

    public static void main(String[] args) {
        SpringApplication.run(BootUserServiceProviderApplication.class, args);
    }
}
