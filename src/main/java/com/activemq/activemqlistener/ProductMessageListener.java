package com.activemq.activemqlistener;

import jakarta.jms.Message;
import jakarta.jms.ObjectMessage;
import jakarta.jms.MessageListener;

public class ProductMessageListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        try {
            if (message instanceof ObjectMessage) {
                Product product = (Product) ((ObjectMessage) message).getObject();
                System.out.println("Listener received Product: " + product);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}