package com.project.Nike_DB.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "carrelli")
public class Carrello {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "utente_id", nullable = false, unique = true)
    @JsonBackReference
    private User utente;

    @OneToMany(mappedBy = "carrello", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<CarrelloItem> prodotti;

    public Carrello() {}

    public Carrello(User utente) {
        this.utente = utente;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public User getUtente() {
        return utente;
    }

    public void setUtente(User utente) {
        this.utente = utente;
    }

    public List<CarrelloItem> getProdotti() {
        return prodotti;
    }

    public void setProdotti(List<CarrelloItem> prodotti) {
        this.prodotti = prodotti;
    }

}
