package com.marc_auberer.musicmanager.db;

import com.marc_auberer.musicmanager.db.persistence.*;

public class Application {
    public static void main(String[] args) {
        // Initialize application
        Database.setupDatabase();
        UserRepositoryImpl userRepository = new UserRepositoryImpl();

        //
    }
}