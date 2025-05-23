package com.project.Nike_DB.controller;

import com.project.Nike_DB.model.Carrello;
import com.project.Nike_DB.model.User;
import com.project.Nike_DB.repository.CarrelloRepository;
import com.project.Nike_DB.repository.UserRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
public class UserController {

    private UserRepository userRepository;

    private CarrelloRepository carrelloRepository;

    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository userRepository, CarrelloRepository carrelloRepository, PasswordEncoder passwordEncoder){
        this.userRepository = userRepository;
        this.carrelloRepository = carrelloRepository;
        this.passwordEncoder = passwordEncoder;
    }

    // Enpoint per registrazione nuovo utente
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody User user){

        if(userRepository.findByUsername(user.getUsername()).isPresent()){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Username giù in uso!");
        }

        user.setSecretKey(UUID.randomUUID().toString());

        String hashedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        user.setSecretKey(UUID.randomUUID().toString());

        userRepository.save(user);


        Carrello carrello = new Carrello(user);
        carrelloRepository.save(carrello);

        return ResponseEntity.ok("Utente registrato con successo + carrello creato");

    }

    // Enpoint per login utente
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody User loginUser) {

        Optional<User> userSuDb = userRepository.findByUsername(loginUser.getUsername());

        if (userSuDb.isPresent() && passwordEncoder.matches(loginUser.getPassword(), userSuDb.get().getPassword())) {

            User user = userSuDb.get();

            carrelloRepository.findByUtenteId(user.getId()).orElseGet(() -> carrelloRepository.save(new Carrello(user)));

            Map<String, Object> response = new HashMap<>();
            response.put("secretKey", user.getSecretKey());
            response.put("id", user.getId());
            response.put("email", user.getEmail());
            response.put("telefono", user.getTelefono());
            response.put("indirizzo", user.getIndirizzo());

            return ResponseEntity.ok(response);
        }

        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body(Collections.singletonMap("error", "Credenziali errate!"));
    }

    // Enpoint protetto dal login (non che serva al 100%, ma è un metodo in più per verificare l'utente)
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
