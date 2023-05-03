package com.bilgeadam.rabbitmq.consumer;

import com.bilgeadam.rabbitmq.model.UpdateAuthModel;
import com.bilgeadam.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UpdateAuthConsumer {

    private final AuthService authService;

    @RabbitListener(queues = "queue-update-auth")
    public boolean updateAuth(UpdateAuthModel updateAuthModel){
      return   authService.updateAuth(updateAuthModel);
    }
}
