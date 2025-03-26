package com.project.Nike_DB.controller;

import com.project.Nike_DB.DTO.OrdineDTO;
import com.project.Nike_DB.model.Carrello;
import com.project.Nike_DB.model.CarrelloItem;
import com.project.Nike_DB.model.Ordine;
import com.project.Nike_DB.model.User;
import com.project.Nike_DB.repository.CarrelloItemRepository;
import com.project.Nike_DB.repository.CarrelloRepository;
import com.project.Nike_DB.repository.OrdineRepository;
import com.project.Nike_DB.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/carrello")
public class CarrelloController {

    private final CarrelloRepository carrelloRepository;

    private final CarrelloItemRepository carrelloItemRepository;

    private final OrdineRepository ordineRepository;

    private final UserRepository userRepository;

    public CarrelloController(CarrelloRepository carrelloRepository, CarrelloItemRepository carrelloItemRepository,
                              OrdineRepository ordineRepository, UserRepository userRepository){
        this.carrelloRepository = carrelloRepository;
        this.carrelloItemRepository = carrelloItemRepository;
        this.ordineRepository = ordineRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/{utenteId}")
    public ResponseEntity<?> getCarrello(@PathVariable Long utenteId) {

        Optional<Carrello> carrelloOpt = carrelloRepository.findByUtenteId(utenteId);

        if(carrelloOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carrello non trovato");
        }

        Carrello carrello = carrelloOpt.get();

        List<CarrelloItem> prodotti = carrelloItemRepository.findByCarrelloId(carrello.getId());

        Map<String, Object> response = new HashMap<>();
        response.put("carrelloId", carrello.getId());
        response.put("prodotti", prodotti);

        return ResponseEntity.ok(response);
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

    @PostMapping("/item/{itemId}/incrementa")
    public ResponseEntity<?> incrementaQuantita(@PathVariable Long itemId) {
        Optional<CarrelloItem> item = carrelloItemRepository.findById(itemId);

        if (item.isPresent()) {
            CarrelloItem prodotto = item.get();
            prodotto.setQuantita(prodotto.getQuantita() + 1);
            prodotto.setPrezzoTotale(prodotto.getPrezzo() * prodotto.getQuantita());
            CarrelloItem aggiornato = carrelloItemRepository.save(prodotto);
            return ResponseEntity.ok(aggiornato);
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Prodotto non trovato");
    }

    @PostMapping("/item/{itemId}/decrementa")
    public ResponseEntity<?> decrementaQuantita(@PathVariable Long itemId) {

        Optional<CarrelloItem> item = carrelloItemRepository.findById(itemId);

        if (item.isPresent()) {

            CarrelloItem prodotto = item.get();

            if (prodotto.getQuantita() <= 1) {

                carrelloItemRepository.delete(prodotto);
                return ResponseEntity.ok().body(Map.of(
                        "eliminato", true,
                        "itemId", prodotto.getId()
                ));
            }
            else {
                prodotto.setQuantita(prodotto.getQuantita() - 1);
                CarrelloItem aggiornato = carrelloItemRepository.save(prodotto);
                return ResponseEntity.ok(Map.of(
                        "eliminato", false,
                        "item", aggiornato
                ));
            }
        }

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Prodotto non trovato");
    }

    @DeleteMapping("/{utenteId}/svuota")
    public ResponseEntity<?> svuotaCarrello(@PathVariable Long utenteId) {

        Optional<Carrello> carrelloOpt = carrelloRepository.findByUtenteId(utenteId);

        if(carrelloOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carrello non trovato!");
        }

        Carrello carrello = carrelloOpt.get();

        List<CarrelloItem> prodotti = carrelloItemRepository.findByCarrelloId(carrello.getId());

        carrelloItemRepository.deleteAll(prodotti);

        Map<String, String> response = new HashMap<>();
        response.put("message", "Carrello svuotato");

        return ResponseEntity.ok(response);

    }

    @PostMapping("/{utenteId}/migra")
    public ResponseEntity<?> migraCarrelloGuest(
            @PathVariable Long utenteId,
            @RequestBody List<Map<String, Object>> prodotti,
            @RequestHeader("Secret-Key") String secretKey) {

        Optional<User> userOpt = userRepository.findById(utenteId);

        if (userOpt.isEmpty() || !userOpt.get().getSecretKey().equals(secretKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Chiave non valida");
        }

        Optional<Carrello> carrelloOpt = carrelloRepository.findByUtenteId(utenteId);
        if (carrelloOpt.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Carrello non trovato");
        }

        Carrello carrello = carrelloOpt.get();

        for (Map<String, Object> item : prodotti) {
            try {
                String nome = (String) item.get("prodotto");
                String taglia = (String) item.get("taglia");
                String colore = (String) item.get("colore");
                int quantita = ((Number) item.get("quantita")).intValue();
                double prezzo = ((Number) item.get("prezzo")).doubleValue();

                carrello.aggiungiProdotto(nome, taglia, colore, prezzo, quantita);

            } catch (Exception e) {
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body("Errore durante la migrazione: dati non validi -> " + item.toString());
            }
        }

        carrelloRepository.save(carrello);
        return ResponseEntity.ok("Prodotti migrati correttamente!");
    }

    @PostMapping("/{utenteId}/checkout")
    public ResponseEntity<?> checkout(@PathVariable Long utenteId,
            @RequestBody List<OrdineDTO> ordiniDTO,
            @RequestHeader("Secret-Key") String secretKey) {

        Optional<User> userOpt = userRepository.findById(utenteId);

        if (userOpt.isEmpty() || !userOpt.get().getSecretKey().equals(secretKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Chiave non valida");
        }

        User user = userOpt.get();

        if (ordiniDTO == null || ordiniDTO.isEmpty()) {
            return ResponseEntity.badRequest().body("Il carrello è vuoto!");
        }

        for (OrdineDTO dto : ordiniDTO) {
            Ordine ordine = new Ordine(
                    user,
                    dto.getProdotto(),
                    dto.getTaglia(),
                    dto.getColore(),
                    dto.getQuantita(),
                    dto.getPrezzoTotale(),
                    dto.getImmagineUrl()
            );

            ordineRepository.save(ordine);
        }


        Optional<Carrello> carrelloOpt = carrelloRepository.findByUtenteId(utenteId);

        carrelloOpt.ifPresent(c -> {
            List<CarrelloItem> prodotti = carrelloItemRepository.findByCarrelloId(c.getId());
            carrelloItemRepository.deleteAll(prodotti);
            c.getProdotti().clear();
            carrelloRepository.save(c);
        });

        return ResponseEntity.ok(Map.of("message", "Ordine completato con successo"));


    }

}
