package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.Worker;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IWorkerRepository extends MongoRepository<Worker,String> {
    Optional<Worker> findOptionalByIdentificationNumber(String identificationNumber);

    Optional<Worker> findOptionalByManagerid(String id);
}
