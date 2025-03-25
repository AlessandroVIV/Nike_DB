package com.project.Nike_DB.model;

import jakarta.persistence.*;
import java.math.BigDecimal;
import java.math.RoundingMode;
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

    private BigDecimal prezzo;

    @Column(name = "prezzo_totale")
    private BigDecimal prezzoTotale;

    private LocalDate dataOrdine;

    public Ordine() {}

    public Ordine(User utente, String prodotto, String taglia, String colore, int quantita, double prezzoTotale) {

        this.utente = utente;
        this.prodotto = prodotto;
        this.taglia = taglia;
        this.colore = colore;
        this.quantita = quantita;

        BigDecimal totale = BigDecimal.valueOf(prezzoTotale);
        BigDecimal qty = BigDecimal.valueOf(quantita);

        this.prezzo = totale.divide(qty, 2, RoundingMode.HALF_UP);

        this.prezzoTotale = totale.setScale(2, RoundingMode.HALF_UP);

        this.dataOrdine = LocalDate.now();

    }

    public Long getId() {
        return id;
    }

    public User getUtente() {
        return utente;
    }

    public void setUtente(User utente) {
        this.utente = utente;
    }

    public String getProdotto() {
        return prodotto;
    }

    public void setProdotto(String prodotto) {
        this.prodotto = prodotto;
    }

    public String getTaglia() {
        return taglia;
    }

    public void setTaglia(String taglia) {
        this.taglia = taglia;
    }

    public String getColore() {
        return colore;
    }

    public void setColore(String colore) {
        this.colore = colore;
    }

    public int getQuantita() {
        return quantita;
    }

    public void setQuantita(int quantita) {
        this.quantita = quantita;
    }

    public BigDecimal getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(BigDecimal prezzo) {
        this.prezzo = prezzo;
    }

    public BigDecimal getPrezzoTotale() {
        return prezzoTotale;
    }

    public void setPrezzoTotale(BigDecimal prezzoTotale) {
        this.prezzoTotale = prezzoTotale;
    }

    public LocalDate getDataOrdine() {
        return dataOrdine;
    }

    public void setDataOrdine(LocalDate dataOrdine) {
        this.dataOrdine = dataOrdine;
    }
}
