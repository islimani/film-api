package com.film.api.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class FilmDto {

    private Long id;
    private String titre;
    private String description;
    private List<ActeurDto> acteurs;

}