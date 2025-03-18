package com.project.Nike_DB.repository;

import com.project.Nike_DB.model.Ordine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrdineRepository extends JpaRepository<Ordine, Long> {

    List<Ordine> findByUtenteId(Long utenteId);

}
