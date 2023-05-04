package com.bilgeadam.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {


    private String exchangeManagerAuth = "exchange-manager-auth";

    private String keyManagerAddAuth = "exchange-manager-auth";

    private String queueAddAuthFromManager = "queue-auth-manager-add";


    @Bean
    DirectExchange directExchangeManagerAuth() {
        return new DirectExchange(exchangeManagerAuth);
    }

    @Bean
    Queue queueManagerAddAuth() {
        return new Queue(queueAddAuthFromManager);
    }

    @Bean
    Binding bindingAddManagerAuth(DirectExchange directExchangeManagerAuth, Queue queueManagerAddAuth) {
        return BindingBuilder.bind(queueManagerAddAuth).to(directExchangeManagerAuth).with(keyManagerAddAuth);
    }
}
