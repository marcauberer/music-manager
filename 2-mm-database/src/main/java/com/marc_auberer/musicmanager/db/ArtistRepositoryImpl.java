package com.marc_auberer.musicmanager.db;

import com.marc_auberer.musicmanager.domain.artist.Artist;
import com.marc_auberer.musicmanager.domain.artist.ArtistRepository;
import com.marc_auberer.musicmanager.utils.CSVHelper;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class ArtistRepositoryImpl extends AbstractRepository implements ArtistRepository {

    private static final String FILE_PATH = "./data/artists.csv";
    private final CSVHelper csvHelper;
    private final List<Artist> artists = new ArrayList<>();

    public ArtistRepositoryImpl() {
        this(new CSVHelper(FILE_PATH, ";"));
    }

    public ArtistRepositoryImpl(CSVHelper csvHelper) {
        this.csvHelper = csvHelper;
        // Pre-fetch all bar types at once
        reload();
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
    public void save(Artist artist) {
        // Load artists once again to reflect any potential changes
        reload();

        // Consider auto-increment ids
        if (artist.getId() == AUTO_INC) {
            artist.setId(artists.size());
        }

        // Save
        artists.add(artist);

        // Save artist list
        writeOut();
    }

    @Override
    public void delete(long id) {
        artists.removeIf(artist -> artist.getId() == id);
        writeOut();
    }

    protected void writeOut() {
        List<String[]> serializedArtists = artists.stream()
                .map(Artist::getFieldContents)
                .collect(Collectors.toList());
        csvHelper.write(Artist.getCSVHeader(), serializedArtists);
    }

    @Override
    protected void reload() {
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
