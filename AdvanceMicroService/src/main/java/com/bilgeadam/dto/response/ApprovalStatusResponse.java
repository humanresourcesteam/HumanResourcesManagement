package com.bilgeadam.dto.response;

import com.bilgeadam.repository.enums.AdvanceAmount;
import com.bilgeadam.repository.enums.AdvanceRequestType;
import com.bilgeadam.repository.enums.ApprovalStatus;
import com.bilgeadam.repository.enums.ECurrency;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

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

    AdvanceAmount advanceAmount;

    ECurrency currency;

    AdvanceRequestType advanceRequestType;

    ApprovalStatus approvalStatus;
}
