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
    private String exchangeManagerCompany = "exchange-manager-company";

    private String keyManagerCompany = "exchange-manager-company";

    private String queueCompanyManager = "queue-manager-company";

    @Bean
    DirectExchange directExchangeCompanyManager() {
        return new DirectExchange(exchangeManagerCompany);
    }

    @Bean
    Queue queueManagerCompany() {
        return new Queue(queueCompanyManager);
    }

    @Bean
    Binding bindingCompanyManager(DirectExchange directExchangeCompanyManager, Queue queueManagerCompany) {
        return BindingBuilder.bind(queueManagerCompany).to(directExchangeCompanyManager).with(keyManagerCompany);
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
