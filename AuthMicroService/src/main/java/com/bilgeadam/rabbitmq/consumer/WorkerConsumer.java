package com.bilgeadam.rabbitmq.consumer;

import com.bilgeadam.rabbitmq.model.CreateWorker;
import com.bilgeadam.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WorkerConsumer  {

    private final AuthService authService;

    @RabbitListener(queues = "queue-worker-auth")
    public Long createWorker(CreateWorker createWorker){
     return    authService.createWorker(createWorker);
    }
}
