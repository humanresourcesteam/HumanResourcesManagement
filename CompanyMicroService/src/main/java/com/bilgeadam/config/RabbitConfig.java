package com.bilgeadam.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    private String exchangeWorkerCompany = "exchange-worker-company";

    private String keyWorkerCompany = "key-worker-company";

    private String queueWorkerFromCompany = "queue-worker-company";


    @Bean
    DirectExchange directExchangeWorkerCompany() {
        return new DirectExchange(exchangeWorkerCompany);
    }

    @Bean
    Queue queueWorkerCompany() {
        return new Queue(queueWorkerFromCompany);
    }

    @Bean
    Binding bindingWorkerCompany(DirectExchange directExchangeWorkerCompany, Queue queueWorkerCompany) {
        return BindingBuilder.bind(queueWorkerCompany).to(directExchangeWorkerCompany).with(keyWorkerCompany);
    }
}
