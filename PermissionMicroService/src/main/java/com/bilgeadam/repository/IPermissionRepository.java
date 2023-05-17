package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.Permission;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPermissionRepository extends MongoRepository<Permission, String> {
//    List<Permission> findOptionalByOrderApprovalStatusDesc();
//
//    List<Permission> findOptionalByApprovalStatus();

    List<Permission> findOptionalByWorkerid(String workerid);

    List<Permission> findOptionalByManagerid(String managerid);

}
