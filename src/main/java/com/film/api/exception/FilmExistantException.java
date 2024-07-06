package com.film.api.exception;

import com.film.api.constant.FilmConstants;

/**
 * Exception lancée lorsqu'un film existe déjà dans la base de données.
 */
public class FilmExistantException extends RuntimeException {

    /**
     * Constructeur prenant le titre du film existant comme argument.
     *
     * @param titre Le titre du film qui existe déjà.
     */
    public FilmExistantException(String titre) {
        super(FilmConstants.FILM_TITRE + titre + FilmConstants.EXISTE_DEJA);
    }

}
