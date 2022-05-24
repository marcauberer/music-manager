package com.marc_auberer.musicmanager.domain.user;

import java.util.ArrayList;
import java.util.List;

import com.marc_auberer.musicmanager.domain.song.Song;

public class User {

    private final long id;
    private final String username;
    private final String password;
    private final List<Song> songs;

    public User(long id, String username, String password, List<Song> songs) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.songs = songs;
    }

    public long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<Song> getSongs() {
        return songs;
    }

    public String[] getFieldContents() {
        return new String[] {String.valueOf(id), username, password};
    }

    public static String[] getCSVHeader() {
        return new String[] {"Id", "Username", "Password"};
    }
}