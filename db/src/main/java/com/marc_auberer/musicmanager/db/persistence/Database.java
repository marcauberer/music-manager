package com.marc_auberer.musicmanager.db.persistence;

import java.sql.*;

public class Database {

    public static final String TABLE_NAME_ARTIST = "artist";
    public static final String TABLE_NAME_BAR_TYPES = "bar_types";
    public static final String TABLE_NAME_GENRE = "genre";
    public static final String TABLE_NAME_SONG = "song";
    public static final String TABLE_NAME_USER = "user";

    private static final String connectionUri = "jdbc:sqlite:./musicmanager.db";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(connectionUri);
        } catch (SQLException e) {
            System.err.println("DB ERROR: " + e.getMessage());
        }
        return null;
    }

    public static void setupDatabase() {
        System.out.println("-- Setting up the local database ...");
        System.out.print("-- Connecting to the db ... ");
        getConnection();
        System.out.println("✓");

        System.out.println("-- Creating tables ... ");
        createTablesIfNotExisting();
        System.out.println("✓");

        System.out.println("-- Adding some data for demo purposes ... ");
        insertDemoData();
        System.out.println("✓");
        System.out.println("-- Setup done.");
    }

    private static void createTablesIfNotExisting() {
        try {
            Connection connection = getConnection();
            assert connection != null;
            Statement stmt = connection.createStatement();
        } catch (Exception e) {
            System.err.println("DB ERROR: " + e.getMessage());
        }
    }

    public static void insertDemoData() {
        // ToDo
    }
}