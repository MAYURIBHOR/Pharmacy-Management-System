package com.pharmacy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.pharmacy.pharmacy_management.model.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
}
