package com.bilgeadam.repository.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import nonapi.io.github.classgraph.json.Id;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;


@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Document
public class Admin extends BaseEntity {


    @Id
    String id;
    Long authid;
    String firstName;
    String surname;
    LocalDate dateOfEmployment;
    String email;

    String image;

    String role = "ADMIN";

    ObjectId avatar;
}
