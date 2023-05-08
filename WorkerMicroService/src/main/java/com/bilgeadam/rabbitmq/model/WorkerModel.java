package com.bilgeadam.rabbitmq.model;

import lombok.*;

import java.io.Serializable;
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WorkerModel implements Serializable {

    String id;
}
