package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.Advance;
import com.bilgeadam.repository.enums.ApprovalStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IAdvanceRepository extends MongoRepository<Advance, String> {

    List<Advance> findByWorkerid(String workerid);
    List<Advance> findByManagerid(String managerid);

    Optional<Advance> findOptionalByWorkeridAndApprovalStatus(String workerid, ApprovalStatus approvalStatus);
}
