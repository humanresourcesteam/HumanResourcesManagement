package com.bilgeadam.dto.request;

import com.bilgeadam.repository.enums.Activity;
import lombok.*;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class AddWorkerRequestDto {

    String managerid;

    String companyid;

    String image;

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
}
