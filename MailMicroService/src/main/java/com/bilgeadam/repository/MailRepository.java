package com.bilgeadam.repository;

import com.bilgeadam.repository.entity.Mail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MailRepository extends JpaRepository<Mail,Long> {

    Optional<Mail> findOptionalByEmail(String email);
}
