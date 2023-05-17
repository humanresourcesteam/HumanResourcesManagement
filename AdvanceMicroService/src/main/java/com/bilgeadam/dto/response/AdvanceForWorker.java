package com.bilgeadam.dto.response;

import com.bilgeadam.repository.enums.AdvanceRequestType;
import com.bilgeadam.repository.enums.ApprovalStatus;
import com.bilgeadam.repository.enums.ECurrency;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class AdvanceForWorker {

    String id;
    ApprovalStatus approvalStatus;
    LocalDate dateOfRequest;

    String description;

    LocalDate replyDate;

    BigDecimal advanceAmount;

    ECurrency currency;
    AdvanceRequestType advanceRequestType;
}
