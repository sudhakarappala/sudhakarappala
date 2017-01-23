package com.example;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class MessageHandler
{
    private static final Logger logger = LoggerFactory.getLogger(MessageHandler.class);
    @RabbitListener(queues =  "${rabbitmq.queue.message}")
    public void handleMessage(String str) {

        logger.info("message: "+ str+" received by consumer2");
        
      
    }

}
