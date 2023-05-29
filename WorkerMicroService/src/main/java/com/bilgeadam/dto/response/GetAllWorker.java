package com.bilgeadam.dto.response;

import com.bilgeadam.repository.enums.Activity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GetAllWorker {

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

    BigDecimal salary;

}
