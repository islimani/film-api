package com.film.api.mapper;

import com.film.api.dto.FilmDto;
import com.film.api.model.Film;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests unitaires pour {@link IFilmMapper}.
 */
class IFilmMapperTest {

    private IFilmMapper filmMapper;

    /**
     * Initialisation du mapper avant chaque test.
     */
    @BeforeEach
    public void setUp() {
        filmMapper = Mappers.getMapper(IFilmMapper.class);
    }

    /**
     * Test de la méthode {@link IFilmMapper#toDto(Film)}.
     */
    @Test
    void testToDto() {
        // Mock de l'objet Film
        Film film = new Film();
        film.setTitre("Titre");
        film.setDescription("Description");

        // Appel de la méthode à tester
        FilmDto filmDto = filmMapper.toDto(film);

        // Assertions
        assertEquals("Titre", filmDto.getTitre());
        assertEquals("Description", filmDto.getDescription());
    }

    /**
     * Test de la méthode {@link IFilmMapper#toEntity(FilmDto)}.
     */
    @Test
    void testToEntity() {
        // Mock de l'objet FilmDto
        FilmDto filmDto = new FilmDto();
        filmDto.setTitre("Titre");
        filmDto.setDescription("Description");

        // Appel de la méthode à tester
        Film film = filmMapper.toEntity(filmDto);

        // Assertions
        assertEquals("Titre", film.getTitre());
        assertEquals("Description", film.getDescription());
    }
}