package com.bilgeadam.rabbitmq.producer;

import com.bilgeadam.rabbitmq.model.CreateModel;
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
}
