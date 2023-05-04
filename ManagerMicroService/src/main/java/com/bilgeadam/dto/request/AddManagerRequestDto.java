package com.bilgeadam.dto.request;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddManagerRequestDto {

    String image;

    String firstName;
    String surname;
    String email;
    LocalDate dateOfEmployment;

    String address;

    String phone;

    String identificationNumber;

    String birthdayPlace;

    LocalDate birthDate;
}
