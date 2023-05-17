package com.bilgeadam.dto.request;


import com.bilgeadam.repository.enums.AdvanceRequestType;

import com.bilgeadam.repository.enums.ECurrency;
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
public class CreateAdvanceRequestDto {

    String managerid;
    String workerid;
    BigDecimal advanceAmount;
    String nameOfTheRequester;
    String surnameOfTheRequester;
    String description;
    ECurrency currency;
    AdvanceRequestType advanceRequestType;

}
