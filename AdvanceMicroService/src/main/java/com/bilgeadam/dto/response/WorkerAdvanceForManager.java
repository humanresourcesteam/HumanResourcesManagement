package com.bilgeadam.dto.response;


import com.bilgeadam.repository.enums.AdvanceRequestType;
import com.bilgeadam.repository.enums.ApprovalStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class WorkerAdvanceForManager {

    String id;
    String managerid;

    String workerid;

    String nameOfTheRequester;

    String surnameOfTheRequester;
    ApprovalStatus approvalStatus;
    LocalDate dateOfRequest;

    String description;

    LocalDate replyDate;

    BigDecimal advanceAmount;

    String currency;

    AdvanceRequestType advanceRequestType;

}
