package com.bilgeadam.dto.response;

import com.bilgeadam.repository.enums.ApprovalStatus;
import com.bilgeadam.repository.enums.ExpenditureType;
import lombok.*;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class WorkerExpense {

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

}
