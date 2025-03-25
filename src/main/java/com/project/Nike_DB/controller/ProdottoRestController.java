package com.project.Nike_DB.controller;

import com.project.Nike_DB.DTO.ProdottoDTO;
import com.project.Nike_DB.model.*;
import com.project.Nike_DB.repository.*;
import com.project.Nike_DB.services.ProdottoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.lang.annotation.Repeatable;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/prodotti")
public class ProdottoRestController {

    private final ProdottoService prodottoService;
    private final NikeRepository nikeRepository;

    public ProdottoRestController(ProdottoService prodottoService, NikeRepository nikeRepository){
        this.prodottoService = prodottoService;
        this.nikeRepository = nikeRepository;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getProdottoById(@PathVariable Long id){

        Optional<Prodotto> prodotto = prodottoService.getProdottoById(id);

        if (prodotto.isPresent()){
            return ResponseEntity.ok(prodotto.get());
        }
        else
        {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Prodotto con ID " + id + " non trovato.");
        }

    }

    @GetMapping
    public ResponseEntity<?> getAllProdotti(){
        List<Prodotto> prodotto = prodottoService.getAllProdotti();
        return ResponseEntity.ok().body(prodotto);
    }

    @GetMapping("/genere/{genere}")
    public List<Prodotto> getProdottiByGenere(@PathVariable String genere) {
        return prodottoService.getProdottiByGenere(genere);
    }

    @GetMapping("/categoria/{categoria}")
    public List<Prodotto> getProdottiByCategoria(@PathVariable String categoria) {
        return prodottoService.getProdottiByCategoria(categoria);
    }

    @GetMapping("/bestSeller/{bestSeller}")
    public List<Prodotto> getProdottiByBestSeller(@PathVariable Integer bestSeller) {
        return prodottoService.getProdottiByBestSeller(bestSeller);
    }

    @GetMapping("/nuovoArrivo")
    public List<Prodotto> getNuoviArrivi() {
        return prodottoService.getNuoviArrivi();
    }

    @PostMapping
    public ResponseEntity<List<Prodotto>> createProdotti(@RequestBody List<ProdottoDTO> prodottiDTO) {
        List<Prodotto> prodottiSalvati = prodottiDTO.stream()
                .map(prodottoService::convertitoreDTO)
                .collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.CREATED).body(prodottiSalvati);
    }


}
