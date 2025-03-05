package com.project.Nike_DB.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "recensioni")
public class Recensione {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonIgnore
    private long id;

    @Column(name = "titolo_recensione")
    private String titoloRecensione;

    @Column(name = "data_recensione")
    @JsonFormat(pattern = "dd MMM yyyy", locale = "it_IT", shape = JsonFormat.Shape.STRING)
    private LocalDate dataRecensione;

    @Column(name = "recensore")
    private String recensore;

    @Column(name = "recensione")
    private String recensione;

    @ManyToOne
    @JoinColumn(name = "prodotto_id", nullable = false)
    @JsonBackReference
    private Prodotto prodotto;

    public Recensione() {}

    public Recensione(long id, String titoloRecensione, LocalDate dataRecensione, String recensore, String recensione, Prodotto prodotto) {
        this.id = id;
        this.titoloRecensione = titoloRecensione;
        this.dataRecensione = dataRecensione;
        this.recensore = recensore;
        this.recensione = recensione;
        this.prodotto = prodotto;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitoloRecensione() {
        return titoloRecensione;
    }

    public void setTitoloRecensione(String titoloRecensione) {
        this.titoloRecensione = titoloRecensione;
    }

    public LocalDate getDataRecensione() {
        return dataRecensione;
    }

    public void setDataRecensione(LocalDate dataRecensione) {
        this.dataRecensione = dataRecensione;
    }

    public String getRecensore() {
        return recensore;
    }

    public void setRecensore(String recensore) {
        this.recensore = recensore;
    }

    public String getRecensione() {
        return recensione;
    }

    public void setRecensione(String recensione) {
        this.recensione = recensione;
    }

    public Prodotto getProdotto() {
        return prodotto;
    }

    public void setProdotto(Prodotto prodotto) {
        this.prodotto = prodotto;
    }

}
