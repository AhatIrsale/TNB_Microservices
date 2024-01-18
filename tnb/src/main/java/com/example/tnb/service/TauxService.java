package com.example.tnb.service;

import com.example.tnb.entities.Taux;
import com.example.tnb.repository.TauxRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TauxService {

    @Autowired
    private TauxRepository tauxRepository;

    public List<Taux> getAllTaux() {
        return tauxRepository.findAll();
    }

    public Taux getTauxById(UUID id) {
        return tauxRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Taux not found with id " + id));
    }

    public Taux createTaux(Taux taux) {
        return tauxRepository.save(taux);
    }

    public Taux updateTaux(UUID id, Taux taux) {
        Taux existingTaux = tauxRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Taux not found with id " + id));
        existingTaux.setAnne(taux.getAnne());
        existingTaux.setValeur(taux.getValeur());
        existingTaux.setCat(taux.getCat());
        return tauxRepository.save(existingTaux);
    }

    public void deleteTaux(UUID id) {
        Taux existingTaux = tauxRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Taux not found with id " + id));
        tauxRepository.delete(existingTaux);
    }
}
