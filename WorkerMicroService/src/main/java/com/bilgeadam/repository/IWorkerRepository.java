package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.Worker;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public interface IWorkerRepository extends MongoRepository<Worker,String> {
    Optional<Worker> findOptionalByIdentificationNumber(String identificationNumber);

    Optional<Worker> findOptionalByCompanyid(String id);

    List<Worker> findByCompanyid(String companyid);

    List<Worker> findTop7ByOrderByCreatedateDesc();


    Optional<Worker> findOptionalById(String id);


    Optional<Worker> findOptionalByAuthid(Long authid);
}
