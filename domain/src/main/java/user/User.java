package user;

import java.util.List;

import song.Song;

public class User {

    private long id;
    private String username;
    private String password;
    private List<Song> songs;

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
}