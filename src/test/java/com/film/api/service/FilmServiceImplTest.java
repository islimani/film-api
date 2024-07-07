package com.film.api.service;

import com.film.api.dto.FilmDto;
import com.film.api.exception.FilmExistantException;
import com.film.api.exception.FilmNotFoundException;
import com.film.api.mapper.IFilmMapper;
import com.film.api.model.Film;
import com.film.api.repository.IFilmRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.dao.DataIntegrityViolationException;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

/**
 * Tests unitaires pour {@link FilmServiceImpl}.
 */
class FilmServiceImplTest {

    @Mock
    private IFilmRepository filmRepository;

    @Mock
    private IFilmMapper filmMapper;

    @InjectMocks
    private FilmServiceImpl filmService;

    /**
     * Initialisation des mocks avant chaque test.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        filmService = new FilmServiceImpl(filmRepository, filmMapper);
    }

    /**
     * Test de la méthode {@link FilmServiceImpl#getFilmById(Long)} pour un cas de succès.
     */
    @Test
    void testGetFilmByIdSuccess() {
        // Mock des données
        Long id = 1L;
        Film film = new Film();
        film.setId(id);
        film.setTitre("Titre Test");
        film.setDescription("Description test");

        FilmDto filmDto = new FilmDto();
        filmDto.setId(id);
        filmDto.setTitre("Titre Test");
        filmDto.setDescription("Description test");

        // Mock  du repository
        when(filmRepository.findById(id)).thenReturn(Optional.of(film));

        // Mock  du mapper
        when(filmMapper.toDto(film)).thenReturn(filmDto);

        // Appel de la méthode à tester
        FilmDto result = filmService.getFilmById(id);

        // Assertions
        assertNotNull(result);
        assertEquals(filmDto.getId(), result.getId());
        assertEquals(filmDto.getTitre(), result.getTitre());
        assertEquals(filmDto.getDescription(), result.getDescription());

    }

    /**
     * Test de la méthode {@link FilmServiceImpl#getFilmById(Long)} pour un cas où le film n'est pas trouvé.
     */
    @Test
    void testGetFilmByIdNotFound() {
        // Mock des données
        Long id = 1L;

        // Mock du repository
        when(filmRepository.findById(id)).thenReturn(Optional.empty());

        // Assertion de l'exception FilmNotFoundException
        assertThrows(FilmNotFoundException.class, () -> filmService.getFilmById(id));

    }

    /**
     * Test de la méthode {@link FilmServiceImpl#saveFilm(FilmDto)} pour un cas où le titre du film existe déjà.
     */
    @Test
    void testSaveFilmExistingTitle() {
        // Mock des données
        FilmDto filmDto = new FilmDto();
        filmDto.setTitre("Titre Test");
        filmDto.setDescription("Description test");

        Film film = new Film();
        film.setTitre("Titre Test");
        film.setDescription("Description test");

        // Mock du mapper
        when(filmMapper.toEntity(filmDto)).thenReturn(film);

        // Mock du repository
        when(filmRepository.existsByTitre(film.getTitre())).thenReturn(true);

        // Assertion de l'exception FilmExistantException
        assertThrows(FilmExistantException.class, () -> filmService.saveFilm(filmDto));

    }

    /**
     * Test de la méthode {@link FilmServiceImpl#saveFilm(FilmDto)} pour une exception lors de l'enregistrement.
     */
    @Test
    void testSaveFilmDataIntegrityViolation() {
        // Mock des données
        FilmDto filmDto = new FilmDto();
        filmDto.setTitre("Titre Test");
        filmDto.setDescription("Description test");

        Film film = new Film();
        film.setTitre("Titre Test");
        film.setDescription("Description test");

        // Mock du mapper
        when(filmMapper.toEntity(filmDto)).thenReturn(film);

        // Mock du repository
        when(filmRepository.existsByTitre(film.getTitre())).thenReturn(false);
        when(filmRepository.save(film)).thenThrow(DataIntegrityViolationException.class);

        // Assertion de l'exception DataIntegrityViolationException
        assertThrows(DataIntegrityViolationException.class, () -> filmService.saveFilm(filmDto));

    }

    /**
     * Test de la méthode {@link FilmServiceImpl#saveFilm(FilmDto)} pour un cas où le mapping vers l'entité Film retourne null.
     */
    @Test
    void testSaveFilmNullMapping() {
        // Mock des données
        FilmDto filmDto = new FilmDto();

        // Mock du comportement du mapper
        when(filmMapper.toEntity(filmDto)).thenReturn(null);

        // Assertion de l'exception IllegalArgumentException
        assertThrows(IllegalArgumentException.class, () -> filmService.saveFilm(filmDto));

    }

}
