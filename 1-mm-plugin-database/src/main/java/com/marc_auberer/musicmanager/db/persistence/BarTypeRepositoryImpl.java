package com.marc_auberer.musicmanager.db.persistence;

import com.marc_auberer.musicmanager.domain.bartype.BarType;
import com.marc_auberer.musicmanager.domain.bartype.BarTypeRepository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BarTypeRepositoryImpl implements BarTypeRepository {

    @Override
    public BarType findBarTypeById(long id) {
        String sql = "SELECT * FROM bar_types WHERE id = ?";
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
        String sql = "SELECT * FROM bar_types";
        try {
            // Setup connection
            Connection connection = Database.getConnection();
            assert connection != null;
            // Prepare statement
            Statement preparedStatement = connection.createStatement();
            // Execute statement
            ResultSet result = preparedStatement.executeQuery(sql);
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
        String sql = "SELECT * FROM bar_types WHERE songId = ?";
        try (Connection connection = Database.getConnection()) {
            // Check if connection is valid
            assert connection != null;
            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, songId);
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
        String sql = "INSERT INTO bar_types (id, beat_count, beat_value) VALUES (?, ?, ?)";
        try (Connection connection = Database.getConnection()) {
            // Check if connection is valid
            assert connection != null;
            // Prepare statement
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setLong(1, barType.getId());
            preparedStatement.setInt(2, barType.getBeatCount());
            preparedStatement.setInt(3, barType.getBeatValue());
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
        String sql = "DELETE FROM bar_types WHERE id = ?";
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
