package com.bilgeadam.dto.request;

import com.bilgeadam.repository.enums.ApprovalStatus;
import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UpdateStatusRequestDto {

    String id;

    ApprovalStatus status;

}
