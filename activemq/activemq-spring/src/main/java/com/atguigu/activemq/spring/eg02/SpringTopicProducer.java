package com.atguigu.activemq.spring.eg02;

import org.springframework.jms.core.JmsTemplate;

/**
 * @author shucheng
 * @date 2022/6/29 8:10
 */
public class SpringTopicProducer {

    private JmsTemplate template;

    public JmsTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JmsTemplate template) {
        this.template = template;
    }
}
