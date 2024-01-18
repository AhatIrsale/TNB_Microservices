package com.example.tnb.repository;

import com.example.tnb.entities.TaxeTNB;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface TaxeTnbRepository extends JpaRepository<TaxeTNB, UUID> {
}
