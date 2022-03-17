package song;

import java.util.List;

import artist.Artist;
import genre.Genre;
import bartype.BarType;

public class Song {

    private final long id;
    private final String title;
    private final List<Artist> artists;
    private final Genre genre;
    private final float bpm;
    private final BarType barType;

    public Song(long id, String title, List<Artist> artists, Genre genre, float bpm, BarType batType) {
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
}