package com.marc_auberer.musicmanager.domain.song;

import com.marc_auberer.musicmanager.domain.artist.Artist;
import com.marc_auberer.musicmanager.domain.bartype.BarType;
import com.marc_auberer.musicmanager.domain.genre.Genre;

import java.util.List;

public class Song {

    private long id;
    private long userId;
    private final String title;
    private final List<Artist> artists;
    private final Genre genre;
    private final float bpm;
    private final BarType barType;

    public Song(long id, long userId, String title, List<Artist> artists, Genre genre, float bpm, BarType barType) {
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

    public BarType getBarType() {
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
        return (int) this.id;
    }
}