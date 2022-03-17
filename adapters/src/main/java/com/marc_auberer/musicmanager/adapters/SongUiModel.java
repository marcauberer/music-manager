package com.marc_auberer.musicmanager.adapters;

import com.marc_auberer.musicmanager.domain.artist.Artist;
import com.marc_auberer.musicmanager.domain.bartype.BarType;
import com.marc_auberer.musicmanager.domain.genre.Genre;

import java.util.ArrayList;
import java.util.List;

public class SongUiModel {

    private final long id;
    private final String title;
    private final List<Artist> artists;
    private final Genre genre;
    private final float bpm;
    private final BarType barType;

    public SongUiModel(long id, String title, List<Artist> artists, Genre genre, float bpm, BarType batType) {
        this.id = id;
        this.title = title;
        this.artists = artists;
        this.genre = genre;
        this.bpm = bpm;
        this.barType = batType;
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

    @Override
    public String toString() {
        // Artist list to string
        List<String> artistsStrings = new ArrayList<>();
        for (Artist artist : artists) {
            artistsStrings.add(artist.getFirstName() + " " + artist.getLastName());
        }
        String artistsString = String.join(", " + artistsStrings);

        return title + " by " + artistsString + " (Genre: " + genre + ", Bpm: " + bpm + ", Bar type: " + barType + ")";
    }
}
