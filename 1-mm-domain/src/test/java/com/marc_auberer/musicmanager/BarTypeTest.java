package com.marc_auberer.musicmanager;

import com.marc_auberer.musicmanager.domain.bartype.BarType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BarTypeTest {

    @Test
    protected void getFieldContents() {
        // Test data
        BarType testBarType = new BarType(3, 5, 4);

        // Execute
        String[] actualResult = testBarType.getFieldContents();

        // Assert
        String[] expectedResult = new String[]{"3", "5", "4"};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    protected void getCSVHeader() {
        // Execute
        String[] actualResult = BarType.getCSVHeader();

        // Assert
        String[] expectedResult = new String[]{"Id", "BeatCount", "BeatValue"};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    protected void toStringNormal() {
        // Test data
        BarType testBarType = new BarType(99, 17, 8);

        // Execute
        String actualResult = testBarType.toString();

        // Assert
        assertEquals("17/8", actualResult);
    }
}
