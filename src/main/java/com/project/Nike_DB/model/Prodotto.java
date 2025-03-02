package com.project.Nike_DB.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "prodotti")
public class Prodotto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String categoria;
    private double prezzo;
    private String descrizione;
    private boolean nuovoArrivi;
    private Integer bestSeller;
    private String genere;

    @OneToMany(mappedBy = "prodotto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<TaglieDisponibili> taglieDisponibili;

    @OneToMany(mappedBy = "prodotto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ColoriDisponibili> coloriDisponibili;

    @OneToMany(mappedBy = "prodotto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Immagini> immagini;

    @OneToMany(mappedBy = "prodotto", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Recensione> recensioni;

    public Prodotto(){}

    public Prodotto(Long id, String nome, String categoria, double prezzo, String descrizione, boolean nuovoArrivi, Integer bestSeller, String genere, List<TaglieDisponibili> taglieDisponibili, List<ColoriDisponibili> coloriDisponibili, List<Immagini> immagini) {
        this.id = id;
        this.nome = nome;
        this.categoria = categoria;
        this.prezzo = prezzo;
        this.descrizione = descrizione;
        this.nuovoArrivi = nuovoArrivi;
        this.bestSeller = bestSeller;
        this.genere = genere;
        this.taglieDisponibili = taglieDisponibili;
        this.coloriDisponibili = coloriDisponibili;
        this.immagini = immagini;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        this.prezzo = prezzo;
    }

    public String getDescrizione() {
        return descrizione;
    }

    public void setDescrizione(String descrizione) {
        this.descrizione = descrizione;
    }

    public boolean isNuovoArrivi() {
        return nuovoArrivi;
    }

    public void setNuovoArrivi(boolean nuovoArrivi) {
        this.nuovoArrivi = nuovoArrivi;
    }

    public Integer getBestSeller() {
        return bestSeller;
    }

    public void setBestSeller(Integer bestSeller) {
        this.bestSeller = bestSeller;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }

    public List<TaglieDisponibili> getTaglieDisponibili() {
        return taglieDisponibili;
    }

    public void setTaglieDisponibili(List<TaglieDisponibili> taglieDisponibili) {
        this.taglieDisponibili = taglieDisponibili;
    }

    public List<ColoriDisponibili> getColoriDisponibili() {
        return coloriDisponibili;
    }

    public void setColoriDisponibili(List<ColoriDisponibili> coloriDisponibili) {
        this.coloriDisponibili = coloriDisponibili;
    }

    public List<Immagini> getImmagini() {
        return immagini;
    }

    public void setImmagini(List<Immagini> immagini) {
        this.immagini = immagini;
    }

    public List<Recensione> getRecensioni() {
        return recensioni;
    }

    public void setRecensioni(List<Recensione> recensione) {
        this.recensioni = recensioni;
    }


}


