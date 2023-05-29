package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.Expense;
import com.bilgeadam.repository.enums.ApprovalStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IExpenseRepository extends MongoRepository<Expense,String> {

    List<Expense> findByWorkerid(String workerid);
    List<Expense> findByManagerid(String managerid);
}
