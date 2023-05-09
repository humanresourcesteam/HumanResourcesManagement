package com.bilgeadam.rabbitmq.consumer;

import com.bilgeadam.rabbitmq.model.WorkerModel;
import com.bilgeadam.service.CompanyService;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkerConsumer {

    private final CompanyService companyService;

    @RabbitListener(queues = "queue-worker-company")
    public String  workerCompanyName(WorkerModel workerModel){
       return companyService.workerCompanyName(workerModel);
    }


}
