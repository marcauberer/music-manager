package com.marc_auberer.musicmanager.domain.song;

import com.marc_auberer.musicmanager.domain.artist.Artist;
import com.marc_auberer.musicmanager.domain.genre.Genre;

import java.util.List;
import java.util.Objects;

public class Song {

    private long id;
    private final long userId;
    private final String title;
    private final List<Artist> artists;
    private final Genre genre;
    private final float bpm;
    private final com.marc_auberer.musicmanager.domain.bartype.BarType barType;

    public Song(long id, long userId, String title, List<Artist> artists, Genre genre, float bpm, com.marc_auberer.musicmanager.domain.bartype.BarType barType) {
        this.id = id;
        this.userId = userId;
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

    public long getUserId() {
        return userId;
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

    public com.marc_auberer.musicmanager.domain.bartype.BarType getBarType() {
        return barType;
    }

    public String[] getFieldContents() {
        String genreString = genre == null ? "-1" : String.valueOf(genre.getId());
        String bpmString = bpm == 0f ? "" : String.valueOf(bpm);
        String barTypeString = barType == null ? "-1" : String.valueOf(barType.getId());
        return new String[] {String.valueOf(id), String.valueOf(userId), title, genreString, bpmString, barTypeString};
    }

    public static String[] getCSVHeader() {
        return new String[] {"Id", "UserId", "Title", "Genre", "Bpm", "BarType"};
    }

    @Override
    public String toString() {
        return title;
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
        return ((Song) obj).getId() == this.id;
    }
}