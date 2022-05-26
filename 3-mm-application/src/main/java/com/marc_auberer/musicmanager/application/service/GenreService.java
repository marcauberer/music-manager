package com.marc_auberer.musicmanager.application.service;

import com.marc_auberer.musicmanager.db.GenreRepositoryImpl;
import com.marc_auberer.musicmanager.domain.genre.Genre;
import com.marc_auberer.musicmanager.domain.genre.GenreRepository;

import java.util.List;

public class GenreService {

    private final GenreRepository genreRepository;

    public GenreService() {
        this.genreRepository = new GenreRepositoryImpl();
    }

    public List<Genre> getAllGenres() {
        return genreRepository.findAllGenres();
    }

    public void createGenre(Genre genre) {
        genreRepository.save(genre);
    }
}
