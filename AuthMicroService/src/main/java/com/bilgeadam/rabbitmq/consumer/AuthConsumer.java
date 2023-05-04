package com.bilgeadam.rabbitmq.consumer;

import com.bilgeadam.rabbitmq.model.CreateManager;
import com.bilgeadam.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthConsumer {

    private final AuthService authService;


    @RabbitListener(queues = "queue-auth-manager-add")
    public Long createManager(CreateManager createManager){
        return authService.createManager(createManager);
    }

}
