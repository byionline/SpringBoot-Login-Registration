package com.loginsignup.repository;
// import java.util.List;
import java.util.Optional;
import com.loginsignup.models.Usermodel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UserRepository extends JpaRepository<Usermodel, Long> {
    // Optional<Usermodel> findByEmailId(String emailId);
    Optional<Usermodel> findByUsername(String username);
    Boolean exitsByUsername(String username);
    // Boolean exitsByEmailId(String emailId);
    // List<Usermodel> findByIdId(List<Long> userIds);
}