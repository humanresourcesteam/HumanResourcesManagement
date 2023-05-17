package com.bilgeadam.dto.response;

import com.bilgeadam.repository.enums.ApprovalStatus;
import com.bilgeadam.repository.enums.TypeOfPermit;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class WorkerPermissionForManager {

    String id;

    String workerid;
    TypeOfPermit typeOfPermit;

    LocalDate startDate;

    LocalDate endDate;

    LocalDate dateOfRequest;

    Integer numberOfDays;

    ApprovalStatus approvalStatus;

    LocalDate replyDate;

    String name;

    String surname;
}
