package com.bilgeadam.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    private String exchangeAuth = "exchange-auth";

    private String keyAuth = "key-auth";

    private String queueAuth = "queue-auth";


    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(exchangeAuth);
    }

    @Bean
    public Queue queue(){
        return new Queue(queueAuth);
    }

    @Bean
    public Binding bindingCreateAdmin(final DirectExchange directExchange,final Queue queue){
       return BindingBuilder.bind(queue).to(directExchange).with(keyAuth);
    }
}
