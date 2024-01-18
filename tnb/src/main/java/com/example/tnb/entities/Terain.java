package com.example.tnb.entities;

import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Terain {
    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    private UUID id;
    private String nom;
    private int surface;
    @ManyToOne
    private Categorie cat;
    @ManyToOne
    private Client  client;
    @ManyToOne
    private User user;
    @OneToMany(targetEntity = TaxeTNB.class, mappedBy = "terain" , fetch = FetchType.LAZY)
    private List<TaxeTNB> taxes;
    public Terain(String id) {
        this.id = UUID.fromString(id);
    }





}
