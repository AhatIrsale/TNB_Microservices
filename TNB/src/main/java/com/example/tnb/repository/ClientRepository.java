package com.example.tnb.repository;

import com.example.tnb.entities.Client;
import com.example.tnb.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {

    Optional<User> findByCin(String cin);
}
