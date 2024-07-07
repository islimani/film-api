package com.film.api.repository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DataJpaTest
class IFilmRepositoryTest {

    @Mock
    private IFilmRepository filmRepository;

    /**
     * Test de la méthode existsByTitre avec un titre existant.
     */
    @Test
    void testExistsByTitre_TitreExistant() {
        // Given
        String titreExistant = "Casablanca!";
        when(filmRepository.existsByTitre(any(String.class))).thenReturn(true);

        // When
        boolean existe = filmRepository.existsByTitre(titreExistant);

        // Then
        assertTrue(existe, "Le film avec le titre existant devrait retourner true.");
        verify(filmRepository, times(1)).existsByTitre(titreExistant);
    }

    /**
     * Test de la méthode existsByTitre avec un titre non existant.
     */
    @Test
    void testExistsByTitre_TitreNonExistant() {
        // Given
        String titreInexistant = "Titre inexistant";
        when(filmRepository.existsByTitre(any(String.class))).thenReturn(false);

        // When
        boolean existe = filmRepository.existsByTitre(titreInexistant);

        // Then
        assertFalse(existe, "Le film avec le titre inexistant devrait retourner false.");
        verify(filmRepository, times(1)).existsByTitre(titreInexistant);
    }

    /**
     * Test de la méthode existsByTitre avec un titre null.
     */
    @Test
    void testExistsByTitre_TitreNull() {
        // When
        boolean existe = filmRepository.existsByTitre(null);

        // Then
        assertFalse(existe, "La méthode avec titre null devrait retourner false.");
        verify(filmRepository, times(1)).existsByTitre(null);
    }
}
