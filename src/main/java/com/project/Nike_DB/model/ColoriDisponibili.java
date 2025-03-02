package com.project.Nike_DB.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

@Entity
@Table(name = "colori_disponibili")
public class ColoriDisponibili {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    private String colore;

    @ManyToOne
    @JoinColumn(name = "prodotto_id", nullable = false)
    @JsonIgnore
    private Prodotto prodotto;

    public ColoriDisponibili(){}

    public ColoriDisponibili(Long id, String colore, Prodotto prodotto) {
        this.id = id;
        this.colore = colore;
        this.prodotto = prodotto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColore() {
        return colore;
    }

    public void setColore(String colore) {
        this.colore = colore;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }
}
