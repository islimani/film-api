package com.film.api.exception;

import com.film.api.constant.FilmConstants;

/**
 * Exception lancée lorsqu'un film n'est pas trouvé dans la base de données.
 */
public class FilmNotFoundException extends RuntimeException {

    /**
     * Constructeur prenant l'id du film non trouvé comme argument.
     *
     * @param id L'id du film qui n'a pas été trouvé.
     */
    public FilmNotFoundException(Long id) {
        super(FilmConstants.FILM_ID + id + FilmConstants.PAS_TROUVE);
    }
}
