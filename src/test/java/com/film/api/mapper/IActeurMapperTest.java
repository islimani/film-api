package com.film.api.mapper;

import com.film.api.dto.ActeurDto;
import com.film.api.model.Acteur;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Tests unitaires pour {@link IActeurMapper}.
 */
class IActeurMapperTest {

    private IActeurMapper acteurMapper;

    /**
     * Initialisation du mapper avant chaque test.
     */
    @BeforeEach
    public void setUp() {
        acteurMapper = Mappers.getMapper(IActeurMapper.class);
    }

    /**
     * Test de la méthode {@link IActeurMapper#toDto(Acteur)}.
     */
    @Test
    void testToDto() {
        // Mock de l'objet Acteur
        Acteur acteur = new Acteur();
        acteur.setNom("Slimani");
        acteur.setPrenom("Ilyass");

        // Appel de la méthode à tester
        ActeurDto acteurDto = acteurMapper.toDto(acteur);

        // Assertions
        assertEquals("Slimani", acteurDto.getNom());
        assertEquals("Ilyass", acteurDto.getPrenom());
    }

    /**
     * Test de la méthode {@link IActeurMapper#toEntity(ActeurDto)}.
     */
    @Test
    void testToEntity() {
        // Mock de l'objet ActeurDto
        ActeurDto acteurDto = new ActeurDto();
        acteurDto.setNom("Slimani");
        acteurDto.setPrenom("Ilyass");

        // Appel de la méthode à tester
        Acteur acteur = acteurMapper.toEntity(acteurDto);

        // Assertions
        assertEquals("Slimani", acteur.getNom());
        assertEquals("Ilyass", acteur.getPrenom());
    }
}