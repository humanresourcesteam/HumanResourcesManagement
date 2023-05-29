package com.bilgeadam.dto.response;

import com.bilgeadam.repository.enums.ApprovalStatus;
import com.bilgeadam.repository.enums.TypeOfPermit;
import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ApprovalStatusResponse {

    String workerid;
    TypeOfPermit typeOfPermit;
    LocalDate startDate;
    LocalDate endDate;
    LocalDate dateOfRequest;
    Integer numberOfDays;
    ApprovalStatus approvalStatus;
}
