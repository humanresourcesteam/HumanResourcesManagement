package com.bilgeadam.rabbitmq.consumer;

import com.bilgeadam.rabbitmq.model.CreateModel;
import com.bilgeadam.service.AdminService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthConsumer {

    private final AdminService adminService;

    @RabbitListener(queues = "queue-auth")
    public void createAdmin(CreateModel createModel){
        adminService.saveAdmin(createModel);
    }
}
