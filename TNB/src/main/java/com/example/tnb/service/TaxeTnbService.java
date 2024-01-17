package com.example.tnb.service;



import com.example.tnb.entities.TaxeTNB;
import com.example.tnb.repository.TaxeTnbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;


@Service
    public class TaxeTnbService {

        @Autowired
        private TaxeTnbRepository taxeTNBRepository;

        public List<TaxeTNB> getAllTaxeTNB() {
            return taxeTNBRepository.findAll();
        }

        public TaxeTNB getTaxeTNBById(UUID id) {
            return taxeTNBRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("TaxeTNB not found with id " + id));
        }

        public TaxeTNB createTaxeTNB(TaxeTNB taxeTNB) {
            return taxeTNBRepository.save(taxeTNB);
        }

        public TaxeTNB updateTaxeTNB(UUID id, TaxeTNB taxeTNB) {
            TaxeTNB existingTaxeTNB = taxeTNBRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("TaxeTNB not found with id " + id));
            existingTaxeTNB.setMarque(taxeTNB.getMarque());
            existingTaxeTNB.setClient(taxeTNB.getClient());
            existingTaxeTNB.setTaux(taxeTNB.getTaux());
            existingTaxeTNB.setTerain(taxeTNB.getTerain());
            return taxeTNBRepository.save(existingTaxeTNB);
        }

        public void deleteTaxeTNB(UUID id) {
            TaxeTNB existingTaxeTNB = taxeTNBRepository.findById(id)
                    .orElseThrow(() -> new ResourceNotFoundException("TaxeTNB not found with id " + id));
            taxeTNBRepository.delete(existingTaxeTNB);
        }
    }


