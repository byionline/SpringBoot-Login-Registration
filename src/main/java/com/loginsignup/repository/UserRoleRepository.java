package com.loginsignup.repository;

// import java.util.Optional;

// import com.loginsignup.models.RoleType;
import com.loginsignup.models.Roles;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRoleRepository extends JpaRepository<Roles, Long> {
    // Optional<Roles> findByName(RoleType name);
    
}