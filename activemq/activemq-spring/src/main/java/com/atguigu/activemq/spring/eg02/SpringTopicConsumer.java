package com.atguigu.activemq.spring.eg02;

import org.springframework.jms.core.JmsTemplate;

/**
 * @author shucheng
 * @date 2022/7/1 7:48
 */
public class SpringTopicConsumer {

    private JmsTemplate template;

    public JmsTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JmsTemplate template) {
        this.template = template;
    }
}
