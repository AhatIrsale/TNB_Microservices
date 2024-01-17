package com.example.tnb.controller;

import com.example.tnb.entities.TaxeTNB;
import com.example.tnb.service.TaxeTnbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/taxe-tnb")
public class TaxeTNBController {

    @Autowired
    private TaxeTnbService taxeTNBService;

    @GetMapping("")
    public List<TaxeTNB> getAllTaxeTNB() {
        return taxeTNBService.getAllTaxeTNB();
    }

    @GetMapping("/{id}")
    public TaxeTNB getTaxeTNBById(@PathVariable UUID id) {
        return taxeTNBService.getTaxeTNBById(id);
    }

    @PostMapping("")
    public TaxeTNB createTaxeTNB(@RequestBody TaxeTNB taxeTNB) {
        return taxeTNBService.createTaxeTNB(taxeTNB);
    }

    @PutMapping("/{id}")
    public TaxeTNB updateTaxeTNB(@PathVariable UUID id, @RequestBody TaxeTNB taxeTNB) {
        return taxeTNBService.updateTaxeTNB(id, taxeTNB);
    }

    @DeleteMapping("/{id}")
    public void deleteTaxeTNB(@PathVariable UUID id) {
        taxeTNBService.deleteTaxeTNB(id);
    }
}

