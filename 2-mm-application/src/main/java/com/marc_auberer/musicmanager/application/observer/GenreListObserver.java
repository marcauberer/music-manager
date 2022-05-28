package com.marc_auberer.musicmanager.application.observer;

import com.marc_auberer.musicmanager.domain.genre.Genre;

import java.util.List;

public interface GenreListObserver {
    void onGenreListChanged(List<Genre> genreList);
}
