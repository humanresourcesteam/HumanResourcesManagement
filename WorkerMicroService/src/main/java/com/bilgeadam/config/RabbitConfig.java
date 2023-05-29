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
    private String exchangeCreateWorkerForAuth = "exchange-worker-auth";
    private String keyWorkerAuth = "key-worker-auth";
    private String queueWorkerAuth = "queue-worker-auth";
    @Bean
    DirectExchange directExchangeWorkerAuth() {
        return new DirectExchange(exchangeCreateWorkerForAuth);
    }
    @Bean
    Queue queueWorkerAuth() {
        return new Queue(queueWorkerAuth);
    }
    @Bean
    Binding bindingWorkerAuth(DirectExchange directExchangeWorkerAuth, Queue queueWorkerAuth) {
        return BindingBuilder.bind(queueWorkerAuth).to(directExchangeWorkerAuth).with(keyWorkerAuth);
    }
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
