package com.marc_auberer.musicmanager.domain.artist;

import java.util.Date;

public class Artist {

    private long id;
    private final String firstName;
    private final String lastName;
    private final Date dateOfBirth;

    public Artist(long id, String firstName, String lastName, Date dateOfBirth) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public void setId(long id) {
        this.id = id;
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

    public String[] getFieldContents() {
        return new String[] {String.valueOf(id), firstName, lastName, String.valueOf(dateOfBirth.getTime())};
    }

    public static String[] getCSVHeader() {
        return new String[] {"Id", "FirstName", "LastName", "DateOfBirth"};
    }

    @Override
    public String toString() {
        return lastName.isEmpty() ? firstName : firstName + " " + lastName;
    }

    @Override
    public int hashCode() {
        return (int) this.id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null || this.getClass() != obj.getClass()) {
            return false;
        }
        return ((Artist) obj).getId() == this.id;
    }
}