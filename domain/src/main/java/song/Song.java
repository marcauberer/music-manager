package song;

import java.util.List;

import artist.Artist;
import genre.Genre;
import bartype.BarType;

public class Song {

    private long id;
    private String title;
    private List<Artist> artists;
    private Genre genre;
    private float bpm;
    private BarType barType;

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