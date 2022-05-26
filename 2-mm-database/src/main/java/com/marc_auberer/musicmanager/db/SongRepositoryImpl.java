package com.marc_auberer.musicmanager.db;

import com.marc_auberer.musicmanager.domain.artist.Artist;
import com.marc_auberer.musicmanager.domain.bartype.BarType;
import com.marc_auberer.musicmanager.domain.exception.TransitiveDataException;
import com.marc_auberer.musicmanager.domain.genre.Genre;
import com.marc_auberer.musicmanager.domain.song.Song;
import com.marc_auberer.musicmanager.domain.song.SongRepository;
import com.marc_auberer.musicmanager.utils.CSVHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class SongRepositoryImpl extends Repository implements SongRepository {

    private static final String FILE_PATH = "./data/songs.csv";
    private final CSVHelper csvHelper;
    private final List<Song> songs = new ArrayList<>();
    private final ArtistRepositoryImpl artistRepository;
    private final GenreRepositoryImpl genreRepository;
    private final BarTypeRepositoryImpl barTypeRepository;

    public SongRepositoryImpl() {
        artistRepository = new ArtistRepositoryImpl();
        genreRepository = new GenreRepositoryImpl();
        barTypeRepository = new BarTypeRepositoryImpl();
        csvHelper = new CSVHelper(FILE_PATH, ";");
        // Pre-fetch all songs at once
        reload();
    }

    @Override
    public Optional<Song> findSongById(long id) {
        return Optional.empty();
    }

    @Override
    public List<Song> findAllSongs() {
        return Collections.emptyList();
    }

    @Override
    public List<Song> findAllSongsByUserId(long userId) {
        return Collections.emptyList();
    }

    @Override
    public void save(Song song) {
        // Load songs once again to reflect any potential changes
        reload();

        songs.add(song);

        // Save song list
        writeOut();
    }

    @Override
    public void delete(long id) {
        songs.removeIf(song -> song.getId() == id);
        writeOut();
    }

    @Override
    protected void writeOut() {
        List<String[]> serializedSongs = songs.stream()
                .map(Song::getFieldContents)
                .collect(Collectors.toList());
        csvHelper.write(Song.getCSVHeader(), serializedSongs);
    }

    @Override
    protected void reload() {
        songs.clear();

        // Load all songs
        Optional<List<String[]>> serializedSongs = csvHelper.read();
        serializedSongs.ifPresent(strings -> strings.forEach(serializedSong -> {
            // Get basic fields
            long songId = Long.parseLong(serializedSong[0]);
            String title = serializedSong[1];
            float bom = Float.parseFloat(serializedSong[3]);

            // Load transitive artists
            List<Artist> artists = Collections.emptyList();

            // Load transitive genre
            long genreId = Long.parseLong(serializedSong[2]);
            Optional<Genre> optionalGenre = genreRepository.findGenreById(genreId);
            Genre genre = optionalGenre.orElseThrow(() -> new TransitiveDataException("Genre not found"));

            // Load transitive bar type
            long barTypeId = Long.parseLong(serializedSong[4]);
            Optional<BarType> optionalBarType = barTypeRepository.findBarTypeById(barTypeId);
            BarType barType = optionalBarType.orElseThrow(() -> new TransitiveDataException("Bar type not found"));

            // Create song object
            songs.add(new Song(songId, title, artists, genre, bom, barType));
        }));
    }
}
