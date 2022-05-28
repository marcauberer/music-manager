package com.marc_auberer.musicmanager.application.builder;

import com.marc_auberer.musicmanager.domain.artist.Artist;
import com.marc_auberer.musicmanager.domain.genre.Genre;
import com.marc_auberer.musicmanager.domain.song.Song;
import com.marc_auberer.musicmanager.domain.user.User;

import java.util.List;

public class SongBuilder {

    private final User user;
    private final long id;
    private final String title;
    private final List<Artist> artists;
    private Genre genre = null;
    private float bpm = 0f;
    private com.marc_auberer.musicmanager.domain.bartype.BarType barType = null;

    public SongBuilder(long id, User user, String title, List<Artist> artists) {
        this.id = id;
        this.user = user;
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

    public SongBuilder withBarType(com.marc_auberer.musicmanager.domain.bartype.BarType barType) {
        this.barType = barType;
        return this;
    }

    public Song build() {
        return new Song(id, user.getId(), title, artists, genre, bpm, barType);
    }
}
