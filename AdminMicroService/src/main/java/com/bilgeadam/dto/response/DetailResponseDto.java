package com.bilgeadam.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DetailResponseDto {

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
