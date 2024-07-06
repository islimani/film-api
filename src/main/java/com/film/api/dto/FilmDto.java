package com.film.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class FilmDto {

    private Long id;
    private String titre;
    private String description;
    private List<ActeurDto> acteurs;

}