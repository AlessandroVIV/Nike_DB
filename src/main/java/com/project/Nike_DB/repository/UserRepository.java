package com.project.Nike_DB.repository;

import com.project.Nike_DB.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findBySecretKey(String secretKey);

}
