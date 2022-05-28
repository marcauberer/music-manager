package com.marc_auberer.musicmanager.domain.user;

import java.util.Objects;

public class User {

    private long id;
    private final String username;
    private final String password;

    public User(long id, String username, String password) {
        this.id = id;
        this.username = username;
        this.password = password;
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
        return Objects.hash(this.id);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        return ((User) obj).getId() == this.id;
    }
}