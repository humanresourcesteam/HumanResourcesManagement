package com.bilgeadam.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Document
public class Admin extends BaseEntity{


    // FOTOGRAF EKLEME İŞLEMİ BİRLİKTE BAKILACAK...

    @Id
    String id;

    Long authid;

    String firstName;

    String secondName;

    String surname;

    String secondSurname;

    LocalDate birthdate;

    LocalDate dateOfEmployment;

    String placeOfBirth;

    String identificationNumber;

    String email;

    String address;

    String phone;


}