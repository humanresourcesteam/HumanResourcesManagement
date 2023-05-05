package com.bilgeadam.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@Document
public class Worker extends BaseEntity{

    @Id
    String id;

//    String firstname;
//
//    String secondname;
//
//    String surname;
//
//    String secondSurname;
//
//    LocalDate birthDate;
//
//    String birthPlace;
//
//    String identificationNumber;
//
//    String dateOfEmployment;
//
//    String terminationDate;
//


}
