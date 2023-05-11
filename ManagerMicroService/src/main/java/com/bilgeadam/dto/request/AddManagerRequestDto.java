package com.bilgeadam.dto.request;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AddManagerRequestDto {

    MultipartFile image;
String companyName;
    String firstName;
    String surname;
    String email;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate dateOfEmployment;

    String address;

    String phone;

    String identificationNumber;

    String birthdayPlace;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate birthDate;
}
