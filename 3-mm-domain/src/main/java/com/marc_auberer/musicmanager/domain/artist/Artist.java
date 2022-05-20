package com.marc_auberer.musicmanager.domain.artist;

import java.util.Date;

public class Artist {

    private final long id;
    private final String firstName;
    private final String lastName;
    private final Date dateOfBirth;

    public Artist(long id, String firstName, String lastName, Date dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }
}