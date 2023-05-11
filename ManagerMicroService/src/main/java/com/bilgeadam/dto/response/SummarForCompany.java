package com.bilgeadam.dto.response;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SummarForCompany {

    String firstName;
    String surname;
    String email;
    String image;
    String phone;
}
