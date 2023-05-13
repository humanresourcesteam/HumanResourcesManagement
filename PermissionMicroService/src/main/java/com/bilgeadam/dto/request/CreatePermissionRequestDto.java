package com.bilgeadam.dto.request;

import com.bilgeadam.repository.enums.ApprovalStatus;
import com.bilgeadam.repository.enums.TypeOfPermit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreatePermissionRequestDto {

    String managerid;

    String workerid;

    TypeOfPermit typeOfPermit;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate startDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate endDate;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate dateOfRequest;

    Integer numberOfDays;



}
