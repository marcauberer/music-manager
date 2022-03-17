package com.marc_auberer.musicmanager.db.persistence;

import com.marc_auberer.musicmanager.domain.genre.Genre;
import com.marc_auberer.musicmanager.domain.genre.GenreRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GenreRepositoryImpl implements GenreRepository {

    @Override
    public Genre findGenreById(long id) {
        String sql = "SELECT * FROM genres WHERE id = ?";
        try {
            // Setup connection
            Connection connection = Database.getConnection();
            assert connection != null;
            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, id);
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
        String sql = "SELECT * FROM genres";
        try {
            // Setup connection
            Connection connection = Database.getConnection();
            assert connection != null;
            // Prepare statement
            Statement statement = connection.createStatement();
            // Execute statement
            ResultSet result = statement.executeQuery(sql);
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
        String stmt = "SELECT * FROM genres WHERE songId = ?";
        try {
            // Setup connection
            Connection connection = Database.getConnection();
            assert connection != null;
            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(stmt);
            preparedStatement.setLong(1, songId);
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
        String sql = "INSERT INTO genres (id, name) VALUES (?, ?)";
        try {
            // Setup connection
            Connection connection = Database.getConnection();
            assert connection != null;
            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, genre.getId());
            preparedStatement.setString(2, genre.getName());
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
        String sql = "DELETE FROM genres WHERE id = ?";
        try {
            // Setup connection
            Connection connection = Database.getConnection();
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
