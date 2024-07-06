package com.film.api.repository;

import com.film.api.model.Film;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Interface de repository pour l'entité Film.
 * Utilise Spring Data JPA pour accéder à la base de données et gérer les entités Film.
 */
@Repository
public interface IFilmRepository extends JpaRepository<Film, Long> {

    /**
     * Vérifie l'existence d'un film par son titre, insensible à la casse.
     *
     * @param titre Le titre du film à vérifier.
     * @return true si un film avec le titre donné existe, false sinon.
     */
    @Query("SELECT CASE WHEN COUNT(f) > 0 THEN true ELSE false END " +
            "FROM Film f " +
            "WHERE LOWER(f.titre) = LOWER(:titre)")
    boolean existsByTitre(@Param("titre") String titre);

}