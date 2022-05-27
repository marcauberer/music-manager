package com.marc_auberer.musicmanager.domain.user;

import com.marc_auberer.musicmanager.domain.artist.Artist;
import com.marc_auberer.musicmanager.domain.song.Song;

import java.util.List;

public class User {

    private long id;
    private final String username;
    private final String password;
    private final List<Song> songs;

    public User(long id, String username, String password, List<Song> songs) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.songs = songs;
    }

    public void setId(long id) {
        this.id = id;
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

    @Override
    public String toString() {
        return username;
    }

    @Override
    public int hashCode() {
        return (int) this.id;
    }

    @Override
    public boolean equals(Object obj) {
        return ((User) obj).getId() == this.id;
    }
}