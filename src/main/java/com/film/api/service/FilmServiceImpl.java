package com.film.api.service;

import com.film.api.constant.FilmConstants;
import com.film.api.dto.FilmDto;
import com.film.api.exception.FilmExistantException;
import com.film.api.exception.FilmNotFoundException;
import com.film.api.mapper.IFilmMapper;
import com.film.api.repository.IFilmRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Implémentation du service pour la gestion des films.
 */
@Service
public class FilmServiceImpl implements IFilmService {

    private static final Logger logger = LoggerFactory.getLogger(FilmServiceImpl.class);

    private final IFilmRepository filmRepository;
    private final IFilmMapper filmMapper;

    public FilmServiceImpl(IFilmRepository filmRepository, IFilmMapper filmMapper) {
        this.filmRepository = filmRepository;
        this.filmMapper = filmMapper;
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Override
    public FilmDto getFilmById(Long id) {
        logger.debug(FilmConstants.APPEL_SERVICE_GETBYID, id);
        return filmRepository.findById(id)
                .map(filmMapper::toDto)
                .map(filmDto -> {
                    logger.info(FilmConstants.FILM_ID + "{}" + FilmConstants.TROUVE, id);
                    return filmDto;
                })
                .orElseThrow(() -> {
                    logger.warn(FilmConstants.FILM_ID + "{}" + FilmConstants.PAS_TROUVE, id);
                    return new FilmNotFoundException(id);
                });
    }

    /**
     * {@inheritDoc}
     */
    @Transactional
    @Override
    public FilmDto saveFilm(FilmDto filmDto) {
        logger.debug(FilmConstants.APPEL_SERVICE_SAVE, filmDto);
        var film = filmMapper.toEntity(filmDto);
        if (film == null) {
            throw new IllegalArgumentException("Le mapping de FilmDto vers Film a retourné null");
        }
        if (filmRepository.existsByTitre(film.getTitre())) {
            logger.warn(FilmConstants.FILM_TITRE + "{}" + FilmConstants.EXISTE_DEJA, film.getTitre());
            throw new FilmExistantException(film.getTitre());
        }
        film.getActeurs().forEach(acteur -> acteur.setFilm(film));
        var savedFilm = filmRepository.save(film);
        logger.info(FilmConstants.FILM_TITRE + "{}" + FilmConstants.BIEN_ENREGISTRE, savedFilm.getTitre());
        return filmMapper.toDto(savedFilm);
    }

}