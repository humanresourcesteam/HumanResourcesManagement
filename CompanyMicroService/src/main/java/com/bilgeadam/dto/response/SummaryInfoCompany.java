package com.bilgeadam.dto.response;

import com.bilgeadam.repository.enums.Title;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class SummaryInfoCompany {

    String id;

    String name;
    Title title;
    String email;
    String address;
    String phone;


}
