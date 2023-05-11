package com.bilgeadam.repository.entity;

import lombok.*;
import lombok.experimental.SuperBuilder;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Document(value = "manager")
public class Manager extends BaseEntity{

    @Id
    String id;
    String image;
    Long authid;
    String companyid;
    String firstName;
    String surname;
    String email;
    LocalDate dateOfEmployment;

    String role = "MANAGER";

    String address;

    String phone;

    String identificationNumber;

    String birthdayPlace;

    LocalDate birthDate;
}
