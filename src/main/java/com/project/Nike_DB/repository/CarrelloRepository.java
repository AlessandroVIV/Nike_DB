package com.project.Nike_DB.repository;

import com.project.Nike_DB.model.Carrello;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CarrelloRepository extends JpaRepository<Carrello, Long> {

    Optional<Carrello> findByUtenteId(Long utenteId);


}
