package com.bilgeadam.dto.response;

import com.bilgeadam.repository.enums.ApprovalStatus;

import com.bilgeadam.repository.enums.ExpenditureType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class WorkerExpenseForManager {


    String id;
    String workerid;
    String managerid;
    ExpenditureType expenditureType;
    BigDecimal amountOfExpenditure;
    String currency;
    LocalDate replyDate;
    ApprovalStatus approvalStatus;
    List<String> file;
    LocalDate requestDate;
    String desc;
    String name;
    String surname;
}
