package com.project.Nike_DB.repository;

import com.project.Nike_DB.model.Recensione;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecensioniRepository extends JpaRepository<Recensione, Long> {
}
