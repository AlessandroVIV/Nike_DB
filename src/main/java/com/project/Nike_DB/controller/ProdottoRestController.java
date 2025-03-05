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

    //    // Chiamo tutti i prodotti
//
//    @GetMapping
//    public ResponseEntity<?> getAllProdotti(){
//        List<Prodotto> prodotto= prodottoService.getAllProdotti();
//        return ResponseEntity.ok().body(prodotto);
//    }
//
//    // Chiamo i prodotti per "Categoria"
//
//    // Categorie chiamabili al momento: "Running", "Sneakers".
//
//    @GetMapping("/{categoria}")
//    public ResponseEntity<?> getProdottiByCat(@PathVariable String categoria){
//        Prodotto prodotto = prodottoService.getProdottoByCat(categoria);
//        return ResponseEntity.ok().body(prodotto);
//    }
//
//    // Chiamo i prodotti a seconda se sono "Best Seller" o no.
//
//    // Ci sono in tutto 5 best sellers, quindi la chiamata sarà: "/bestSeller/{numerodesideratoda1a5}
//
//    @GetMapping("/bestSeller/{bestSeller}")
//    public ResponseEntity<?> getProdottiByBestSeller(@PathVariable Integer bestSeller){
//        Prodotto prodotto = prodottoService.getProdottoByBestSeller(bestSeller);
//        return ResponseEntity.ok().body(prodotto);
//    }
//
//    // Chiami i prodotti a seconda se sono nuovi arrivi o no; il tipo è "boolean"
//    // I prodotti che non hanno la voce "nuovoArrivi" non posso essere visualizzati dato che il parametro richiesto è booleano
//
//    @GetMapping("/nuovoArrivo/{nuovoArrivo}")
//    public ResponseEntity<?> getProdottoByNuovoArrivo(@PathVariable Boolean nuovoArrivo){
//        Prodotto prodotto = prodottoService.getProdottoByNuovoArrivo(nuovoArrivo);
//        return ResponseEntity.ok().body(prodotto);
//    }
//
//    // Chiamo i prodotti per il loro "genere"; al momenti ci sono 3 generi: "Donna", "Uomo" e "Unisex"
//    // ATTENZIONE: SONO TUTTI CON LA PRIMA LETTERA MAIUSCOLA
//
//    @GetMapping("/genere/{genere}")
//    public ResponseEntity<?> getProdottoByGenre(@PathVariable String genere){
//        Prodotto prodotto = prodottoService.getProdottoByGenere(genere);
//        return ResponseEntity.ok().body(prodotto);
//    }

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
    public ResponseEntity<Prodotto> createProdotto(@RequestBody ProdottoDTO prodottoDTO) {
        Prodotto prodottoSalvato = prodottoService.convertitoreDTO(prodottoDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(prodottoSalvato);
    }

}
