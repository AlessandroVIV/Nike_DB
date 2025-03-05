package com.project.Nike_DB.services;

import com.project.Nike_DB.DTO.ProdottoDTO;
import com.project.Nike_DB.model.*;
import com.project.Nike_DB.repository.NikeRepository;
import com.project.Nike_DB.repository.RecensioniRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdottoService {

    private final NikeRepository nikeRepository;
    private final RecensioniRepository recensioniRepository;

    public ProdottoService(NikeRepository nikeRepository, RecensioniRepository recensioniRepository) {
        this.nikeRepository = nikeRepository;
        this.recensioniRepository = recensioniRepository;
    }

    public List<Prodotto> getAllProdotti() {
        return nikeRepository.findAll();
    }

    public Optional<Prodotto> getProdottoById(Long id) {
        return nikeRepository.findById(id);
    }

    public List<Prodotto> getProdottiByGenere(String genere) {
        return nikeRepository.findByGenere(genere);
    }

    public List<Prodotto> getProdottiByCategoria(String categoria) {
        return nikeRepository.findByCategoria(categoria);
    }

    public List<Prodotto> getNuoviArrivi() {
        return nikeRepository.findByNuovoArriviTrue();
    }

    public List<Prodotto> getProdottiByBestSeller(int bestSeller) {
        return nikeRepository.findByBestSeller(bestSeller);
    }

    public Prodotto convertitoreDTO(ProdottoDTO prodottoDTO){

        Prodotto prodotto = new Prodotto();

        prodotto.setNome(prodottoDTO.getNome());
        prodotto.setCategoria(prodottoDTO.getCategoria());
        prodotto.setPrezzo(prodottoDTO.getPrezzo());
        prodotto.setDescrizione(prodottoDTO.getDescrizione());
        prodotto.setNuovoArrivi(prodottoDTO.isNuovoArrivi());
        prodotto.setBestSeller(prodottoDTO.getBestSeller());
        prodotto.setGenere(prodottoDTO.getGenere());

        List<TaglieDisponibili> taglie = prodottoDTO.getTaglieDisponibili().stream()
                .map(t -> new TaglieDisponibili(null, t, prodotto))
                .collect(Collectors.toList());

        List<ColoriDisponibili> colori = prodottoDTO.getColoriDisponibili().stream()
                .map(c -> new ColoriDisponibili(null, c, prodotto))
                .collect(Collectors.toList());

        List<Immagini> immagini = prodottoDTO.getImmagini().stream()
                .map(img -> new Immagini(null, img, prodotto))
                .collect(Collectors.toList());

        List<Recensione> recensioni =
                prodottoDTO.getRecensioni()
                        .stream()
                .map(r -> new Recensione
                        (0, r.getTitoloRecensione(), LocalDate.parse(r.getDataRecensione()),
                                r.getRecensore(), r.getRecensione(), prodotto))
                .collect(Collectors.toList());

        prodotto.setTaglieDisponibili(taglie);
        prodotto.setColoriDisponibili(colori);
        prodotto.setImmagini(immagini);
        prodotto.getRecensioni().addAll(recensioni);

        Prodotto prodottoSalvato = nikeRepository.save(prodotto);

        return nikeRepository.save(prodotto);

    }

    public Prodotto saveProdotto(Prodotto prodotto) {

        Prodotto prodottoSalvato = nikeRepository.save(prodotto);

        if (prodotto.getRecensioni() != null && !prodotto.getRecensioni().isEmpty()) {

            for (Recensione recensione : prodotto.getRecensioni()) {
                System.out.println("Salvando recensione: " + recensione.getTitoloRecensione());
                recensione.setProdotto(prodottoSalvato);
                recensioniRepository.save(recensione);
            }

        }

        return prodottoSalvato;

    }

}

//    // GET PRODOTTI
//    public List<Prodotto> getAllProdotti(){
//        return nikeRepository.findAll();
//    }
//
//    // GET PRODOTTI PER CATEGORIA
//    public Prodotto getProdottoByCat(String categoria) {
//
//        for (Prodotto p : getAllProdotti()) {
//            if (p.getCategoria().equals(categoria)) {
//                return p;
//            }
//        }
//
//        throw new RuntimeException("Categoria non trovata: " + categoria);
//    }
//
//    // GET PRODOTTI PER BESTSELLER
//    public Prodotto getProdottoByBestSeller(Integer bestSeller){
//
//        for(Prodotto p : getAllProdotti()){
//            if(p.getBestSeller().equals(bestSeller)){
//                return p;
//            }
//        }
//
//        throw new RuntimeException("bestSeller non trovato: " + bestSeller);
//
//    }
//
//    //GET PRODOTTI PER NUOVOARRIVI
//    public Prodotto getProdottoByNuovoArrivo(boolean nuovoArrivo){
//
//        for(Prodotto p : getAllProdotti()){
//            if(p.isNuovoArrivi()){
//                return p;
//            }
//        }
//
//        throw new RuntimeException("Nuovo arrivo non trovato: " + nuovoArrivo);
//
//    }
//
//    // GET PRODOTTI PER GENERE
//    public Prodotto getProdottoByGenere(String genere){
//
//        for(Prodotto p : getAllProdotti()){
//            if(p.getGenere().equals(genere)){
//                return p;
//            }
//        }
//
//        throw new RuntimeException("Genere non trovato: " + genere);
//
//    }
