package com.bilgeadam.rabbitmq.consumer;

import com.bilgeadam.rabbitmq.model.MailManagerPassword;
import com.bilgeadam.service.MailService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailConsumer {

    private final MailService mailService;

    @RabbitListener(queues = "queue-mail-for-maanager")
    public void sendPasswordForManagerAfterCreate(MailManagerPassword mailManagerPassword){
        mailService.sendPassword(mailManagerPassword);
    }
}
