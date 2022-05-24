package com.marc_auberer.musicmanager.adapters;

import com.marc_auberer.musicmanager.domain.genre.Genre;

import java.util.function.Function;

public class GenreToGenreUiModelMapper implements Function<Genre, GenreUiModel> {
    @Override
    public GenreUiModel apply(Genre genre) {
        return new GenreUiModel(
                genre.getId(),
                genre.getName()
        );
    }
}
