package com.marc_auberer.musicmanager.adapters;

public class GenreUiModel {

    private final long id;
    private final String name;

    public GenreUiModel(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }
}
