package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.Advance;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IAdvanceRepository extends MongoRepository<Advance,String > {
}
