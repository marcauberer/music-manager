package com.marc_auberer.musicmanager.domain.genre;

import java.util.Objects;

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

    @Override
    public String toString() {
        return name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        return ((Genre) obj).getId() == this.id;
    }
}