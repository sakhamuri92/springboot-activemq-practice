package com.activemq.activemqlistener;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MessageController {

    private final JmsTemplate jmsTemplate;
    private final JmsProducer jmsProducer;

    public MessageController(JmsTemplate jmsTemplate, JmsProducer jmsProducer) {
        this.jmsTemplate = jmsTemplate;
        this.jmsProducer= jmsProducer;
    }

    @GetMapping("/send")
    public String sendMessage(@RequestParam String message) {
        jmsTemplate.convertAndSend("example.queue", message);
        return "Message sent: " + message;
    }
    @GetMapping("/sendProduct")
    public String sendProduct(@RequestParam String name, @RequestParam double price) {
        Product product = new Product();
        product.setName(name);
        product.setPrice(price);
        this.jmsProducer.sendProductToQueue(product);
        return "Product sent to queue!";
    }
}
