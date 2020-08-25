package com.loginsignup.repository;

import java.util.List;
import java.util.Optional;

import com.loginsignup.models.Usermodel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<Usermodel, Long> {
    Optional<Usermodel> findByEmail_id(String email_id);

    Optional<Usermodel> findByUsername(String username);

    Boolean exitsByUsername(String username);

    Boolean exitsByEmail_id(String email_id);

    List<Usermodel> findByIdIn(List<Long> userIds);

}