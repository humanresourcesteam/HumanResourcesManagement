package com.bilgeadam.dto.response;

import com.bilgeadam.repository.enums.Activity;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class SummaryWorker {

    String id;
    String name;
    String image;
    String surname;
    String companyPhone;
    String email;
    Activity activity;
}
