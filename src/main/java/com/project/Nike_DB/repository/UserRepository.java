package com.project.Nike_DB.repository;

import com.project.Nike_DB.model.Utente;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Utente, Long> {

    Optional<Utente> findByUsername(String username);

    Optional<Utente> findBySecretKey(String secretKey);

}
