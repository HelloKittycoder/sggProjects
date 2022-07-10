package com.atguigu.activemq.spring.eg01;

import org.springframework.jms.core.JmsTemplate;

/**
 * @author shucheng
 * @date 2022/6/29 8:10
 */
public class SpringQueueProducer {

    private JmsTemplate template;

    public JmsTemplate getTemplate() {
        return template;
    }

    public void setTemplate(JmsTemplate template) {
        this.template = template;
    }
}
