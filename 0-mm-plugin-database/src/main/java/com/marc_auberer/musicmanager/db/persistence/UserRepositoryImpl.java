package com.marc_auberer.musicmanager.db.persistence;

import com.marc_auberer.musicmanager.domain.song.Song;
import com.marc_auberer.musicmanager.domain.user.User;
import com.marc_auberer.musicmanager.domain.user.UserRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private final SongRepositoryImpl songRepository;

    public UserRepositoryImpl() {
        songRepository = new SongRepositoryImpl();
    }

    @Override
    public List<User> findAllUsers() {
        String sql = "SELECT * FROM users";
        try (Connection connection = Database.getConnection()) {
            // Check if connection is valid
            assert connection != null;
            // Prepare statement
            Statement statement = connection.createStatement();
            // Execute statement
            ResultSet result = statement.executeQuery(sql);
            // Materialize result data
            List<User> user = new ArrayList<>();
            while (result.next()) {
                int userId = result.getInt("id");
                String userName = result.getString("username");
                String userPassword = result.getString("password");
                List<Song> userSongs = songRepository.findAllSongsByUserId(userId);
                user.add(new User(userId, userName, userPassword, userSongs));
            }
            return user;
        } catch (SQLException e) {
            System.err.println("DB ERROR: " + e.getMessage());
        }
        return null;
    }

    @Override
    public User findUserByUsername(String username) {
        String sql = "SELECT * FROM users WHERE username = ?";
        try (Connection connection = Database.getConnection()) {
            // Check if connection is valid
            assert connection != null;
            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, username);
            // Execute statement
            ResultSet result = preparedStatement.executeQuery();
            if (!result.next()) return null;
            // Materialize result data
            int userId = result.getInt("id");
            String userName = result.getString("username");
            String userPassword = result.getString("password");
            List<Song> userSongs = songRepository.findAllSongsByUserId(userId);
            return new User(userId, userName, userPassword, userSongs);
        } catch (SQLException e) {
            System.err.println("DB ERROR in findUserByUsername: " + e.getMessage());
        }
        return null;
    }

    @Override
    public User save(User user) {
        // Check if the artist exists already
        if (findUserByUsername(user.getUsername()) != null) return null;

        // Insert the new record
        String sql = "INSERT INTO users (id, username, password) VALUES (?, ?, ?)";
        try (Connection connection = Database.getConnection()) {
            // Check if connection is valid
            assert connection != null;
            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, user.getId());
            preparedStatement.setString(2, user.getUsername());
            preparedStatement.setString(3, user.getPassword());

            // Execute statement
            preparedStatement.executeUpdate();
            return user;
        } catch (SQLException e) {
            System.err.println("DB ERROR: " + e.getMessage());
        }
        return null;
    }
}
