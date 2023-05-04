package com.bilgeadam.dto.response;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NewEmployeeResponseDto {

    Long id;


    String email;
}
