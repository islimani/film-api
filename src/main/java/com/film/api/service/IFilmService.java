package com.film.api.service;

import com.film.api.dto.FilmDto;

/**
 * Interface de service pour la gestion des films.
 */
public interface IFilmService {

    /**
     * Récupère les détails d'un film par son ID.
     *
     * @param id L'ID du film à récupérer.
     * @return DTO représentant les détails du film trouvé.
     */
    FilmDto getFilmById(Long id);

    /**
     * Enregistre un nouveau film ou met à jour un film existant.
     *
     * @param filmDto DTO contenant les détails du film à enregistrer.
     * @return DTO représentant le film enregistré.
     */
    FilmDto saveFilm(FilmDto filmDto);

}
