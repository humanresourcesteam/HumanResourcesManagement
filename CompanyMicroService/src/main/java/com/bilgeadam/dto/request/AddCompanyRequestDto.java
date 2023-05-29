package com.bilgeadam.dto.request;

import com.bilgeadam.repository.enums.Status;
import com.bilgeadam.repository.enums.Title;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.multipart.MultipartFile;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class AddCompanyRequestDto {


    String name;

    Title title;

    String centralRegistrySystem;

    String taxNumber;

    String taxOffice;

    MultipartFile image;

    String phone;

    String address;

    String email;

    Integer numberOfWorkers;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate contractEndYear;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate contractStartYear;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate yearOfEstablishment;

    Status status;

}
