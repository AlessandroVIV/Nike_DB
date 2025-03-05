package com.project.Nike_DB.model;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

@Entity
@Table(name = "taglie_disponibili")
public class TaglieDisponibili {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private Long id;

    private String taglia;

    @ManyToOne
    @JoinColumn(name = "prodotto_id", nullable = false)
    @JsonBackReference
    private Prodotto prodotto;

    public TaglieDisponibili(){}

    public TaglieDisponibili(Long id, String taglia, Prodotto prodotto) {
        this.id = id;
        this.taglia = taglia;
        this.prodotto = prodotto;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTaglia() {
        return taglia;
    }

    public void setTaglia(String taglia) {
        this.taglia = taglia;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }

}
