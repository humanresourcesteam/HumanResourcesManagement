package com.bilgeadam.rabbitmq.consumer;

import com.bilgeadam.rabbitmq.model.WorkerModel;
import com.bilgeadam.service.CompanyService;
import com.bilgeadam.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkerConsumer {

    private final CompanyService companyService;

    @RabbitListener(queues = "queue-worker-manager")
    public void workerCompanyName(WorkerModel workerModel){
        companyService.workerCompanyName(workerModel);
    }


}
