package com.example.tnb.service;

import com.example.tnb.entities.Categorie;
import com.example.tnb.entities.Terain;
import com.example.tnb.entities.User;
import com.example.tnb.repository.TerainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TerrainService {

    @Autowired
    private TerainRepository terrainRepository;

    public List<Terain> getAllTerrain() {
        return terrainRepository.findAll();
    }

    public Terain getTerrainById(UUID id) {
        return terrainRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Terrain not found with id " + id));
    }

    public Terain createTerrain(Terain terrain) {
        return terrainRepository.save(terrain);
    }

    public Terain updateTerrain(UUID id, Terain terrain) {
        Terain existingTerrain = terrainRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Terrain not found with id " + id));
        existingTerrain.setSurface(terrain.getSurface());
        existingTerrain.setCat(terrain.getCat());
        existingTerrain.setClient(terrain.getClient());
        return terrainRepository.save(existingTerrain);
    }

    public void deleteTerrain(UUID id) {
        Terain existingTerrain = terrainRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Terrain not found with id " + id));
        terrainRepository.delete(existingTerrain);
    }
    public List<Terain> getTerrainsByCategory(Categorie cat) {
        return terrainRepository.findByCat(cat);
    }

    public List<Terain> getTerrainsByUser(User user) {
        return terrainRepository.findByClient_Cin(user.getCin());
    }

    public List<Terain> getTerrainsByUsername(String username) {
        return terrainRepository.findByUser_Username(username);
    }

}
