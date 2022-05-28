package com.marc_auberer.musicmanager;

import com.marc_auberer.musicmanager.domain.artist.Artist;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class ArtistTest {

    @Test
    protected void getFieldContents() {
        // Test data
        Date dob = new GregorianCalendar(1985, Calendar.AUGUST, 25).getTime();
        Artist testArtist = new Artist(12, "Amy", "McDonald", dob);

        // Execute
        String[] actualResult = testArtist.getFieldContents();

        // Assert
        String[] expectedResult = new String[]{"12", "Amy", "McDonald", String.valueOf(dob.getTime())};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    protected void getCSVHeader() {
        // Execute
        String[] actualResult = Artist.getCSVHeader();

        // Assert
        String[] expectedResult = new String[]{"Id", "FirstName", "LastName", "DateOfBirth"};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    protected void toStringFirstAndLastName() {
        // Test data
        Date dob = new GregorianCalendar(1950, Calendar.DECEMBER, 30).getTime();
        Artist testArtist = new Artist(0, "Bjarne", "Stroustrup", dob);

        // Execute
        String actualResult = testArtist.toString();

        // Assert
        assertEquals("Bjarne Stroustrup", actualResult);
    }

    @Test
    protected void toStringOnlyFirstName() {
        // Test data
        Artist testArtist = new Artist(11, "Arch Enemy", "", new Date(0));

        // Execute
        String actualResult = testArtist.toString();

        // Assert
        assertEquals("Arch Enemy", actualResult);
    }
}
