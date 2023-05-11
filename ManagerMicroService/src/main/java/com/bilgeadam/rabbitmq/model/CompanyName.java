package com.bilgeadam.rabbitmq.model;

import lombok.*;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class CompanyName implements Serializable {

    String companyName;
}
