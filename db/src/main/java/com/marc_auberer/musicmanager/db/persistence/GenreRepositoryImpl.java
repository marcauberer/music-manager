package com.marc_auberer.musicmanager.db.persistence;

import bartype.BarType;
import genre.Genre;
import genre.GenreRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.marc_auberer.musicmanager.db.persistence.Database.*;

public class GenreRepositoryImpl implements GenreRepository {

    @Override
    public Genre findGenreById(long id) {
        String stmt = "SELECT * FROM ? WHERE id = ?";
        try {
            // Setup connection
            Connection connection = getConnection();
            assert connection != null;
            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(stmt);
            preparedStatement.setString(1, TABLE_NAME_GENRE);
            preparedStatement.setLong(2, id);
            // Execute statement
            ResultSet result = preparedStatement.executeQuery();
            if (!result.next()) return null;
            // Materialize result data
            int genreId = result.getInt("id");
            String genreName = result.getString("name");
            return new Genre(genreId, genreName);
        } catch (SQLException e) {
            System.err.println("DB ERROR: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<Genre> findAllGenres() {
        String stmt = "SELECT * FROM ?";
        try {
            // Setup connection
            Connection connection = getConnection();
            assert connection != null;
            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(stmt);
            preparedStatement.setString(1, TABLE_NAME_GENRE);
            // Execute statement
            ResultSet result = preparedStatement.executeQuery();
            // Materialize result data
            List<Genre> genres = new ArrayList<>();
            while (result.next()) {
                int genreId = result.getInt("id");
                String genreName = result.getString("name");
                genres.add(new Genre(genreId, genreName));
            }
            return genres;
        } catch (SQLException e) {
            System.err.println("DB ERROR: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Genre findGenreBySongId(long songId) {
        String stmt = "SELECT * FROM ? WHERE songId = ?";
        try {
            // Setup connection
            Connection connection = getConnection();
            assert connection != null;
            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(stmt);
            preparedStatement.setString(1, TABLE_NAME_GENRE);
            preparedStatement.setLong(2, songId);
            // Execute statement
            ResultSet result = preparedStatement.executeQuery();
            if (!result.next()) return null;
            // Materialize result data
            int genreId = result.getInt("id");
            String genreName = result.getString("name");
            return new Genre(genreId, genreName);
        } catch (SQLException e) {
            System.err.println("DB ERROR: " + e.getMessage());
        }
        return null;
    }

    @Override
    public Genre save(Genre genre) {
        // Check if the artist exists already
        if (findGenreById(genre.getId()) != null) return null;

        // Insert the new record
        String stmt = "INSERT INTO ? (id, name) VALUES (?, ?)";
        try {
            // Setup connection
            Connection connection = getConnection();
            assert connection != null;
            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(stmt);
            preparedStatement.setString(1, TABLE_NAME_GENRE);
            preparedStatement.setLong(2, genre.getId());
            preparedStatement.setString(3, genre.getName());
            // Execute statement
            preparedStatement.executeUpdate();
            return genre;
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
            preparedStatement.setString(1, TABLE_NAME_GENRE);
            preparedStatement.setLong(2, id);
            // Execute statement
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("DB ERROR: " + e.getMessage());
        }
    }
}
