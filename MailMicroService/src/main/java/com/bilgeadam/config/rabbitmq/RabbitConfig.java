package com.bilgeadam.config.rabbitmq;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    //
    private String exchangeMail = "exchange-mail";
    private String keyMailForManager = "key-mail-for-manager";
    private String queueMailForManager = "queue-mail-for-maanager";

    //FORGOT-PASSWORD START
    private String keyAuthFromMail = ("key-auth-from-mail");
    private String queueAuthFromMail = ("queue-auth-from-mail");
    @Bean
    Queue queueAuthFromMail() {
        return new Queue(queueAuthFromMail);
    }
    @Bean
    Binding bindingAuthFromMail(final Queue queueAuthFromMail, final DirectExchange directExchangeMail) {
        return BindingBuilder.bind(queueAuthFromMail).to(directExchangeMail).with(keyAuthFromMail);
    }
    //FORGOT-PASSWORD END

    @Bean
    DirectExchange directExchangeMail() {
        return new DirectExchange(exchangeMail);
    }
    @Bean
    Queue queueMailForManager() {
        return new Queue(queueMailForManager);
    }
    @Bean
    Binding bindingMailForManager(final Queue queueMailForManager, final DirectExchange directExchangeMail) {
        return BindingBuilder.bind(queueMailForManager).to(directExchangeMail).with(keyMailForManager);
    }
    //

}
