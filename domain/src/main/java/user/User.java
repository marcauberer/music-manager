package user;

import java.util.List;

import song.Song;

public class User {

    private String username;
    private String password;
    private List<Song> songs;

    public User(String username, String password, List<Song> songs) {
        this.username = username;
        this.password = password;
        this.songs = songs;
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
}