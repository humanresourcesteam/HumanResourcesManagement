package com.bilgeadam.repository.entity;

import com.bilgeadam.repository.enums.ERole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Entity
public class Auth extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;


    String email;

    String password;

    @Enumerated(EnumType.STRING)
    @Builder.Default
    ERole roles = ERole.ADMIN;
}
