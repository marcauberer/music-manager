package com.marc_auberer.musicmanager.db.persistence;

import com.marc_auberer.musicmanager.domain.artist.Artist;
import com.marc_auberer.musicmanager.domain.artist.ArtistRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ArtistRepositoryImpl implements ArtistRepository {

    @Override
    public Artist findArtistById(long id) {
        String sql = "SELECT * FROM artists WHERE id = ?";
        try (Connection connection = Database.getConnection()) {
            // Check if connection is valid
            assert connection != null;
            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
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
        String sql = "SELECT * FROM artists";
        try (Connection connection = Database.getConnection()) {
            // Check if connection is valid
            assert connection != null;
            // Prepare statement
            Statement statement = connection.createStatement();
            // Execute statement
            ResultSet result = statement.executeQuery(sql);
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
        String sql = "SELECT * FROM artists WHERE songId = ?";
        try (Connection connection = Database.getConnection()) {
            // Check if connection is valid
            assert connection != null;
            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, songId);
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
        String sql = "INSERT INTO artists (id, first_name, last_name, date_of_birth) VALUES (?, ?, ?, ?)";
        try (Connection connection = Database.getConnection()) {
            // Check if connection is valid
            assert connection != null;
            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, artist.getId());
            preparedStatement.setString(2, artist.getFirstName());
            preparedStatement.setString(3, artist.getLastName());
            preparedStatement.setDate(4, new java.sql.Date(artist.getDateOfBirth().getTime()));
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
        String sql = "DELETE FROM artists WHERE id = ?";
        try (Connection connection = Database.getConnection()) {
            // Check if connection is valid
            assert connection != null;
            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
            // Execute statement
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("DB ERROR: " + e.getMessage());
        }
    }
}
