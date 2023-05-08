package com.bilgeadam.dto.response;

import com.bilgeadam.repository.enums.Status;
import com.bilgeadam.repository.enums.Title;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class GetAllInfoCompany {


    String id;

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

    Integer allContractDay;
    Integer remainingDays;

}
