package com.project.Nike_DB.controller;

import com.project.Nike_DB.model.Carrello;
import com.project.Nike_DB.model.CarrelloItem;
import com.project.Nike_DB.repository.CarrelloItemRepository;
import com.project.Nike_DB.repository.CarrelloRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/carrello")
public class CarrelloController {

    private final CarrelloRepository carrelloRepository;

    private final CarrelloItemRepository carrelloItemRepository;

    public CarrelloController(CarrelloRepository carrelloRepository, CarrelloItemRepository carrelloItemRepository){
        this.carrelloRepository = carrelloRepository;
        this.carrelloItemRepository = carrelloItemRepository;
    }

    @GetMapping("/{utenteId}")
    public ResponseEntity<?> getCarrello(@PathVariable Long utenteId){

        Optional<Carrello> carrello = carrelloRepository.findByUtenteId(utenteId);

        if(carrello.isPresent()){
            List<CarrelloItem> prodotti = carrelloItemRepository.findByCarrelloId(carrello.get().getId());
            return ResponseEntity.ok(prodotti);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carrello non trovato");
        }


    }

    @PostMapping("/{carrelloId}/aggiungi")
    public ResponseEntity<?> aggiungiProdotto(@PathVariable Long carrelloId, @RequestBody CarrelloItem nuovoProdotto){

        Optional<Carrello> carrello = carrelloRepository.findById(carrelloId);

        if(carrello.isPresent()){

            nuovoProdotto.setCarrello(carrello.get());

            carrelloItemRepository.save(nuovoProdotto);

            return ResponseEntity.ok("Prodotto aggiunto al carrello con successo!");
        }
        else {

            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carrello non trovato");

        }

    }






}
