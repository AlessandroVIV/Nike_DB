package com.project.Nike_DB.controller;

import com.project.Nike_DB.model.Ordine;
import com.project.Nike_DB.repository.OrdineRepository;
import com.project.Nike_DB.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ordini")
public class OrdineController {

    private final OrdineRepository ordineRepository;
    private final UserRepository userRepository;

    public OrdineController(OrdineRepository ordineRepository, UserRepository userRepository) {
        this.ordineRepository = ordineRepository;
        this.userRepository = userRepository;
    }

    @GetMapping("/{utenteId}")
    public ResponseEntity<?> getOrdini(@PathVariable Long utenteId, @RequestHeader("Secret-Key") String secretKey) {

        var userOpt = userRepository.findById(utenteId);

        if (userOpt.isEmpty() || !userOpt.get().getSecretKey().equals(secretKey)) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Chiave non valida");
        }

        List<Ordine> ordini = ordineRepository.findByUtenteId(utenteId);

        if (ordini.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Nessun ordine trovato.");
        }

        return ResponseEntity.ok(ordini);

    }


}
