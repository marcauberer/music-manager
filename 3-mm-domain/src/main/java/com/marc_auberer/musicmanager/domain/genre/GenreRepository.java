package com.marc_auberer.musicmanager.domain.genre;

import java.util.List;

public interface GenreRepository {
    Genre findGenreById(long id);

    List<Genre> findAllGenres();

    Genre findGenreBySongId(long songId);

    Genre save(Genre genre);

    void delete(long id);
}