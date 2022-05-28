package com.marc_auberer.musicmanager;

import com.marc_auberer.musicmanager.domain.artist.Artist;
import com.marc_auberer.musicmanager.domain.artist.ArtistRepository;
import com.marc_auberer.musicmanager.domain.relation.RelSongArtist;
import com.marc_auberer.musicmanager.domain.relation.RelSongArtistRepository;
import com.marc_auberer.musicmanager.plugin.repository.RelSongArtistRepositoryImpl;
import com.marc_auberer.musicmanager.utils.CSVHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class RelSongArtistRepositoryTest {

    @Mock
    private CSVHelper csvHelper;
    @Mock
    private ArtistRepository artistRepository;

    @BeforeEach
    protected void prepareTest() {
        MockitoAnnotations.openMocks(this);

        // CSVHelper read()
        List<String[]> mockedReadResult = List.of(
                new String[]{"0", "0", "1"},
                new String[]{"1", "1", "2"},
                new String[]{"2", "1", "3"},
                new String[]{"2", "1", "4"}
        );
        when(csvHelper.read()).thenReturn(Optional.of(mockedReadResult));

        // CSVHelper write()
        doNothing().when(csvHelper).write(any(String[].class), any());

        // ArtistRepository findArtistById()
        List<Optional<Artist>> mockData = List.of(
                Optional.of(new Artist(2, "Kamelot", "", new Date(0))),
                Optional.of(new Artist(3, "Alissa", "White-Gluz", new Date(491608800000L))),
                Optional.of(new Artist(4, "Elize", "Ryd", new Date(466642800000L)))
        );
        when(artistRepository.findArtistById(2)).thenReturn(mockData.get(0));
        when(artistRepository.findArtistById(3)).thenReturn(mockData.get(1));
        when(artistRepository.findArtistById(4)).thenReturn(mockData.get(2));
    }

    @Test
    protected void findArtistsBySongId() {
        // Test data
        RelSongArtistRepository songArtistRelRepository = new RelSongArtistRepositoryImpl(artistRepository, csvHelper);

        // Execute
        List<Artist> artists = songArtistRelRepository.findAllArtistsBySongId(1);

        // Verify
        verify(csvHelper, times(1)).read();
        verify(artistRepository, times(1)).findArtistById(2);
        verify(artistRepository, times(1)).findArtistById(3);
        verify(artistRepository, times(1)).findArtistById(4);

        // Assert
        assertEquals(3, artists.size());
        assertEquals("Kamelot", artists.get(0).getFirstName());
        assertEquals("Alissa", artists.get(1).getFirstName());
        assertEquals("Elize", artists.get(2).getFirstName());
    }

    @Test
    protected void updateRelations() {
        // Test data
        RelSongArtistRepository songArtistRelRepository = new RelSongArtistRepositoryImpl(artistRepository, csvHelper);
        List<RelSongArtist> newRelations = List.of(
                new RelSongArtist(0, 0, 1),
                new RelSongArtist(1, 1, 2),
                new RelSongArtist(2, 1, 1)
        );

        // Execute
        songArtistRelRepository.updateRelations(newRelations);

        // Verify
        verify(csvHelper, times(1)).read();
        verify(csvHelper, times(1)).write(any(), any());

        // Assert
        assertEquals(3, newRelations.size());
    }
}
