package com.bilgeadam.repository.entity;

import com.bilgeadam.repository.enums.AdvanceAmount;
import com.bilgeadam.repository.enums.AdvanceRequestType;
import com.bilgeadam.repository.enums.ECurrency;
import lombok.AllArgsConstructor;
import lombok.Builder;
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
public class Advance extends BaseEntity{
    @Id
    String id;

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
}
