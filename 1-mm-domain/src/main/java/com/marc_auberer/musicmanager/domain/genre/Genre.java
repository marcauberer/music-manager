package com.marc_auberer.musicmanager.domain.genre;

public class Genre {

    private long id;
    private final String name;

    public Genre(long id, String name) {
        this.id = id;
        this.name = name;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String[] getFieldContents() {
        return new String[] {String.valueOf(id), name};
    }

    public static String[] getCSVHeader() {
        return new String[] {"Id", "Name"};
    }
}