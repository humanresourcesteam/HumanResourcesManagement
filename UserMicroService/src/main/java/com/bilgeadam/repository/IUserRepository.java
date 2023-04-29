package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserRepository extends MongoRepository<User,String> {
}
