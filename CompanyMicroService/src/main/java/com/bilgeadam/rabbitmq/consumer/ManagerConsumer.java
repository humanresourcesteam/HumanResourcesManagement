package com.bilgeadam.rabbitmq.consumer;

import com.bilgeadam.rabbitmq.model.CompanyName;
import com.bilgeadam.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerConsumer {

    private final CompanyService companyService;

    @RabbitListener(queues = "queue-manager-company")
    public String companyIdForManager(CompanyName companyName){
        return companyService.companyIdForManager(companyName);

    }
}
