package com.marc_auberer.musicmanager;

import com.marc_auberer.musicmanager.db.ArtistRepositoryImpl;
import com.marc_auberer.musicmanager.domain.artist.Artist;
import com.marc_auberer.musicmanager.domain.artist.ArtistRepository;
import com.marc_auberer.musicmanager.utils.CSVHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.marc_auberer.musicmanager.db.AbstractRepository.AUTO_INC;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class ArtistRepositoryTest {

    @Mock
    private CSVHelper csvHelper;

    @BeforeEach
    protected void prepareTest() {
        MockitoAnnotations.openMocks(this);

        // CSVHelper read()
        List<String[]> mockedReadResult = List.of(
                new String[]{"0", "Arch Enemy", "", "0"},
                new String[]{"1", "Amorphis", "", "0"},
                new String[]{"2", "Amaranthe", "", "0"}
        );
        when(csvHelper.read()).thenReturn(Optional.of(mockedReadResult));

        // CSVHelper write()
        doNothing().when(csvHelper).write(any(String[].class), any());
    }

    @Test
    protected void findArtistById() {
        // Test data
        ArtistRepository artistRepository = new ArtistRepositoryImpl(csvHelper);

        // Execute
        Optional<Artist> optionalArtist = artistRepository.findArtistById(2);

        // Verify
        verify(csvHelper, times(1)).read();

        // Assert
        assertTrue(optionalArtist.isPresent());
        Artist artist = optionalArtist.get();
        assertEquals("Amaranthe", artist.getFirstName());
    }

    @Test
    protected void findAllArtists() {
        // Test data
        ArtistRepository artistRepository = new ArtistRepositoryImpl(csvHelper);

        // Execute
        List<Artist> artists = artistRepository.findAllArtists();

        // Verify
        verify(csvHelper, times(1)).read();

        // Assert
        assertEquals(3, artists.size());
        assertEquals("Amorphis", artists.get(1).getFirstName());
    }

    @Test
    protected void save() {
        // Test data
        ArtistRepository artistRepository = new ArtistRepositoryImpl(csvHelper);
        Artist newArtist = new Artist(AUTO_INC, "Northlane", "", new Date(0));

        // Execute
        artistRepository.save(newArtist);

        // Verify
        verify(csvHelper, times(2)).read();
        verify(csvHelper, times(1)).write(any(), any());

        // Assert
        assertEquals(4, artistRepository.findAllArtists().size());
    }

    @Test
    protected void delete() {
        // Test data
        ArtistRepository artistRepository = new ArtistRepositoryImpl(csvHelper);

        // Execute
        artistRepository.delete(1);

        // Verify
        verify(csvHelper, times(1)).read();
        verify(csvHelper, times(1)).write(any(), any());

        // Assert
        assertEquals(2, artistRepository.findAllArtists().size());
    }
}
