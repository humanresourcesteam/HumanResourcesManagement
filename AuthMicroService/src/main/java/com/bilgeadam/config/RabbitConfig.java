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

    private String exchangeUpdateAuth = "exchange-update-auth";

    private String exchangeAdminControl = "exchange-admin-control";

    private String keyAuth = "key-auth";
    private String keyUpdateAuth = "key-update-auth";

    private String keyAdminControl = "key-admin-control";

    private String queueAuth = "queue-auth";

    private String queueUpdateAuth = "queue-update-auth";

    private String queueAdminControl = "queue-admin-control";
    @Bean
    public DirectExchange directExchangeUpdateAuth(){
        return new DirectExchange(exchangeUpdateAuth);
    }

    @Bean
    public DirectExchange directExchange(){
        return new DirectExchange(exchangeAuth);
    }

    @Bean
    public DirectExchange directExchangeAdminControl(){
        return new DirectExchange(exchangeAdminControl);
    }
    @Bean
    public Queue queueUpdateAuth(){
        return new Queue(queueUpdateAuth);
    }


    @Bean
    public Queue queue(){
        return new Queue(queueAuth);
    }

    @Bean
    public Queue queueAdminControl(){
        return new Queue(queueAdminControl);
    }

    @Bean
    public Binding bindingUpdateAuth(final DirectExchange directExchangeUpdateAuth,final Queue queueUpdateAuth){
        return BindingBuilder.bind(queueUpdateAuth).to(directExchangeUpdateAuth).with(keyUpdateAuth);
    }


    @Bean
    public Binding bindingCreateAdmin(final DirectExchange directExchange,final Queue queue){
        return BindingBuilder.bind(queue).to(directExchange).with(keyAuth);
    }

    @Bean
    public Binding bindingAdminControl(final DirectExchange directExchangeAdminControl,final Queue queueAdminControl){
        return BindingBuilder.bind(queueAdminControl).to(directExchangeAdminControl).with(keyAdminControl);
    }
}
