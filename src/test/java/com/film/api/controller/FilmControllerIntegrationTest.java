package com.film.api.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.film.api.dto.FilmDto;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Tests d'intégration pour le contrôleur FilmController.
 */
@SpringBootTest
@AutoConfigureMockMvc
class FilmControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    /**
     * Teste la récupération d'un film par son ID.
     *
     * @throws Exception si une erreur survient lors de la requête MockMvc.
     */
    @Test
    void testGetFilmById() throws Exception {
        Long filmId = 1L;

        mockMvc.perform(get("/api/film/{id}", filmId))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(filmId));
    }

    /**
     * Teste l'enregistrement d'un film'.
     *
     * @throws Exception si une erreur survient lors de la requête MockMvc.
     */
    @Test
    void testSaveFilm() throws Exception {
        FilmDto filmDto = new FilmDto();
        filmDto.setTitre("Nouveau Film");
        filmDto.setDescription("Description du nouveau film");

        mockMvc.perform(post("/api/film")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(filmDto)))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.titre").value(filmDto.getTitre()));
    }
}
