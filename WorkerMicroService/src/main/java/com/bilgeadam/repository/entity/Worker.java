package com.bilgeadam.repository.entity;

import com.bilgeadam.repository.enums.Activity;
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

    String companyid;

    String managerid;

    String name;

    String secondname;

    String surname;

    String secondSurname;

    LocalDate birthDate;

    String birthPlace;

    String identificationNumber;

    String dateOfEmployment;

    String terminationDate;

    Activity activity;

    String occupation;

    String email;

    String address;

    String companyPhone;

    String image;

}
