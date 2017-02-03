package com.example;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.AmqpException;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessagePostProcessor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;


@Service
public class RabbitmqProducerService
{

    private static final Logger logger = LoggerFactory.getLogger(
        RabbitmqProducerService.class);
    @Value("${rabbitmq.queue.message}")
    String messageQueueName;

    @Value("${rabbitmq.exchange.messageexchange}")
    String exchange;

    
    @Autowired
    RabbitTemplate rabbitMessagingTemplate;

  public void send(String str)
  {


      logger.info("Sending message to rabbit "+str);
       rabbitMessagingTemplate.convertAndSend(exchange,
       messageQueueName, str, new MessagePostProcessor() {
        
        @Override
        public Message postProcessMessage(Message message) throws AmqpException
        {
            message.getMessageProperties().setReplyTo("my-reply-queue");
            return message;
        }
    });

  }
}
