package com.marc_auberer.musicmanager;

import com.marc_auberer.musicmanager.db.BarTypeRepositoryImpl;
import com.marc_auberer.musicmanager.domain.bartype.BarType;
import com.marc_auberer.musicmanager.domain.bartype.BarTypeRepository;
import com.marc_auberer.musicmanager.utils.CSVHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static com.marc_auberer.musicmanager.db.Repository.AUTO_INC;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class BarTypeRepositoryTest {

    @Mock
    private CSVHelper csvHelper;

    @BeforeEach
    protected void prepareTest() {
        MockitoAnnotations.openMocks(this);

        // CSVHelper read()
        List<String[]> mockedReadResult = List.of(
                new String[]{"0", "3", "4"},
                new String[]{"1", "15", "16"},
                new String[]{"2", "4", "4"}
        );
        when(csvHelper.read()).thenReturn(Optional.of(mockedReadResult));

        // CSVHelper write()
        doNothing().when(csvHelper).write(any(String[].class), any());
    }

    @Test
    protected void findBarTypeById() {
        // Test data
        BarTypeRepository barTypeRepository = new BarTypeRepositoryImpl(csvHelper);

        // Execute
        Optional<com.marc_auberer.musicmanager.domain.bartype.BarType> optionalBarType = barTypeRepository.findBarTypeById(2);

        // Verify
        verify(csvHelper, times(1)).read();

        // Assert
        assertTrue(optionalBarType.isPresent());
        BarType barType = optionalBarType.get();
        assertEquals(4, barType.getBeatCount());
        assertEquals(4, barType.getBeatValue());
    }

    @Test
    protected void findAllBarTypes() {
        // Test data
        BarTypeRepository barTypeRepository = new BarTypeRepositoryImpl(csvHelper);

        // Execute
        List<BarType> barTypes = barTypeRepository.findAllBarTypes();

        // Verify
        verify(csvHelper, times(1)).read();

        // Assert
        assertEquals(3, barTypes.size());
        assertEquals(15, barTypes.get(1).getBeatCount());
        assertEquals(16, barTypes.get(1).getBeatValue());
    }

    @Test
    protected void save() {
        // Test data
        BarTypeRepository barTypeRepository = new BarTypeRepositoryImpl(csvHelper);
        BarType newBarType = new BarType(AUTO_INC, 5, 6);

        // Execute
        barTypeRepository.save(newBarType);

        // Verify
        verify(csvHelper, times(2)).read();
        verify(csvHelper, times(1)).write(any(), any());

        // Assert
        assertEquals(4, barTypeRepository.findAllBarTypes().size());
    }

    @Test
    protected void delete() {
        // Test data
        BarTypeRepository barTypeRepository = new BarTypeRepositoryImpl(csvHelper);

        // Execute
        barTypeRepository.delete(1);

        // Verify
        verify(csvHelper, times(1)).read();
        verify(csvHelper, times(1)).write(any(), any());

        // Assert
        assertEquals(2, barTypeRepository.findAllBarTypes().size());
    }
}
