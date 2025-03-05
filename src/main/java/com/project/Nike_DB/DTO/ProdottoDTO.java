package com.project.Nike_DB.DTO;


import java.util.List;

public class ProdottoDTO {

    private String nome;
    private String categoria;
    private double prezzo;
    private String descrizione;
    private boolean nuovoArrivi;
    private Integer bestSeller;
    private String genere;
    private List<String> taglieDisponibili;
    private List<String> coloriDisponibili;
    private List<String> immagini;
    private List<RecensioneDTO> recensioni;

    public ProdottoDTO(){}

    public ProdottoDTO(String nome, String categoria, double prezzo, String descrizione, boolean nuovoArrivi, Integer bestSeller, String genere, List<String> taglieDisponibili, List<String> coloriDisponibili, List<String> immagini, List<RecensioneDTO> recensioni) {
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
        this.recensioni = recensioni;
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

    public List<String> getTaglieDisponibili() {
        return taglieDisponibili;
    }

    public void setTaglieDisponibili(List<String> taglieDisponibili) {
        this.taglieDisponibili = taglieDisponibili;
    }

    public List<String> getColoriDisponibili() {
        return coloriDisponibili;
    }

    public void setColoriDisponibili(List<String> coloriDisponibili) {
        this.coloriDisponibili = coloriDisponibili;
    }

    public List<String> getImmagini() {
        return immagini;
    }

    public void setImmagini(List<String> immagini) {
        this.immagini = immagini;
    }

    public List<RecensioneDTO> getRecensioni() {
        return recensioni;
    }

    public void setRecensioni(List<RecensioneDTO> recensioni) {
        this.recensioni = recensioni;
    }

}

