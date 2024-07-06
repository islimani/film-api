package com.film.api.mapper;

import com.film.api.dto.FilmDto;
import com.film.api.model.Film;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * Interface Mapper pour la convertion entre Film et FilmDto.
 */
@Mapper(componentModel = "spring", uses = {IActeurMapper.class})
public interface IFilmMapper {

    /**
     * Convertit un objet Film en FilmDto.
     *
     * @param film L'objet Film à convertir.
     * @return Le FilmDto converti.
     */
    @Mapping(target = "acteurs", source = "acteurs")
    @Mapping(target = "titre", source = "titre", qualifiedByName = "trimTitreDesc")
    @Mapping(target = "description", source = "description", qualifiedByName = "trimTitreDesc")
    FilmDto toDto(Film film);

    /**
     * Convertit un objet FilmDto en Film.
     *
     * @param filmDto Le FilmDto à convertir.
     * @return Le Film converti.
     */
    @Mapping(target = "titre", source = "titre", qualifiedByName = "trimTitreDesc")
    @Mapping(target = "description", source = "description", qualifiedByName = "trimTitreDesc")
    @Mapping(target = "acteurs", source = "acteurs")
    Film toEntity(FilmDto filmDto);

    /**
     * Méthode de trim personnalisée pour enlever les espaces avant et après une chaîne de caractères.
     *
     * @param value La valeur à trimmer.
     * @return La chaîne de caractères trimmée ou null si la valeur initiale est null.
     */
    @Named("trimTitreDesc")
    default String trimTitreDesc(String value) {
        return value != null ? value.trim() : null;
    }

}
