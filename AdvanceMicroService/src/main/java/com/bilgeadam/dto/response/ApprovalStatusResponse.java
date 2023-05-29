package com.bilgeadam.dto.response;


import com.bilgeadam.repository.enums.AdvanceRequestType;
import com.bilgeadam.repository.enums.ApprovalStatus;
import com.bilgeadam.repository.enums.ECurrency;
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
public class ApprovalStatusResponse {

    String managerid;

    String workerid;

    String nameOfTheRequester;

    String surnameOfTheRequester;

    LocalDate dateOfRequest;

    String description;

    LocalDate replyDate;

    BigDecimal advanceAmount;

   String currency;

    AdvanceRequestType advanceRequestType;

    ApprovalStatus approvalStatus;
}
