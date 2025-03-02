package com.project.Nike_DB.repository;

import com.project.Nike_DB.model.Prodotto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NikeRepository extends JpaRepository<Prodotto, Long > {

    Optional<Prodotto> findById(Long id);

    List<Prodotto> findByGenere(String genere);

    List<Prodotto> findByCategoria(String categoria);

    List<Prodotto> findByNuovoArriviTrue();

    List<Prodotto> findByBestSeller(int bestSeller);

}
