package com.example.tnb.controller;

import com.example.tnb.entities.Categorie;
import com.example.tnb.entities.Terain;
import com.example.tnb.entities.User;
import com.example.tnb.service.CategorieService;
import com.example.tnb.service.ClientService;
import com.example.tnb.service.TerrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/api/terrain")
public class TerrainController {

    @Autowired
    private TerrainService terrainService;

    @Autowired
    private ClientService clr;

    @Autowired
    private CategorieService cs;

    @GetMapping("")
    public List<Terain> getAllTerrain() {
        return terrainService.getAllTerrain();
    }

    @GetMapping("/{id}")
    public Terain getTerrainById(@PathVariable UUID id) {
        return terrainService.getTerrainById(id);
    }

    @PostMapping("")
    public Terain createTerrain(@RequestBody Terain terrain) {
        return terrainService.createTerrain(terrain);
    }

    @PutMapping("/{id}")
    public Terain updateTerrain(@PathVariable UUID id, @RequestBody Terain terrain) {
        return terrainService.updateTerrain(id, terrain);
    }

    @DeleteMapping("/{id}")
    public void deleteTerrain(@PathVariable UUID id) {
        terrainService.deleteTerrain(id);
    }

    @GetMapping("/by-category/{catId}")
    public List<Terain> getTerrainsByCategory(@PathVariable UUID catId) {
        Categorie cat = cs.getCategorieById(catId);
        return terrainService.getTerrainsByCategory(cat);
    }


    @GetMapping("/u/terrains/{username}")
    public ResponseEntity<List<Terain>> getTerrainsByUser(@PathVariable String username) {
        Optional<User> user = Optional.ofNullable(clr.getUserByUsername(username));

        if (user.isPresent()) {
            List<Terain> terrains = terrainService.getTerrainsByUser(user.get());
            return ResponseEntity.ok(terrains);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/t/user/{username}")
    public List<Terain> getTerrainsByUsername(@PathVariable String username) {
        return terrainService.getTerrainsByUsername(username);
    }

}

