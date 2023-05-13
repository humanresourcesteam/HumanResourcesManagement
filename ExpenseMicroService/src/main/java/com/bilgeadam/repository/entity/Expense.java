package com.bilgeadam.repository.entity;

import com.bilgeadam.repository.enums.ECurrency;
import com.bilgeadam.repository.enums.ExpenditureType;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;


import java.math.BigDecimal;
import java.time.LocalDate;


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

    ECurrency currency;

    LocalDate replyDate;

    String  file;

}
