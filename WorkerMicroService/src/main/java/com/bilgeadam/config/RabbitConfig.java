package com.bilgeadam.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    private String exchangeWorkerManager = "exchange-worker-manager";

    private String keyWorkerManager = "key-worker-manager";

    private String queueWorkerFromManager = "queue-worker-manager";


    @Bean
    DirectExchange directExchangeWorkerManager() {
        return new DirectExchange(exchangeWorkerManager);
    }

    @Bean
    Queue queueWorkerManager() {
        return new Queue(queueWorkerFromManager);
    }

    @Bean
    Binding bindingWorkerManager(DirectExchange directExchangeWorkerManager, Queue queueWorkerManager) {
        return BindingBuilder.bind(queueWorkerManager).to(directExchangeWorkerManager).with(keyWorkerManager);
    }

}
