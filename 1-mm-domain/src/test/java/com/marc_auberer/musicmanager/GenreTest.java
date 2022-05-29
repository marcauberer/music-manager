package com.marc_auberer.musicmanager;

import com.marc_auberer.musicmanager.domain.genre.Genre;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GenreTest {

    @Test
    void getFieldContents() {
        // Test data
        Genre testGenre = new Genre(42, "Power Metal");

        // Execute
        String[] actualResult = testGenre.getFieldContents();

        // Assert
        String[] expectedResult = new String[]{"42", "Power Metal"};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    void getCSVHeader() {
        // Execute
        String[] actualResult = Genre.getCSVHeader();

        // Assert
        String[] expectedResult = new String[]{"Id", "Name"};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    void toStringNormal() {
        // Test data
        Genre testGenre = new Genre(6, "Speed Metal");

        // Execute
        String actualResult = testGenre.toString();

        // Assert
        assertEquals("Speed Metal", actualResult);
    }
}
