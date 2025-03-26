package com.project.Nike_DB.DTO;

public class OrdineDTO {

    private String prodotto;
    private String taglia;
    private String colore;
    private int quantita;
    private double prezzoUnitario;
    private double prezzoTotale;
    private String immagineUrl;

    public OrdineDTO() {
    }

    public OrdineDTO(String prodotto, String taglia, String colore, int quantita, double prezzoUnitario, double prezzoTotale, String immagineUrl) {
        this.prodotto = prodotto;
        this.taglia = taglia;
        this.colore = colore;
        this.quantita = quantita;
        this.prezzoUnitario = prezzoUnitario;
        this.prezzoTotale = prezzoTotale;
        this.immagineUrl = immagineUrl;
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

    public double getPrezzoUnitario() {
        return prezzoUnitario;
    }

    public void setPrezzoUnitario(double prezzoUnitario) {
        this.prezzoUnitario = prezzoUnitario;
    }

    public double getPrezzoTotale() {
        return prezzoTotale;
    }

    public void setPrezzoTotale(double prezzoTotale) {
        this.prezzoTotale = prezzoTotale;
    }

    public String getImmagineUrl() {
        return immagineUrl;
    }

    public void setImmagineUrl(String immagineUrl) {
        this.immagineUrl = immagineUrl;
    }

}
