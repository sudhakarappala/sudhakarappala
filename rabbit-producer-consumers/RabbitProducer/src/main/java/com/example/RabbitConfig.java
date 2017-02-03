package com.example;

import org.springframework.amqp.core.AmqpAdmin;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig{
    
    @Value("${rabbitmq.queue.message}")
    String messageQueueName;
    
    @Value("${rabbitmq.exchange.messageexchange}")
    String exchange;

    @Autowired
    RabbitTemplate rabbitTemplate;
 
    @Autowired
    private ConnectionFactory connectionFactory;

    @Bean
    public AmqpAdmin rabbitAdmin()
    {
        return new RabbitAdmin(connectionFactory);
    }

    @Bean
    public DirectExchange exchange()
    {
        return new DirectExchange(exchange, true, false);
    }

    @Bean
    Queue messageQueueName()
    {
        return new Queue(messageQueueName, true, false, false);
    }

    @Bean
    public Queue replyQueue() {
        return new Queue("my-reply-queue");
    }

}
