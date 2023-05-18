package com.bilgeadam.dto.response;

import com.bilgeadam.repository.enums.ApprovalStatus;

import com.bilgeadam.repository.enums.ExpenditureType;
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

    String workerid;

    ExpenditureType expenditureType;

    BigDecimal amountOfExpenditure;

    String currency;

    LocalDate replyDate;

    ApprovalStatus approvalStatus;
    String  file;
}
