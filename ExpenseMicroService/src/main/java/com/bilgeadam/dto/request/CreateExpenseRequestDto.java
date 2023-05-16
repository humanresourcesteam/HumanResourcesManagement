package com.bilgeadam.dto.request;

import com.bilgeadam.repository.enums.ECurrency;
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
public class CreateExpenseRequestDto {

    String managerid;

    String workerid;

    ExpenditureType expenditureType;

    BigDecimal amountOfExpenditure;

    ECurrency currency;

    LocalDate replyDate;

    String  file;
}
