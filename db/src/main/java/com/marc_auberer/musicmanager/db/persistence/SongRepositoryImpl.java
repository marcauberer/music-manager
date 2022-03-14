package com.marc_auberer.musicmanager.db.persistence;

import artist.Artist;
import bartype.BarType;
import genre.Genre;
import song.Song;
import song.SongRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.marc_auberer.musicmanager.db.persistence.Database.TABLE_NAME_SONG;
import static com.marc_auberer.musicmanager.db.persistence.Database.getConnection;

public class SongRepositoryImpl implements SongRepository {

    private final ArtistRepositoryImpl artistRepository;
    private final GenreRepositoryImpl genreRepository;
    private final BarTypeRepositoryImpl barTypeRepository;

    public SongRepositoryImpl() {
        artistRepository = new ArtistRepositoryImpl();
        genreRepository = new GenreRepositoryImpl();
        barTypeRepository = new BarTypeRepositoryImpl();
    }

    @Override
    public Song findSongById(long id) {
        String stmt = "SELECT * FROM ? WHERE id = ?";
        try {
            // Setup connection
            Connection connection = getConnection();
            assert connection != null;
            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(stmt);
            preparedStatement.setString(1, TABLE_NAME_SONG);
            preparedStatement.setLong(2, id);
            // Execute statement
            ResultSet result = preparedStatement.executeQuery();
            if (!result.next()) return null;
            // Materialize result data
            int songId = result.getInt("id");
            String songTitle = result.getString("title");
            float songBpm = result.getFloat("bpm");
            List<Artist> songArtists = artistRepository.findAllArtistsBySongId(songId);
            Genre songGenre = genreRepository.findGenreBySongId(songId);
            BarType songBarType = barTypeRepository.findBarTypeBySongId(songId);
            return new Song(songId, songTitle, songArtists, songGenre, songBpm, songBarType);
        } catch (SQLException e) {
            System.err.println("DB ERROR: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Song> findAllSongs() {
        String stmt = "SELECT * FROM ?";
        try {
            // Setup connection
            Connection connection = getConnection();
            assert connection != null;
            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(stmt);
            preparedStatement.setString(1, TABLE_NAME_SONG);
            // Execute statement
            ResultSet result = preparedStatement.executeQuery();
            // Materialize result data
            List<Song> songs = new ArrayList<>();
            while (result.next()) {
                int songId = result.getInt("id");
                String songTitle = result.getString("title");
                float songBpm = result.getFloat("bpm");
                List<Artist> songArtists = artistRepository.findAllArtistsBySongId(songId);
                Genre songGenre = genreRepository.findGenreBySongId(songId);
                BarType songBarType = barTypeRepository.findBarTypeBySongId(songId);
                songs.add(new Song(songId, songTitle, songArtists, songGenre, songBpm, songBarType));
            }
            return songs;
        } catch (SQLException e) {
            System.err.println("DB ERROR: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Song> findAllSongsByUserId(long userId) {
        String stmt = "SELECT * FROM ? WHERE userId = ?";
        try {
            // Setup connection
            Connection connection = getConnection();
            assert connection != null;
            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(stmt);
            preparedStatement.setString(1, TABLE_NAME_SONG);
            preparedStatement.setLong(2, userId);
            // Execute statement
            ResultSet result = preparedStatement.executeQuery();
            // Materialize result data
            List<Song> songs = new ArrayList<>();
            while (result.next()) {
                int songId = result.getInt("id");
                String songTitle = result.getString("title");
                float songBpm = result.getFloat("bpm");
                List<Artist> songArtists = artistRepository.findAllArtistsBySongId(songId);
                Genre songGenre = genreRepository.findGenreBySongId(songId);
                BarType songBarType = barTypeRepository.findBarTypeBySongId(songId);
                songs.add(new Song(songId, songTitle, songArtists, songGenre, songBpm, songBarType));
            }
            return songs;
        } catch (SQLException e) {
            System.err.println("DB ERROR: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Song save(Song song) {
        // Check if the artist exists already
        if (findSongById(song.getId()) != null) return null;

        // Save artists
        for (Artist artist : song.getArtists()) {
            artistRepository.save(artist);
        }
        // Save genre
        // ToDo
        // Save bar type
        // ToDo

        // Insert the new record
        String stmt = "INSERT INTO ? (id, title, bpm) VALUES (?, ?, ?)";
        try {
            // Setup connection
            Connection connection = getConnection();
            assert connection != null;
            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(stmt);
            preparedStatement.setString(1, TABLE_NAME_SONG);
            preparedStatement.setLong(2, song.getId());
            preparedStatement.setString(3, song.getTitle());
            preparedStatement.setFloat(4, song.getBpm());
            // Execute statement
            preparedStatement.executeUpdate();
            return song;
        } catch (SQLException e) {
            System.err.println("DB ERROR: " + e.getMessage());
        }
        return null;
    }

    @Override
    public void delete(long id) {
        String stmt = "DELETE FROM ? WHERE id = ?";
        try {
            // Setup connection
            Connection connection = getConnection();
            assert connection != null;
            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(stmt);
            preparedStatement.setString(1, TABLE_NAME_SONG);
            preparedStatement.setLong(2, id);
            // Execute statement
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("DB ERROR: " + e.getMessage());
        }
    }
}
