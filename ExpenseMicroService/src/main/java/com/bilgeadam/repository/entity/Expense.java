package com.bilgeadam.repository.entity;

import com.bilgeadam.repository.enums.ApprovalStatus;
import com.bilgeadam.repository.enums.ExpenditureType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Document
public class Expense extends BaseEntity{

    @Id
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
