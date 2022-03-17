package com.marc_auberer.musicmanager.db.persistence;

import song.Song;
import user.User;
import user.UserRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepositoryImpl implements UserRepository {

    private final SongRepositoryImpl songRepository;

    public UserRepositoryImpl() {
        songRepository = new SongRepositoryImpl();
    }

    @Override
    public List<User> findAllUsers() {
        String stmt = "SELECT * FROM ?";
        try {
            // Setup connection
            Connection connection = Database.getConnection();
            assert connection != null;
            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(stmt);
            preparedStatement.setString(1, Database.TABLE_NAME_USER);
            // Execute statement
            ResultSet result = preparedStatement.executeQuery();
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
        String stmt = "SELECT * FROM ? WHERE username = ?";
        try {
            // Setup connection
            Connection connection = Database.getConnection();
            assert connection != null;
            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(stmt);
            preparedStatement.setString(1, Database.TABLE_NAME_USER);
            preparedStatement.setString(2, username);
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
            System.err.println("DB ERROR: " + e.getMessage());
        }
        return null;
    }

    @Override
    public User save(User user) {
        // Check if the artist exists already
        if (findUserByUsername(user.getUsername()) != null) return null;

        // Insert the new record
        String stmt = "INSERT INTO ? (id, username, password) VALUES (?, ?, ?)";
        try {
            // Setup connection
            Connection connection = Database.getConnection();
            assert connection != null;
            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(stmt);
            preparedStatement.setString(1, Database.TABLE_NAME_USER);
            preparedStatement.setLong(2, user.getId());
            preparedStatement.setString(3, user.getUsername());
            preparedStatement.setString(4, user.getPassword());
            // Execute statement
            preparedStatement.executeUpdate();
            return user;
        } catch (SQLException e) {
            System.err.println("DB ERROR: " + e.getMessage());
        }
        return null;
    }
}
