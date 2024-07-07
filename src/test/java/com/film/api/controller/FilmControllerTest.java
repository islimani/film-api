package com.film.api.controller;

import com.film.api.dto.FilmDto;
import com.film.api.exception.FilmExistantException;
import com.film.api.exception.FilmNotFoundException;
import com.film.api.service.IFilmService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


/**
 * Tests unitaires pour {@link FilmController}.
 */
class FilmControllerTest {

    @Mock
    private IFilmService filmService;

    @InjectMocks
    private FilmController filmController;

    /**
     * Méthode de configuration exécutée avant chaque test pour initialiser les mocks.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Teste le comportement de {@link FilmController#getFilmById(Long)} pour un ID de film existant.
     */
    @Test
    void testGetFilmByIdSuccess() {
        // Given
        Long filmId = 1L;
        FilmDto filmDto = new FilmDto();
        filmDto.setId(filmId);
        filmDto.setTitre("Film Test");

        when(filmService.getFilmById(filmId)).thenReturn(filmDto);

        // When
        ResponseEntity<FilmDto> response = filmController.getFilmById(filmId);

        // Then
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(filmId, response.getBody().getId());
        assertEquals(filmDto.getTitre(), response.getBody().getTitre());
    }

    /**
     * Teste le comportement de {@link FilmController#getFilmById(Long)} pour un ID de film non trouvé.
     */
    @Test
    void testGetFilmByIdFilmNotFoundException() {
        // Given
        Long filmId = 1L;
        when(filmService.getFilmById(filmId)).thenThrow(new FilmNotFoundException(filmId));

        // When and Then
        FilmNotFoundException exception = assertThrows(FilmNotFoundException.class, () -> filmController.getFilmById(filmId));

        assertEquals("Le film ayant l'ID " + filmId + " n'a pas été trouvé!", exception.getMessage());
    }

    /**
     * Teste le comportement de {@link FilmController#saveFilm(FilmDto)} pour l'ajout d'un film avec succès.
     */
    @Test
    void testSaveFilmSuccess() {
        // Given
        FilmDto filmDto = new FilmDto();
        filmDto.setTitre("Film Test");
        filmDto.setDescription("Description du film test");
        filmDto.setActeurs(Collections.emptyList());

        when(filmService.saveFilm(any())).thenReturn(filmDto);

        // When
        ResponseEntity<FilmDto> response = filmController.saveFilm(filmDto);

        // Then
        assertEquals(HttpStatus.CREATED, response.getStatusCode());
        assertNotNull(response.getBody());
        assertEquals(filmDto.getTitre(), response.getBody().getTitre());
    }

    /**
     * Teste le comportement de {@link FilmController#saveFilm(FilmDto)} lorsqu'un film avec le même titre existe déjà.
     */
    @Test
    void testSaveFilmFilmExistantException() {
        // Given
        FilmDto filmDto = new FilmDto();
        filmDto.setTitre("Film Existant");
        filmDto.setDescription("Description du film existant");
        filmDto.setActeurs(Collections.emptyList());

        when(filmService.saveFilm(any())).thenThrow(new FilmExistantException("Film Existant"));

        // When
        assertThrows(FilmExistantException.class, () -> filmController.saveFilm(filmDto));
    }

}