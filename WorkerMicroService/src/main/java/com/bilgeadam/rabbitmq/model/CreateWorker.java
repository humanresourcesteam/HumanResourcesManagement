package com.bilgeadam.rabbitmq.model;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CreateWorker implements Serializable {

    String email;

}
