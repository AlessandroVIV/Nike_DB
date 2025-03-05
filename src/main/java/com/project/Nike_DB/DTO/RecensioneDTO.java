package com.project.Nike_DB.DTO;

import com.project.Nike_DB.model.Recensione;

import java.time.LocalDate;

public class RecensioneDTO {

    private String titoloRecensione;
    private String dataRecensione;
    private String recensore;
    private String recensione;

    public RecensioneDTO(){}

    public RecensioneDTO(String titoloRecensione, String dataRecensione, String recensore, String recensione) {
        this.titoloRecensione = titoloRecensione;
        this.dataRecensione = dataRecensione;
        this.recensore = recensore;
        this.recensione = recensione;
    }

    public String getTitoloRecensione() {
        return titoloRecensione;
    }

    public void setTitoloRecensione(String titoloRecensione) {
        this.titoloRecensione = titoloRecensione;
    }

    public String getDataRecensione() {
        return dataRecensione;
    }

    public void setDataRecensione(String dataRecensione) {
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

}

