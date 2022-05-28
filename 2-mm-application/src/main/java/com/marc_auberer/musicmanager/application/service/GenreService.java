package com.marc_auberer.musicmanager.application.service;

import com.marc_auberer.musicmanager.domain.genre.Genre;

import java.util.List;

public interface GenreService {

    List<Genre> getAllGenres();

    void createGenre(Genre genre);

}
