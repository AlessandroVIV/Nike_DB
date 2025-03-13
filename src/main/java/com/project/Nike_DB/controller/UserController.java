package com.project.Nike_DB.controller;

import com.project.Nike_DB.model.Utente;
import com.project.Nike_DB.repository.UserRepository;
import org.apache.catalina.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

@RestController
public class UserController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository){
        this.userRepository = userRepository;
    }

    // Enpoint per registrazione nuovo utente
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody Utente utente){

        if(userRepository.findByUsername(utente.getUsername()).isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username giù in uso!");
        }

        utente.setSecretKey(UUID.randomUUID().toString());

        userRepository.save(utente);

        return ResponseEntity.ok("Utente registrato con successo!");

    }

    // Enpoint per login utente
    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody Utente loginUser){

        Optional<Utente> userSuDb = userRepository.findByUsername(loginUser.getUsername());

        if (userSuDb.isPresent() && userSuDb.get().getPassword().equals(loginUser.getPassword())) {
            return ResponseEntity.ok(Collections.singletonMap("secretKey", userSuDb.get().getSecretKey()));
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("error", "Credenziali errate!"));

    }
    
    // Enpoint protetto dal login
    @GetMapping("/protected")
    public ResponseEntity<String> protectedEndpoint(@RequestHeader (value = "Secret-Key", required = false) String secretKey){

        // Se la secretKey è stata inserita ed esiste un utente che la possiede, mostramelo
        if(secretKey != null && userRepository.findBySecretKey(secretKey).isPresent()){
            return ResponseEntity.ok(
                    userRepository.findBySecretKey(secretKey).get().getUsername()
            );
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    }

}
