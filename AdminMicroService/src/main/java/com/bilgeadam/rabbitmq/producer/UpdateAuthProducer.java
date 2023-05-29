package com.bilgeadam.rabbitmq.producer;

import com.bilgeadam.rabbitmq.model.UpdateAuthModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateAuthProducer {

    private final RabbitTemplate rabbitTemplate;

    public boolean updateAuth(UpdateAuthModel updateAuthModel) {
        return (boolean) rabbitTemplate.convertSendAndReceive("exchange-update-auth", "key-update-auth", updateAuthModel);

    }
}
