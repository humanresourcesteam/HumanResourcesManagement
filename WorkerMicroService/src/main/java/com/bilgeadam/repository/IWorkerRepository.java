package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.Worker;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IWorkerRepository extends MongoRepository<Worker,String> {
}
