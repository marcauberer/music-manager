package com.marc_auberer.musicmanager.domain.song;

import com.marc_auberer.musicmanager.domain.artist.Artist;
import com.marc_auberer.musicmanager.domain.bartype.BarType;
import com.marc_auberer.musicmanager.domain.genre.Genre;

import java.util.List;

public class Song {

    private long id;
    private final String title;
    private final List<Artist> artists;
    private final Genre genre;
    private final float bpm;
    private final BarType barType;

    public Song(long id, String title, List<Artist> artists, Genre genre, float bpm, BarType barType) {
        this.id = id;
        this.title = title;
        this.artists = artists;
        this.genre = genre;
        this.bpm = bpm;
        this.barType = barType;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public List<Artist> getArtists() {
        return artists;
    }

    public Genre getGenre() {
        return genre;
    }

    public float getBpm() {
        return bpm;
    }

    public BarType getBarType() {
        return barType;
    }

    public String[] getFieldContents() {
        return new String[] {
                String.valueOf(id),
                String.valueOf(genre.getId()),
                title,
                String.valueOf(bpm),
                String.valueOf(barType.getId())
        };
    }

    public static String[] getCSVHeader() {
        return new String[] {"Id", "Title", "Genre", "Bpm", "BarType"};
    }

    @Override
    public String toString() {
        return title;
    }
}