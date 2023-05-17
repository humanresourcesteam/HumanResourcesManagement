package com.bilgeadam.repository.entity;

import com.bilgeadam.repository.enums.ApprovalStatus;
import com.bilgeadam.repository.enums.TypeOfPermit;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Document
public class Permission extends BaseEntity {

    @Id
    String id;

    String managerid;

    String workerid;

    TypeOfPermit typeOfPermit;

    LocalDate startDate;

    LocalDate endDate;

    LocalDate dateOfRequest;

    Integer numberOfDays;

    ApprovalStatus approvalStatus;

    LocalDate replyDate;

    String name;

    String surname;

}
