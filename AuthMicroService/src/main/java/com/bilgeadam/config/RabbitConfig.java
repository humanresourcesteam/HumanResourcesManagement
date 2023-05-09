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

    private String exchangeManagerAuth = "exchange-manager-auth";

    private String keyManagerAddAuth = "exchange-manager-auth";

    private String queueAddAuthFromManager = "queue-auth-manager-add";


    //
    private String exchangeMail = "exchange-mail";

    private String keyMailForManager = "key-mail-for-manager";

    private String queueMailForManager = "queue-mail-for-maanager";


    @Bean
    DirectExchange directExchangeMail() {
        return new DirectExchange(exchangeMail);
    }

    @Bean
    Queue queueMailForManager(){
        return new Queue(queueMailForManager);
    }

    @Bean
    Binding bindingMailForManager(final Queue queueMailForManager,final DirectExchange directExchangeMail){
        return BindingBuilder.bind(queueMailForManager).to(directExchangeMail).with(keyMailForManager);
    }
    //



    @Bean
    public DirectExchange directExchangeUpdateAuth() {
        return new DirectExchange(exchangeUpdateAuth);
    }

    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange(exchangeAuth);
    }

    @Bean
    public DirectExchange directExchangeAdminControl() {
        return new DirectExchange(exchangeAdminControl);
    }

    @Bean
    DirectExchange directExchangeManagerAuth() {
        return new DirectExchange(exchangeManagerAuth);
    }

    @Bean
    public Queue queueUpdateAuth() {
        return new Queue(queueUpdateAuth);
    }


    @Bean
    public Queue queue() {
        return new Queue(queueAuth);
    }

    @Bean
    public Queue queueAdminControl() {
        return new Queue(queueAdminControl);
    }


    @Bean
    Queue queueManagerAddAuth() {
        return new Queue(queueAddAuthFromManager);
    }

    @Bean
    Binding bindingAddManagerAuth(DirectExchange directExchangeManagerAuth, Queue queueManagerAddAuth) {
        return BindingBuilder.bind(queueManagerAddAuth).to(directExchangeManagerAuth).with(keyManagerAddAuth);
    }

    @Bean
    public Binding bindingUpdateAuth(final DirectExchange directExchangeUpdateAuth, final Queue queueUpdateAuth) {
        return BindingBuilder.bind(queueUpdateAuth).to(directExchangeUpdateAuth).with(keyUpdateAuth);
    }


    @Bean
    public Binding bindingCreateAdmin(final DirectExchange directExchange, final Queue queue) {
        return BindingBuilder.bind(queue).to(directExchange).with(keyAuth);
    }

    @Bean
    public Binding bindingAdminControl(final DirectExchange directExchangeAdminControl, final Queue queueAdminControl) {
        return BindingBuilder.bind(queueAdminControl).to(directExchangeAdminControl).with(keyAdminControl);
    }
}
