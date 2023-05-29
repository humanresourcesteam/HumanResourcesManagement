package com.bilgeadam.dto.response;

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
public class WorkerPermissionForWorker {


    String id;
    TypeOfPermit typeOfPermit;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate startDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate endDate;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate dateOfRequest;
    Integer numberOfDays;
    ApprovalStatus approvalStatus;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    LocalDate replyDate;
}
