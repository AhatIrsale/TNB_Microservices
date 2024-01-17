package com.example.tnb.repository;

import com.example.tnb.entities.Categorie;
import com.example.tnb.entities.Terain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface TerainRepository extends JpaRepository<Terain, UUID> {
    List<Terain> findByClient_Cin(String cin);
    List<Terain> findByCat(Categorie cat);
    List<Terain> findByUser_Username(String username);


}
