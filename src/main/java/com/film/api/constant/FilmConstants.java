package com.film.api.constant;

/**
 * Classe contenant les constantes et messages utilisés dans l'api des films.
 */
public class FilmConstants {

    private FilmConstants() {
        throw new AssertionError("Cette classe ne doit pas étre instanciée!");
    }

    public static final String FILM_TITRE = "Le film avec le titre '";
    public static final String EXISTE_DEJA = "' existe déjà!";
    public static final String FILM_ID = "Le film ayant l'ID ";
    public static final String PAS_TROUVE = " n'a pas été trouvé!";
    public static final String TROUVE = " a été trouvé!";
    public static final String APPEL_SERVICE_GETBYID = "Appel du service getFilmById avec l'Id: ";
    public static final String APPEL_SERVICE_SAVE = "Appel du service saveFilm avec le film: ";
    public static final String BIEN_ENREGISTRE = "' a bien été enregistré!";

}
