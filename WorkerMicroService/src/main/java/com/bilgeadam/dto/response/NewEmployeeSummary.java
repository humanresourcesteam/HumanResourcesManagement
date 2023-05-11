package com.bilgeadam.dto.response;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class NewEmployeeSummary {

    String name;

    String surname;

    String occupation;

    String image;
}
