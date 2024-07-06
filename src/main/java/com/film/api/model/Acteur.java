package com.film.api.model;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class Acteur {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nom;
    private String prenom;

    @ManyToOne
    @JoinColumn(name = "film_id")
    private Film film;

}
