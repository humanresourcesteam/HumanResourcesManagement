package com.bilgeadam.rabbitmq.producer;


import com.bilgeadam.rabbitmq.model.WorkerModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkerProducer {

    private final RabbitTemplate rabbitTemplate;

    public String  getNameWorkerFromManager(WorkerModel workerModel) {
        return (String) rabbitTemplate.convertSendAndReceive("exchange-worker-manager", "key-worker-manager", workerModel);
    }
}
