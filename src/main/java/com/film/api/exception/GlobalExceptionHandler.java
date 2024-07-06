package com.film.api.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Classe pour gérer les exceptions globales dans l'api.
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    /**
     * Gère l'exception FilmNotFoundException.
     *
     * @param ex L'exception FilmNotFoundException levée.
     * @return ResponseEntity contenant le message d'erreur et le statut HTTP NOT_FOUND.
     */
    @ExceptionHandler(FilmNotFoundException.class)
    public ResponseEntity<String> handleFilmNotFound(FilmNotFoundException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }

    /**
     * Gère l'exception FilmExistantException.
     *
     * @param ex L'exception FilmExistantException levée.
     * @return ResponseEntity contenant le message d'erreur et le statut HTTP CONFLICT.
     */
    @ExceptionHandler(FilmExistantException.class)
    public ResponseEntity<String> handleFilmExistant(FilmExistantException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.CONFLICT);
    }

    /**
     * Gère toutes les autres exceptions génériques.
     *
     * @param ex L'exception générique levée.
     * @return ResponseEntity contenant le message d'erreur et le statut HTTP INTERNAL_SERVER_ERROR.
     */
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleGenericException(Exception ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
