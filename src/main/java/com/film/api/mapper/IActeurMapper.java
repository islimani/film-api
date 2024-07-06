package com.film.api.mapper;

import com.film.api.dto.ActeurDto;
import com.film.api.model.Acteur;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

/**
 * Interface Mapper pour la convertion entre Acteur et ActeurDto.
 */
@Mapper(componentModel = "spring")
public interface IActeurMapper {

    /**
     * Convertit un objet Acteur en ActeurDto.
     *
     * @param acteur L'acteur à convertir.
     * @return L'objet ActeurDto correspondant.
     */
    @Mapping(target = "nom", source = "nom", qualifiedByName = "trimNomPrenom")
    @Mapping(target = "prenom", source = "prenom", qualifiedByName = "trimNomPrenom")
    ActeurDto toDto(Acteur acteur);

    /**
     * Convertit un objet ActeurDto en Acteur.
     *
     * @param acteurDto L'ActeurDto à convertir.
     * @return L'objet Acteur correspondant.
     */
    @Mapping(target = "film", ignore = true)
    @Mapping(target = "nom", source = "nom", qualifiedByName = "trimNomPrenom")
    @Mapping(target = "prenom", source = "prenom", qualifiedByName = "trimNomPrenom")
    Acteur toEntity(ActeurDto acteurDto);

    /**
     * Méthode de trimming pour les champs de type String.
     *
     * @param value La valeur à trimmer.
     * @return La valeur trimmée ou null si la valeur est null.
     */
    @Named("trimNomPrenom")
    default String trimNomPrenom(String value) {
        return value != null ? value.trim() : null;
    }

}
