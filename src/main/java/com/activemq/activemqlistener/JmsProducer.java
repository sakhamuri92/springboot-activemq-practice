package com.activemq.activemqlistener;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class JmsProducer {

    private final JmsTemplate jmsTemplate;

    public JmsProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendProductToQueue(Product product) {
        jmsTemplate.convertAndSend("product.queue", product);  // Send the Product object directly
        System.out.println("Sent Product: " + product);
    }
}
