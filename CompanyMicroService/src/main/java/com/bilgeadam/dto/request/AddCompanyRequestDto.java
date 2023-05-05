package com.bilgeadam.dto.request;

import com.bilgeadam.repository.enums.Status;
import com.bilgeadam.repository.enums.Title;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AddCompanyRequestDto {


    String token;

    String name;

    Title title;

    String centralRegistrySystem;

    String taxNumber;

    String taxOffice;

    String image;

    String phone;

    String address;

    String email;

    Integer numberOfWorkers;

    LocalDate yearOfEstablishment;

    LocalDate contractStartYear;

    LocalDate contractEndYear;



    Status status;

}
