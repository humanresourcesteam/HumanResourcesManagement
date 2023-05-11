package com.bilgeadam.rabbitmq.producer;

import com.bilgeadam.rabbitmq.model.CompanyName;
import com.bilgeadam.rabbitmq.model.CreateManager;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ManagerProducer {

    private final RabbitTemplate rabbitTemplate;

    public Long createAuthFromManager(CreateManager createManager) {
        return (Long) rabbitTemplate.convertSendAndReceive("exchange-manager-auth", "exchange-manager-auth", createManager);
    }

    public String companyIdForManager(CompanyName companyName){
        return (String) rabbitTemplate.convertSendAndReceive("exchange-manager-company","exchange-manager-company",companyName);
    }
}
