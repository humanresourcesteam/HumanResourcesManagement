package com.bilgeadam.rabbitmq.consumer;

import com.bilgeadam.rabbitmq.model.PasswordForgot;
import com.bilgeadam.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MailConsumer {

    private final AuthService authService;

    @RabbitListener(queues = "queue-auth-from-mail")
    public String forgotPassword(PasswordForgot passwordForgot){
        return authService.forgotPassword(passwordForgot);
    }
}
