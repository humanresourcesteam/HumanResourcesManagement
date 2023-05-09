package com.bilgeadam.rabbitmq.producer;

import com.bilgeadam.rabbitmq.model.CreateModel;
import com.bilgeadam.rabbitmq.model.MailManagerPassword;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthProducer {

    private final RabbitTemplate rabbitTemplate;

    public void createAdmin(CreateModel createModel){
        rabbitTemplate.convertAndSend("exchange-auth","key-auth",createModel);
    }


    public void sendPasswordAfterManagerCreate(MailManagerPassword mailManagerPassword){
        rabbitTemplate.convertAndSend("exchange-mail","key-mail-for-manager",mailManagerPassword);

    }


}
