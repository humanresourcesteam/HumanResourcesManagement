package com.bilgeadam.dto.response;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GetAllInfoManager {

    String companyid;
    String id;
    String image;
    Long authid;
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
