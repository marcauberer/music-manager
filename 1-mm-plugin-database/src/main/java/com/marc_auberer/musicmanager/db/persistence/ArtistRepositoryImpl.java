package com.marc_auberer.musicmanager.db.persistence;

import com.marc_auberer.musicmanager.domain.artist.Artist;
import com.marc_auberer.musicmanager.domain.artist.ArtistRepository;
import com.marc_auberer.musicmanager.domain.bartype.BarType;
import com.marc_auberer.musicmanager.domain.exception.TransitiveDataException;
import com.marc_auberer.musicmanager.domain.genre.Genre;
import com.marc_auberer.musicmanager.domain.song.Song;
import com.marc_auberer.musicmanager.utils.CSVHelper;

import java.sql.*;
import java.time.Instant;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.OptionalInt;
import java.util.stream.Collectors;

public class ArtistRepositoryImpl implements ArtistRepository {

    private static final String FILE_PATH = "./data/artists.csv";
    private final CSVHelper csvHelper;
    private final List<Artist> artists = new ArrayList<>();

    public ArtistRepositoryImpl() {
        csvHelper = new CSVHelper(FILE_PATH, ";");
        // Pre-fetch all bar types at once
        reloadArtists();
    }

    @Override
    public Optional<Artist> findArtistById(long id) {
        return artists.stream().filter(artist -> artist.getId() == id).findFirst();
    }

    @Override
    public List<Artist> findAllArtists() {
        return artists;
    }

    @Override
    public List<Artist> findArtistsBySongId(long songId) {
        return Collections.emptyList();
    }

    @Override
    public void save(Artist artist) {
        // Load artists once again to reflect any potential changes
        reloadArtists();

        artists.add(artist);

        // Save artist list
        writeOut();
    }

    @Override
    public void delete(long id) {
        artists.removeIf(artist -> artist.getId() == id);
        writeOut();
    }

    private void writeOut() {
        List<String[]> serializedArtists = artists.stream()
                .map(Artist::getFieldContents)
                .collect(Collectors.toList());
        csvHelper.write(Artist.getCSVHeader(), serializedArtists);
    }

    private void reloadArtists() {
        artists.clear();

        // Load all artists
        Optional<List<String[]>> serializedArtists = csvHelper.read();
        serializedArtists.ifPresent(strings -> strings.forEach(serializedArtist -> {
            // Get basic fields
            long artistId = Long.parseLong(serializedArtist[0]);
            String firstName = serializedArtist[1];
            String lastName = serializedArtist[2];
            Date dateOfBirth = Date.from(Instant.ofEpochMilli(Long.parseLong(serializedArtist[3])));

            // Create artist object
            artists.add(new Artist(artistId, firstName, lastName, dateOfBirth));
        }));
    }
}
