package com.marc_auberer.musicmanager.domain.song;

import com.marc_auberer.musicmanager.domain.artist.Artist;
import com.marc_auberer.musicmanager.domain.bartype.BarType;
import com.marc_auberer.musicmanager.domain.genre.Genre;

import java.util.List;

public class SongBuilder {

    private final String title;
    private final List<Artist> artists;
    private Genre genre;
    private float bpm = 0f;
    private BarType barType;

    public SongBuilder(String title, List<Artist> artists) {
        this.title = title;
        this.artists = artists;
    }

    public SongBuilder withGenre(Genre genre) {
        this.genre = genre;
        return this;
    }

    public SongBuilder withBpm(float bpm) {
        this.bpm = bpm;
        return this;
    }

    public SongBuilder withBarType(BarType barType) {
        this.barType = barType;
        return this;
    }

    public Song build() {
        return new Song(0, title, artists, genre, bpm, barType);
    }
}
