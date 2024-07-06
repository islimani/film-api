package com.film.api.controller;

import com.film.api.dto.FilmDto;
import com.film.api.service.IFilmService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/film")
public class FilmController {

    private final IFilmService filmService;

    public FilmController(IFilmService filmService) {
        this.filmService = filmService;
    }

    /**
     * Endpoint GET pour obtenir un film par ID.
     *
     * @param id L'ID du film à récupérer.
     * @return ResponseEntity contenant le FilmDto du film trouvé.
     */
    @GetMapping("/{id}")
    public ResponseEntity<FilmDto> getFilmById(@PathVariable Long id) {
        var filmDto = filmService.getFilmById(id);
        return ResponseEntity.ok(filmDto);
    }

    /**
     * Endpoint POST pour ajouter un nouveau film s'il n'existe pas déjà en base.
     *
     * @param filmDTO Le FilmDto du film à ajouter.
     * @return ResponseEntity contenant le FilmDto du film ajouté avec le code de statut 201 CREATED.
     */
    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<FilmDto> saveFilm(@RequestBody FilmDto filmDTO) {
        var savedFilm = filmService.saveFilm(filmDTO);
        return new ResponseEntity<>(savedFilm, HttpStatus.CREATED);
    }

}
