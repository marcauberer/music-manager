package com.marc_auberer.musicmanager;

import com.marc_auberer.musicmanager.domain.user.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class UserTest {

    @Test
    protected void getFieldContents() {
        // Test data
        User testUser = new User(5, "this is my username", "most secure password");

        // Execute
        String[] actualResult = testUser.getFieldContents();

        // Assert
        String[] expectedResult = new String[]{"5", "this is my username", "most secure password"};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    protected void getCSVHeader() {
        // Execute
        String[] actualResult = User.getCSVHeader();

        // Assert
        String[] expectedResult = new String[]{"Id", "Username", "Password"};
        assertArrayEquals(expectedResult, actualResult);
    }

    @Test
    protected void toStringNormal() {
        // Test data
        User testUser = new User(7, "marc", "12345");

        // Execute
        String actualResult = testUser.toString();

        // Assert
        assertEquals("marc", actualResult);
    }
}
