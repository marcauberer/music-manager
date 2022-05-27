package com.marc_auberer.musicmanager.application.service;

import com.marc_auberer.musicmanager.application.observer.GenreListObserver;
import com.marc_auberer.musicmanager.db.GenreRepositoryImpl;
import com.marc_auberer.musicmanager.domain.genre.Genre;
import com.marc_auberer.musicmanager.domain.genre.GenreRepository;

import java.util.List;

public class GenreService {

    private final GenreRepository genreRepository;
    private final GenreListObserver genreListObserver;

    public GenreService(GenreListObserver genreListObserver) {
        this.genreListObserver = genreListObserver;
        this.genreRepository = new GenreRepositoryImpl();
        // Notify observers about initial data load
        genreListObserver.onGenreListChanged(getAllGenres());
    }

    public List<Genre> getAllGenres() {
        return genreRepository.findAllGenres();
    }

    public void createGenre(Genre genre) {
        genreRepository.save(genre);
        genreListObserver.onGenreListChanged(getAllGenres());
    }
}
