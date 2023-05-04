package com.bilgeadam.dto.response;

import lombok.*;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SumamryInfoManager {

    String id;
    String image;
    String firstName;
    String surname;
    String email;

    String address;

    String phone;

}
