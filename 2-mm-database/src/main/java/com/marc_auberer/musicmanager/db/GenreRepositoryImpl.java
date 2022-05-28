package com.marc_auberer.musicmanager.db;

import com.marc_auberer.musicmanager.domain.genre.Genre;
import com.marc_auberer.musicmanager.domain.genre.GenreRepository;
import com.marc_auberer.musicmanager.utils.CSVHelper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class GenreRepositoryImpl extends AbstractRepository implements GenreRepository {

    private static final String FILE_PATH = "./data/genres.csv";
    private final CSVHelper csvHelper;
    private final List<Genre> genres = new ArrayList<>();

    public GenreRepositoryImpl() {
        this(new CSVHelper(FILE_PATH, ";"));
    }
    public GenreRepositoryImpl(CSVHelper csvHelper) {
        this.csvHelper = csvHelper;
        // Pre-fetch all genres at once
        reload();
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
        reload();

        // Consider auto-increment ids
        if (genre.getId() == AUTO_INC) {
            genre.setId(genres.size());
        }

        // Save
        genres.add(genre);

        // Save genres list
        writeOut();
    }

    @Override
    public void delete(long id) {
        genres.removeIf(genre -> genre.getId() == id);
        writeOut();
    }

    @Override
    protected void writeOut() {
        List<String[]> serializedGenres = genres.stream()
                .map(Genre::getFieldContents)
                .collect(Collectors.toList());
        csvHelper.write(Genre.getCSVHeader(), serializedGenres);
    }

    @Override
    protected void reload() {
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
