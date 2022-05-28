package com.marc_auberer.musicmanager;

import com.marc_auberer.musicmanager.domain.relation.RelSongArtist;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class RelSongArtistTest {

    @Test
    protected void getFieldContents() {
        // Test data
        RelSongArtist testRel = new RelSongArtist(5, 7, 4);

        // Execute
        String[] actualResult = testRel.getFieldContents();

        // Assert
        String[] expectedResult = new String[]{"5", "7", "4"};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    protected void getCSVHeader() {
        // Execute
        String[] actualResult = RelSongArtist.getCSVHeader();

        // Assert
        String[] expectedResult = new String[]{"Id", "SongId", "ArtistId"};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    protected void toStringNormal() {
        // Test data
        RelSongArtist testRel = new RelSongArtist(9, 5, 124);

        // Execute
        String actualResult = testRel.toString();

        // Assert
        assertEquals("{9,5,124}", actualResult);
    }
}
