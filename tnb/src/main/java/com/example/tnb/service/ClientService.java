package com.example.tnb.service;

import com.example.tnb.entities.Client;
import com.example.tnb.entities.ERole;
import com.example.tnb.entities.Terain;
import com.example.tnb.entities.User;
import com.example.tnb.entities.payload.request.SignupRequest;
import com.example.tnb.entities.payload.request.LoginRequest;
import com.example.tnb.entities.payload.response.JwtResponse;

import com.example.tnb.repository.ClientRepository;
import com.example.tnb.repository.TerainRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clr;
    @Autowired
    private TerainRepository terrainRepository;

    public List<Client> getAllClients() {
        return clr.findAll();
    }


    public Client getClientById(UUID id) {
        return clr.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id " + id));
    }

    public Client createClient(Client client) {
        return clr.save(client);
    }

    public Client updateClient(UUID id, Client clientd) {
        Client cl = clr.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Client not found with id " + id));
        cl.setNom(clientd.getNom());
        cl.setCin(clientd.getCin());
        return clr.save(cl);
    }

    public void deleteClient(UUID id) {
        Client cl = clr.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Zone not found with id " + id));
        clr.delete(cl);
    }
    public List<Terain> getTerrainsAndTaxesByCIN(String cin) {
        return terrainRepository.findByClient_Cin(cin);
    }


    @Autowired
    private RestTemplate restTemplate;
    private final String URL = "http://localhost:8888/SERVICE-USER";



    //others




    public ResponseEntity<?> authenticateUser(LoginRequest loginRequest) {
        ResponseEntity<JwtResponse> response = restTemplate.postForEntity(this.URL + "/api/auth/signin", loginRequest, JwtResponse.class);
        JwtResponse jwtResponse = response.getBody();
        return ResponseEntity.ok(jwtResponse);
    }

    public ResponseEntity<?> registerUser(SignupRequest signUpRequest) {
        ResponseEntity<String> response = restTemplate.postForEntity(this.URL + "/api/auth/signup", signUpRequest, String.class);
        String responseBody = response.getBody();
        System.out.println("Response Body: " + responseBody);
        // Traitez manuellement le corps de la réponse comme une chaîne ou essayez une autre approche de désérialisation
        return ResponseEntity.ok(responseBody);
    }


    //get methodes

    public List<User> findAll() {
        ResponseEntity<User[]> response = restTemplate.getForEntity(this.URL + "/api/auth/users", User[].class);
        User[] users = response.getBody(); // Extraction du corps de la réponse

        // Vérification que la réponse n'est pas nulle avant de retourner la liste
        return users != null ? Arrays.asList(users) : Collections.emptyList();
    }

    public ResponseEntity<?> getUserProfile(String jwtToken) {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", "Bearer " + jwtToken);

        HttpEntity<String> entity = new HttpEntity<>(headers);

        ResponseEntity<User> response = restTemplate.exchange(
                this.URL + "/api/auth/user-profile",
                HttpMethod.GET,
                entity,
                User.class
        );

        User user = response.getBody();
        return ResponseEntity.ok(user);
    }

    public List<User> findAdmin() {
        ResponseEntity<User[]> response = restTemplate.getForEntity(this.URL + "/api/auth/users/admin", User[].class);
        User[] users = response.getBody();
        return users != null ? Arrays.asList(users) : Collections.emptyList();
    }

    public List<User> findMod() {
        ResponseEntity<User[]> response = restTemplate.getForEntity(this.URL + "/api/auth/users/mod", User[].class);
        User[] users = response.getBody();
        return users != null ? Arrays.asList(users) : Collections.emptyList();
    }

    public List<User> findUser() {
        ResponseEntity<User[]> response = restTemplate.getForEntity(this.URL + "/api/auth/users/user", User[].class);
        User[] users = response.getBody();
        return users != null ? Arrays.asList(users) : Collections.emptyList();
    }

    public ERole findRole(String username) {
        ResponseEntity<ERole> response = restTemplate.getForEntity(this.URL + "/api/auth/users/role/" + username, ERole.class);
        return response.getBody();
    }

    public Optional<User> findByCin(String cin) {
        return clr.findByCin(cin);
    }

    public User getUserByUsername(String username) {
        ResponseEntity<User> responseEntity = restTemplate.getForEntity(this.URL +"/api/auth/user/{username}", User.class, username);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            return responseEntity.getBody();
        } else {
            return null; // ou gérer autrement en cas d'erreur
        }
    }

}
