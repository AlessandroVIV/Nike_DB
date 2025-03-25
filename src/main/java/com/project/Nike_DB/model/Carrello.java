package com.project.Nike_DB.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
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

    public void aggiungiProdotto(String nome, String taglia, String colore, double prezzo, int quantita) {

        for(CarrelloItem item : prodotti) {

            if(item.getProdotto().equals(nome) &&
                    item.getTaglia().equals(taglia) &&
                    item.getColore().equals(colore)) {

                item.setQuantita(item.getQuantita() + quantita);
                return;

            }

        }

        CarrelloItem nuovoItem = new CarrelloItem();
        nuovoItem.setProdotto(nome);
        nuovoItem.setTaglia(taglia);
        nuovoItem.setColore(colore);
        nuovoItem.setPrezzo(prezzo);
        nuovoItem.setQuantita(quantita);
        nuovoItem.setCarrello(this);

        prodotti.add(nuovoItem);

    }


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
