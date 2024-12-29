package com.activemq.activemqlistener;


import org.apache.activemq.ActiveMQConnectionFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.jms.DefaultJmsListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.config.DefaultJmsListenerContainerFactory;
import org.springframework.jms.config.JmsListenerContainerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.listener.MessageListenerContainer;

import jakarta.jms.ConnectionFactory;
import jakarta.jms.MessageListener;

@Configuration
public class JmsConfig {


    String BROKER_URL = "tcp://localhost:61616"; 
	String BROKER_USERNAME = "admin"; 
	String BROKER_PASSWORD = "admin";
	
	@Bean
	public ConnectionFactory connectionFactory(){
	    ActiveMQConnectionFactory connectionFactory = new ActiveMQConnectionFactory();
	    connectionFactory.setBrokerURL(BROKER_URL);
	    connectionFactory.setPassword(BROKER_USERNAME);
	    connectionFactory.setUserName(BROKER_PASSWORD);
        connectionFactory.setTrustAllPackages(true); 
	    return connectionFactory;
	}

	@Bean
	public JmsTemplate jmsTemplate(ConnectionFactory connectionFactory){
	    return new JmsTemplate(connectionFactory);
	}

    @Bean
    public org.springframework.jms.config.DefaultJmsListenerContainerFactory jmsListenerContainerFactory(ConnectionFactory connectionFactory) {
        org.springframework.jms.config.DefaultJmsListenerContainerFactory factory = 
            new org.springframework.jms.config.DefaultJmsListenerContainerFactory();
        factory.setConnectionFactory(connectionFactory);
        factory.setConcurrency("3-10");

        return factory;
    }
    
      @Bean
    public MessageListenerContainer messageListenerContainer(ConnectionFactory connectionFactory, MessageListener messageListener,MessageListener productMessageListener) {
        DefaultMessageListenerContainer container = new DefaultMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.setDestinationName("product.queue");
        container.setMessageListener(productMessageListener);
        return container;
    }
    @Bean
    public MessageListener messageListener() {
        return new ProductMessageListener(); 
    }
    @Bean
    public MessageListener productMessageListener() {
        return new ProductMessageListener();
    }
}
