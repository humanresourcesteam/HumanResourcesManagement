package com.bilgeadam.rabbitmq.producer;


import com.bilgeadam.rabbitmq.model.CreateWorker;
import com.bilgeadam.rabbitmq.model.WorkerModel;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkerProducer {

    private final RabbitTemplate rabbitTemplate;

    public String getNameWorkerFromCompany(WorkerModel workerModel) {
        System.out.println("producer");
        return (String) rabbitTemplate.convertSendAndReceive("exchange-worker-company", "key-worker-company", workerModel);

    }
    public Long createAuth(CreateWorker createWorker){
        return (Long) rabbitTemplate.convertSendAndReceive("exchange-worker-auth","key-worker-auth",createWorker);
    }


}
