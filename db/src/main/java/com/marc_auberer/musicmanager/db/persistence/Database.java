package com.marc_auberer.musicmanager.db.persistence;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class Database {

    public static final String TABLE_NAME_ARTIST = "artists";
    public static final String TABLE_NAME_BAR_TYPE = "bar_types";
    public static final String TABLE_NAME_GENRE = "genres";
    public static final String TABLE_NAME_SONG = "songs";
    public static final String TABLE_NAME_USER = "users";

    private static final String connectionUri = "jdbc:sqlite:musicmanager.db";

    public static Connection getConnection() {
        try {
            return DriverManager.getConnection(connectionUri);
        } catch (Exception e) {
            System.err.println("DB ERROR: " + e.getMessage());
        }
        return null;
    }

    public static void setupDatabase() {
        System.out.println("-- Setting up the local database ...");
        System.out.print("-- Connecting to the db ... ");
        getConnection();
        System.out.println("✓");

        System.out.print("-- Creating tables ... ");
        createTables();
        System.out.println("✓");

        System.out.print("-- Adding some data for demo purposes ... ");
        insertDemoData();
        System.out.println("✓");
        System.out.println("-- Setup done.");
    }

    private static void createTables() {
        try {
            Connection connection = getConnection();
            assert connection != null;

            // Create tables
            Statement stmt = connection.createStatement();
            String scriptPath = Objects.requireNonNull(Database.class.getClassLoader().getResource("create-tables.sql"))
                    .getFile().substring(1);
            scriptPath = URLDecoder.decode(scriptPath, StandardCharsets.UTF_8);
            List<String> sqlStatements = readStatementsFromSQLScript(scriptPath);
            assert sqlStatements != null;
            sqlStatements.forEach(s -> {
                try {
                    stmt.execute(s);
                } catch (SQLException e) {
                    System.err.println("DB ERROR: " + e.getMessage());
                    System.out.println("Statement: " + s);
                }
            });


        } catch (Exception e) {
            System.err.println("DB ERROR: " + e.getMessage());
        }
    }

    public static void insertDemoData() {
        // ToDo
    }

    private static List<String> readStatementsFromSQLScript(String path) {
        try {
            String content = Files.readString(Paths.get(path), StandardCharsets.US_ASCII);
            // Filter comments and empty lines
            List<String> lines = Arrays.stream(content.split("\n"))
                    .filter(line -> !line.startsWith("--") && line.length() > 0)
                    .collect(Collectors.toList());
            content = String.join("\n", lines);
            return Arrays.stream(content.split(";"))
                    .map(stmt -> stmt + ";")
                    .collect(Collectors.toList());
        } catch (IOException e) {
            System.out.println("DB ERROR: " + e);
        }
        return new ArrayList<>();
    }
}