package com.bilgeadam.dto.request;

import lombok.*;
import org.springframework.web.multipart.MultipartFile;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
@ToString
public class UpdateWorkerRequestDto {

    String token;
    String name;

    String secondname;

    String surname;

    String secondSurname;

    String email;

    String address;

    String companyPhone;

    MultipartFile image;
}
