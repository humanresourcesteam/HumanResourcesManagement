package com.bilgeadam.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class UpdateAdminInfoRequestDto {

 private   String token;
    private    String firstName;
    private    String surname;
  //  LocalDate dateOfEmployment;
  private    String email;

    private    MultipartFile image;
}
