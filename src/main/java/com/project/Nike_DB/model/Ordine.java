package com.project.Nike_DB.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "ordini")
public class Ordine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "utente_id", nullable = false)
    private User utente;

    private String prodotto;
    private String taglia;
    private String colore;
    private int quantita;
    private double prezzo;
    private LocalDate dataOrdine;

    public Ordine() {}

    public Ordine(User utente, String prodotto, String taglia, String colore, int quantita, double prezzo) {
        this.utente = utente;
        this.prodotto = prodotto;
        this.taglia = taglia;
        this.colore = colore;
        this.quantita = quantita;
        this.prezzo = prezzo;
        this.dataOrdine = LocalDate.now();
    }

    public Long getId() {
        return id;
    }

    public User getUtente() {
        return utente;
    }

    public String getProdotto() {
        return prodotto;
    }

    public String getTaglia() {
        return taglia;
    }

    public String getColore() {
        return colore;
    }

    public int getQuantita() {
        return quantita;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public LocalDate getDataOrdine() {
        return dataOrdine;
    }

}
