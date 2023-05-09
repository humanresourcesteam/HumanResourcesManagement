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


    @Bean
    DirectExchange directExchangeMail() {
        return new DirectExchange(exchangeMail);
    }

    @Bean
    Queue queueMailForManager(){
        return new Queue(queueMailForManager);
    }

    @Bean
    Binding bindingMailForManager(final Queue queueMailForManager, final DirectExchange directExchangeMail){
        return BindingBuilder.bind(queueMailForManager).to(directExchangeMail).with(keyMailForManager);
    }
    //

}
