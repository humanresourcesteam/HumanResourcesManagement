package com.bilgeadam.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class DetailResponseDto {

    String firstName;

    String surname;

    LocalDate dateOfEmployment;

    String email;
    String image;

    String role;

}
