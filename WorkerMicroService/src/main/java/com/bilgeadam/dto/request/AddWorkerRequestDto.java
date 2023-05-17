package com.bilgeadam.dto.request;

import com.bilgeadam.repository.enums.Activity;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class AddWorkerRequestDto {

    String managerid;

    String companyid;

    MultipartFile image;

    String name;

    String secondname;

    String surname;

    String secondSurname;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate birthDate;

    String birthPlace;

    String identificationNumber;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate dateOfEmployment;

    Activity activity;

    String occupation;

    String email;

    String address;

    String companyPhone;

    BigDecimal salary;
}
