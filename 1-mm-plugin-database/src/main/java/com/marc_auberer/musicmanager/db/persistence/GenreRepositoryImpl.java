package com.marc_auberer.musicmanager.db.persistence;

import com.marc_auberer.musicmanager.domain.artist.Artist;
import com.marc_auberer.musicmanager.domain.bartype.BarType;
import com.marc_auberer.musicmanager.domain.exception.TransitiveDataException;
import com.marc_auberer.musicmanager.domain.genre.Genre;
import com.marc_auberer.musicmanager.domain.genre.GenreRepository;
import com.marc_auberer.musicmanager.domain.song.Song;
import com.marc_auberer.musicmanager.domain.user.User;
import com.marc_auberer.musicmanager.utils.CSVHelper;

import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GenreRepositoryImpl implements GenreRepository {

    private static final String FILE_PATH = "./data/genres.csv";
    private final CSVHelper csvHelper;
    private final List<Genre> genres = new ArrayList<>();

    public GenreRepositoryImpl() {
        csvHelper = new CSVHelper(FILE_PATH, ";");
        // Pre-fetch all genres at once
        reloadGenres();
    }

    @Override
    public Optional<Genre> findGenreById(long id) {
        return genres.stream()
                .filter(genre -> genre.getId() == id)
                .findFirst();
    }

    @Override
    public List<Genre> findAllGenres() {
        return genres;
    }

    @Override
    public void save(Genre genre) {
        // Load genres once again to reflect any potential changes
        reloadGenres();

        genres.add(genre);

        // Save genres list
        writeOut();
    }

    @Override
    public void delete(long id) {
        genres.removeIf(genre -> genre.getId() == id);
        writeOut();
    }

    private void writeOut() {
        List<String[]> serializedGenres = genres.stream()
                .map(Genre::getFieldContents)
                .collect(Collectors.toList());
        csvHelper.write(Genre.getCSVHeader(), serializedGenres);
    }

    private void reloadGenres() {
        genres.clear();

        // Load all genres
        Optional<List<String[]>> serializedGenres = csvHelper.read();
        serializedGenres.ifPresent(strings -> strings.forEach(serializedGenre -> {
            // Get basic fields
            long genreId = Long.parseLong(serializedGenre[0]);
            String name = serializedGenre[1];

            // Create genre object
            genres.add(new Genre(genreId, name));
        }));
    }
}
