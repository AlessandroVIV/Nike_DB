package com.project.Nike_DB.services;

import com.project.Nike_DB.model.Prodotto;
import com.project.Nike_DB.repository.NikeRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdottoService {

    private final NikeRepository nikeRepository;

    public ProdottoService(NikeRepository nikeRepository) {
        this.nikeRepository = nikeRepository;
    }

    public List<Prodotto> getAllProdotti() {
        return nikeRepository.findAll();
    }

    public Optional<Prodotto> getProdottoById(Long id) {
        return nikeRepository.findById(id);
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


}
