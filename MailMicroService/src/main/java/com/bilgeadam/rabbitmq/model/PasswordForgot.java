package com.bilgeadam.rabbitmq.model;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PasswordForgot implements Serializable {

    String email;
    Long authid;
}
