package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.Permission;
import com.bilgeadam.repository.enums.ApprovalStatus;
import org.apache.catalina.Manager;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface IPermissionRepository extends MongoRepository<Permission,String > {
    Optional<Permission> findOptionalByManagerid(String managerid);

    List<Permission> findOptionalByOrderApprovalStatusDesc(ApprovalStatus approvalStatus);

    List<Permission> findOptionalByApprovalStatus(ApprovalStatus approvalStatus);

}
