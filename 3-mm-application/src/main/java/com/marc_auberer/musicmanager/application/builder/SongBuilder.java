package com.marc_auberer.musicmanager.application.builder;

import com.marc_auberer.musicmanager.domain.artist.Artist;
import com.marc_auberer.musicmanager.domain.bartype.BarType;
import com.marc_auberer.musicmanager.domain.genre.Genre;
import com.marc_auberer.musicmanager.domain.song.Song;
import com.marc_auberer.musicmanager.domain.user.User;

import java.util.List;

import static com.marc_auberer.musicmanager.db.Repository.AUTO_INC;

public class SongBuilder {

    private final User user;
    private final String title;
    private final List<Artist> artists;
    private Genre genre = null;
    private float bpm = 0f;
    private BarType barType = null;

    public SongBuilder(User user, String title, List<Artist> artists) {
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

    public SongBuilder withBarType(BarType barType) {
        this.barType = barType;
        return this;
    }

    public Song build() {
        return new Song(AUTO_INC, user.getId(), title, artists, genre, bpm, barType);
    }
}