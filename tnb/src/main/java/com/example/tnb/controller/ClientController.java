package com.example.tnb.controller;

import com.example.tnb.entities.Client;
import com.example.tnb.entities.ERole;
import com.example.tnb.entities.Terain;
import com.example.tnb.entities.User;
import com.example.tnb.entities.payload.request.LoginRequest;
import com.example.tnb.entities.payload.request.SignupRequest;
import com.example.tnb.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/client")
public class ClientController {

    @Autowired
    private ClientService cls;

    @GetMapping("")
    public List<Client> getAllClient() {
        return cls.getAllClients();
    }

    @GetMapping("/{id}")
    public Client getClientById(@PathVariable UUID id) {
        return cls.getClientById(id);
    }

    @PostMapping("")
    public Client createClient(@RequestBody Client cl) {
        return cls.createClient(cl);
    }

    @PutMapping("/{id}")
    public Client updateClient(@PathVariable UUID id, @RequestBody Client cl) {
        Client existingclient= cls.getClientById(id);
        if (existingclient != null) {
            existingclient.setNom(cl.getNom());
            return cls.createClient(existingclient);
        }
        return null;
    }

    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable UUID id) {
        cls.deleteClient(id);
    }

    @GetMapping("/{cin}/terrains-et-taxes")
    public List<Terain> getTerrainsAndTaxesByCIN(@PathVariable String cin) {
        return cls.getTerrainsAndTaxesByCIN(cin);
    }


    //methode provenant du microservice

    @GetMapping("/u")
    public List<User> findAll() {
        return cls.findAll();
    }

    @GetMapping("/u/admin")
    public List<User> findAdmin() {
        return cls.findAdmin();
    }

    @GetMapping("/u/mod")
    public List<User> findMod() {
        return cls.findMod();
    }

    @GetMapping("/u/user")
    public List<User> findUser() {
        return cls.findUser();
    }

    @GetMapping("/u/role/{username}")
    public ERole findRole(@PathVariable String username) {
        return cls.findRole(username);
    }


    @GetMapping("/u/profile")
    public ResponseEntity<?> getUserProfile(HttpServletRequest request) {
        String jwtToken = extractJwtToken(request); // Méthode pour extraire le jeton du HttpServletRequest
        ResponseEntity<?> response = cls.getUserProfile(jwtToken);
        return response;
    }
    private String extractJwtToken(HttpServletRequest request) {
        String bearerToken = request.getHeader("Authorization");
        if (StringUtils.hasText(bearerToken) && bearerToken.startsWith("Bearer ")) {
            return bearerToken.substring(7); // Retourne le jeton en excluant le préfixe "Bearer "
        }
        return null; // Retourne null si aucun jeton n'est trouvé
    }



    @PostMapping("/u/signin")
    public ResponseEntity<?> authenticateUser( @RequestBody LoginRequest loginRequest) {
        return cls.authenticateUser(loginRequest);
    }

    @PostMapping("/u/signup")
    public ResponseEntity<?> registerUser( @RequestBody SignupRequest signUpRequest) {
        return cls.registerUser(signUpRequest);
    }



}
