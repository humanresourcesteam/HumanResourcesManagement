package com.bilgeadam.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.validation.constraints.Email;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class RegisterRequestDto {

    @Email
    String email;

    String password;

    String repassword;
}
