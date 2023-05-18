package com.bilgeadam.rabbitmq.producer;

import com.bilgeadam.rabbitmq.model.PasswordForgot;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailProducer {

    private final RabbitTemplate rabbitTemplate;

    public String forgotPassword(PasswordForgot passwordForgot){
        return (String) rabbitTemplate.convertSendAndReceive("exchange-mail","key-auth-from-mail",passwordForgot);
    }
}
