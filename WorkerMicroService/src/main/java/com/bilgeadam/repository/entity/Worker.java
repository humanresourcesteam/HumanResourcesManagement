package com.bilgeadam.repository.entity;

import com.bilgeadam.repository.enums.Activity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Document
public class Worker extends BaseEntity {

    @Id
    String id;
    Long authid;
    String companyid;
    String managerid;
    String name;
    String secondname;
    String surname;
    String secondSurname;
    LocalDate birthDate;
    String birthPlace;
    String identificationNumber;
    LocalDate dateOfEmployment;
    String terminationDate;
    Activity activity;
    String occupation;
    String email;
    String address;
    String companyPhone;
    String image;
    BigDecimal salary;

}
