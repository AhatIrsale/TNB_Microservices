package com.example.tnb.service;

import com.example.tnb.entities.Categorie;
import com.example.tnb.repository.CategorieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class CategorieService {

    @Autowired
    private CategorieRepository categorieRepository;

    public List<Categorie> getAllCategories() {
        return categorieRepository.findAll();
    }

    public Categorie getCategorieById(UUID id) {
        return categorieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categorie not found with id " + id));
    }

    public Categorie createCategorie(Categorie categorie) {
        return categorieRepository.save(categorie);
    }

    public Categorie updateCategorie(UUID id, Categorie categorie) {
        Categorie existingCategorie = categorieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categorie not found with id " + id));
        existingCategorie.setLibelle(categorie.getLibelle());
        existingCategorie.setDescription(categorie.getDescription());
        return categorieRepository.save(existingCategorie);
    }

    public void deleteCategorie(UUID id) {
        Categorie existingCategorie = categorieRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Categorie not found with id " + id));
        categorieRepository.delete(existingCategorie);
    }
}
