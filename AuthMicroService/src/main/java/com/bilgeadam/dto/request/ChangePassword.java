package com.bilgeadam.dto.request;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ChangePassword {

  String  current;
    String   newpass;
    String   repass;
    String  authid;
}
