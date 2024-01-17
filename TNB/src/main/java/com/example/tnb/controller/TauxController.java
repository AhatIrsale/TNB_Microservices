package com.example.tnb.controller;

import com.example.tnb.entities.Taux;
import com.example.tnb.service.TauxService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/taux")
public class TauxController {

    @Autowired
    private TauxService tauxService;

    @GetMapping("")
    public List<Taux> getAllTaux() {
        return tauxService.getAllTaux();
    }

    @GetMapping("/{id}")
    public Taux getTauxById(@PathVariable UUID id) {
        return tauxService.getTauxById(id);
    }

    @PostMapping("")
    public Taux createTaux(@RequestBody Taux taux) {
        return tauxService.createTaux(taux);
    }

    @PutMapping("/{id}")
    public Taux updateTaux(@PathVariable UUID id, @RequestBody Taux taux) {
        return tauxService.updateTaux(id, taux);
    }

    @DeleteMapping("/{id}")
    public void deleteTaux(@PathVariable UUID id) {
        tauxService.deleteTaux(id);
    }
}

