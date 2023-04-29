package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.Admin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface IAdminRepository extends MongoRepository<Admin,String> {

    Optional<Admin> findOptionalByAuthid(Long authid);
}
