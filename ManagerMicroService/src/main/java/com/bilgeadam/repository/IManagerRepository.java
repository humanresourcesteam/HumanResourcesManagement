package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.Manager;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IManagerRepository extends MongoRepository<Manager,String> {
}
