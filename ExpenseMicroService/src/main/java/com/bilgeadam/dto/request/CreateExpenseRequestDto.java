package com.bilgeadam.dto.request;

import com.bilgeadam.repository.enums.ApprovalStatus;

import com.bilgeadam.repository.enums.ExpenditureType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CreateExpenseRequestDto {

    String workerid;

    String managerid;

    ExpenditureType expenditureType;

    BigDecimal amountOfExpenditure;

    String currency;

    ApprovalStatus approvalStatus;
    List<MultipartFile> file;
    String desc;

    String name;

    String surname;


}
