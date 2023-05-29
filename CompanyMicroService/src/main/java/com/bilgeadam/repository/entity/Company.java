package com.bilgeadam.repository.entity;

import com.bilgeadam.repository.enums.Status;
import com.bilgeadam.repository.enums.Title;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import nonapi.io.github.classgraph.json.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@SuperBuilder
@Document
public class Company extends BaseEntity {

    @Id
    String id;
    Long authid;
    String name;
    Title title;
    String centralRegistrySystem;
    String taxNumber;
    String taxOffice;
    String image;
    String phone;
    String address;
    String email;
    Integer numberOfWorkers;
    LocalDate yearOfEstablishment;
    LocalDate contractStartYear;
    LocalDate contractEndYear;
    Status status;

}
