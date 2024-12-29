package com.activemq.activemqlistener;


import org.springframework.stereotype.Component;

import jakarta.jms.JMSException;
import jakarta.jms.Message;
import jakarta.jms.MessageListener;
import jakarta.jms.TextMessage;

@Component
public class CustomMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof TextMessage) {
                TextMessage textMessage = (TextMessage) message;
                System.out.println("Received Message: " + textMessage.getText());
            } else {
                System.out.println("Received Non-Text Message");
            }
        } catch (JMSException e) {
            e.printStackTrace();
        }
    }
}
