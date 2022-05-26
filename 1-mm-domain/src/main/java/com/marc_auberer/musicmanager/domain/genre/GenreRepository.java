package com.marc_auberer.musicmanager.domain.genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {
    Optional<Genre> findGenreById(long id);

    List<Genre> findAllGenres();

    void save(Genre genre);

    void delete(long id);
}