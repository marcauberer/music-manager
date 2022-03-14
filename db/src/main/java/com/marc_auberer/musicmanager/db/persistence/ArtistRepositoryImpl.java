package com.marc_auberer.musicmanager.db.persistence;

import artist.Artist;
import artist.ArtistRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.marc_auberer.musicmanager.db.persistence.Database.TABLE_NAME_ARTIST;
import static com.marc_auberer.musicmanager.db.persistence.Database.getConnection;

public class ArtistRepositoryImpl implements ArtistRepository {

    @Override
    public Artist findArtistById(long id) {
        String stmt = "SELECT * FROM ? WHERE id = ?";
        try {
            // Setup connection
            Connection connection = getConnection();
            assert connection != null;
            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(stmt);
            preparedStatement.setString(1, TABLE_NAME_ARTIST);
            preparedStatement.setLong(2, id);
            // Execute statement
            ResultSet result = preparedStatement.executeQuery();
            if (!result.next()) return null;
            // Materialize result data
            int artistId = result.getInt("id");
            String artistFirstName = result.getString("first_name");
            String artistLastName = result.getString("last_name");
            Date artistDateOfBirth = result.getDate("date_of_birth");
            return new Artist(artistId, artistFirstName, artistLastName, artistDateOfBirth);
        } catch (SQLException e) {
            System.err.println("DB ERROR: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Artist> findAllArtists() {
        String stmt = "SELECT * FROM ?";
        try {
            // Setup connection
            Connection connection = getConnection();
            assert connection != null;
            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(stmt);
            preparedStatement.setString(1, TABLE_NAME_ARTIST);
            // Execute statement
            ResultSet result = preparedStatement.executeQuery();
            // Materialize result data
            List<Artist> artists = new ArrayList<>();
            while (result.next()) {
                int artistId = result.getInt("id");
                String artistFirstName = result.getString("first_name");
                String artistLastName = result.getString("last_name");
                Date artistDateOfBirth = result.getDate("date_of_birth");
                artists.add(new Artist(artistId, artistFirstName, artistLastName, artistDateOfBirth));
            }
            return artists;
        } catch (SQLException e) {
            System.err.println("DB ERROR: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Artist> findAllArtistsBySongId(long songId) {
        String stmt = "SELECT * FROM ? WHERE songId = ?";
        try {
            // Setup connection
            Connection connection = getConnection();
            assert connection != null;
            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(stmt);
            preparedStatement.setString(1, TABLE_NAME_ARTIST);
            preparedStatement.setLong(2, songId);
            // Execute statement
            ResultSet result = preparedStatement.executeQuery();
            // Materialize result data
            List<Artist> artists = new ArrayList<>();
            while (result.next()) {
                int artistId = result.getInt("id");
                String artistFirstName = result.getString("first_name");
                String artistLastName = result.getString("last_name");
                Date artistDateOfBirth = result.getDate("date_of_birth");
                artists.add(new Artist(artistId, artistFirstName, artistLastName, artistDateOfBirth));
            }
            return artists;
        } catch (SQLException e) {
            System.err.println("DB ERROR: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Artist save(Artist artist) {
        // Check if the artist exists already
        if (findArtistById(artist.getId()) != null) return null;

        // Insert the new record
        String stmt = "INSERT INTO ? (id, first_name, last_name, date_of_birth) VALUES (?, ?, ?, ?)";
        try {
            // Setup connection
            Connection connection = getConnection();
            assert connection != null;
            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(stmt);
            preparedStatement.setString(1, TABLE_NAME_ARTIST);
            preparedStatement.setLong(2, artist.getId());
            preparedStatement.setString(3, artist.getFirstName());
            preparedStatement.setString(4, artist.getLastName());
            preparedStatement.setDate(5, new java.sql.Date(artist.getDateOfBirth().getTime()));
            // Execute statement
            preparedStatement.executeUpdate();
            return artist;
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
            preparedStatement.setString(1, TABLE_NAME_ARTIST);
            preparedStatement.setLong(2, id);
            // Execute statement
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("DB ERROR: " + e.getMessage());
        }
    }
}
