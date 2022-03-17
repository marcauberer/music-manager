package com.marc_auberer.musicmanager.db.persistence;

import bartype.BarType;
import bartype.BarTypeRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BarTypeRepositoryImpl implements BarTypeRepository {

    @Override
    public BarType findBarTypeById(long id) {
        String stmt = "SELECT * FROM ? WHERE id = ?";
        try {
            // Setup connection
            Connection connection = Database.getConnection();
            assert connection != null;
            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(stmt);
            preparedStatement.setString(1, Database.TABLE_NAME_BAR_TYPE);
            preparedStatement.setLong(2, id);
            // Execute statement
            ResultSet result = preparedStatement.executeQuery();
            if (!result.next()) return null;
            // Materialize result data
            int barTypeId = result.getInt("id");
            int barTypeBeatCount = result.getInt("beat_count");
            int barTypeBeatValue = result.getInt("beat_value");
            return new BarType(barTypeId, barTypeBeatCount, barTypeBeatValue);
        } catch (SQLException e) {
            System.err.println("DB ERROR: " + e.getMessage());
        }
        return null;
    }

    @Override
    public List<BarType> findAllBarTypes() {
        String stmt = "SELECT * FROM ?";
        try {
            // Setup connection
            Connection connection = Database.getConnection();
            assert connection != null;
            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(stmt);
            preparedStatement.setString(1, Database.TABLE_NAME_BAR_TYPE);
            // Execute statement
            ResultSet result = preparedStatement.executeQuery();
            // Materialize result data
            List<BarType> barTypes = new ArrayList<>();
            while (result.next()) {
                int barTypeId = result.getInt("id");
                int barTypeBeatCount = result.getInt("beat_count");
                int barTypeBeatValue = result.getInt("beat_value");
                barTypes.add(new BarType(barTypeId, barTypeBeatCount, barTypeBeatValue));
            }
            return barTypes;
        } catch (SQLException e) {
            System.err.println("DB ERROR: " + e.getMessage());
        }
        return null;
    }

    @Override
    public BarType findBarTypeBySongId(long songId) {
        String stmt = "SELECT * FROM ? WHERE songId = ?";
        try {
            // Setup connection
            Connection connection = Database.getConnection();
            assert connection != null;
            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(stmt);
            preparedStatement.setString(1, Database.TABLE_NAME_BAR_TYPE);
            preparedStatement.setLong(2, songId);
            // Execute statement
            ResultSet result = preparedStatement.executeQuery();
            if (!result.next()) return null;
            // Materialize result data
            int barTypeId = result.getInt("id");
            int barTypeBeatCount = result.getInt("beat_count");
            int barTypeBeatValue = result.getInt("beat_value");
            return new BarType(barTypeId, barTypeBeatCount, barTypeBeatValue);
        } catch (SQLException e) {
            System.err.println("DB ERROR: " + e.getMessage());
        }
        return null;
    }

    @Override
    public BarType save(BarType barType) {
        // Check if the artist exists already
        if (findBarTypeById(barType.getId()) != null) return null;

        // Insert the new record
        String stmt = "INSERT INTO ? (id, beat_count, beat_value) VALUES (?, ?, ?)";
        try {
            // Setup connection
            Connection connection = Database.getConnection();
            assert connection != null;
            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(stmt);
            preparedStatement.setString(1, Database.TABLE_NAME_ARTIST);
            preparedStatement.setLong(2, barType.getId());
            preparedStatement.setInt(3, barType.getBeatCount());
            preparedStatement.setInt(4, barType.getBeatValue());
            // Execute statement
            preparedStatement.executeUpdate();
            return barType;
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
            Connection connection = Database.getConnection();
            assert connection != null;
            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(stmt);
            preparedStatement.setString(1, Database.TABLE_NAME_BAR_TYPE);
            preparedStatement.setLong(2, id);
            // Execute statement
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("DB ERROR: " + e.getMessage());
        }
    }
}
