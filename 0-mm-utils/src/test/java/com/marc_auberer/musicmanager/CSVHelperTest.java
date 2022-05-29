package com.marc_auberer.musicmanager;

import com.marc_auberer.musicmanager.domain.artist.Artist;
import com.marc_auberer.musicmanager.domain.genre.Genre;
import com.marc_auberer.musicmanager.domain.song.Song;
import com.marc_auberer.musicmanager.utils.CSVHelper;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;

class CSVHelperTest {

    // Constants
    private final String TEST_FILE_1 = "../.github/test-files/read1.csv";
    private final String TEST_FILE_2 = "../.github/test-files/read2.csv";
    private final String TEST_FILE_3 = "../.github/test-files/write1.csv";
    private final String TEST_FILE_4 = "../.github/test-files/write2.csv";

    @Test
    void read1() {
        // Test data
        CSVHelper csvHelper = new CSVHelper(TEST_FILE_1, ";");

        // Execute
        Optional<List<String[]>> actualResult = csvHelper.read();

        // Assert
        assertTrue(actualResult.isPresent());
        assertEquals(4, actualResult.get().size());
        List<String[]> expectedResult = List.of(
                new String[]{"0", "Cheese Burger", "1200", "9.5"},
                new String[]{"1", "Spaghetti", "450", "8.2"},
                new String[]{"2", "Schnitzel", "950", "8.9"},
                new String[]{"3", "Caesar Salad", "340", "7.0"}
        );
        assertArrayEquals(expectedResult.get(0), actualResult.get().get(0));
        assertArrayEquals(expectedResult.get(1), actualResult.get().get(1));
        assertArrayEquals(expectedResult.get(2), actualResult.get().get(2));
        assertArrayEquals(expectedResult.get(3), actualResult.get().get(3));
    }

    @Test
    void read2() {
        // Test data
        CSVHelper csvHelper = new CSVHelper(TEST_FILE_2, ",");

        // Execute
        Optional<List<String[]>> actualResult = csvHelper.read();

        // Assert
        assertTrue(actualResult.isPresent());
        assertTrue(actualResult.get().isEmpty());
    }

    @Test
    void write1() {
        // Test data
        CSVHelper csvHelper = new CSVHelper(TEST_FILE_3, ";");
        String[] header = Song.getCSVHeader();
        List<Artist> artists = List.of(new Artist(1, "Beast In Black", "", new Date(0)));
        Genre genre = new Genre(12, "Metal");
        com.marc_auberer.musicmanager.domain.bartype.BarType barType = new com.marc_auberer.musicmanager.domain.bartype.BarType(5, 4, 4);
        List<Song> songs = List.of(
                new Song(0, 0, "One Night in Tokio", artists, genre, 128, barType),
                new Song(1, 0, "Moonlight Rendezvous", artists, genre, 102, barType)
        );
        List<String[]> data = songs.stream().map(Song::getFieldContents).collect(Collectors.toList());

        // Execute
        csvHelper.write(header, data);

        // Assert
        try {
            String actualFileContents = Files.readString(Path.of(TEST_FILE_3)).replaceAll("\r\n", "\n");
            String expectedFileContents =
                    "Id;UserId;Title;GenreId;Bpm;BarTypeId\n" +
                    "0;0;One Night in Tokio;12;128.0;5\n" +
                    "1;0;Moonlight Rendezvous;12;102.0;5";
            assertEquals(expectedFileContents, actualFileContents);
        } catch (IOException e) {
            fail();
        }
    }

    @Test
    void write2() {
        // Test data
        CSVHelper csvHelper = new CSVHelper(TEST_FILE_4, ",");
        String[] header = Song.getCSVHeader();
        List<Artist> artists = List.of(
                new Artist(0, "Amaranthe", "", new Date(0)),
                new Artist(1, "Beast In Black", "", new Date(0)),
                new Artist(2, "Halocene", "", new Date(0)),
                new Artist(3, "Matt", "McGuire", new Date(0))
        );
        List<String[]> data = artists.stream().map(Artist::getFieldContents).collect(Collectors.toList());

        // Execute
        csvHelper.write(header, data);

        // Assert
        try {
            String actualFileContents = Files.readString(Path.of(TEST_FILE_4)).replaceAll("\r\n", "\n");
            String expectedFileContents = "Id,UserId,Title,GenreId,Bpm,BarTypeId\n" +
                    "0,Amaranthe,,0\n" +
                    "1,Beast In Black,,0\n" +
                    "2,Halocene,,0\n" +
                    "3,Matt,McGuire,0";
            assertEquals(expectedFileContents, actualFileContents);
        } catch (IOException e) {
            fail();
        } finally {
            File testFile = new File(TEST_FILE_4);
            testFile.deleteOnExit();
        }
    }
}
