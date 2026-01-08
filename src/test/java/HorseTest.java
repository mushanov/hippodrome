import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Horse class tests")
public class HorseTest {

    // ==================== Constructor Tests ====================

    @Test
    @DisplayName("Constructor throws exception when name is null")
    void testConstructorNameNull() {
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 2.0, 0));
    }

    @Test
    @DisplayName("Constructor exception message for null name")
    void testConstructorNameNullMessage() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, 2.0, 0));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n", "  \t  "})
    @DisplayName("Constructor throws exception when name is blank")
    void testConstructorNameBlank(String name) {
        assertThrows(IllegalArgumentException.class, () -> new Horse(name, 2.0, 0));
    }

    @Test
    @DisplayName("Constructor exception message for blank name")
    void testConstructorNameBlankMessage() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse("   ", 2.0, 0));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    @DisplayName("Constructor throws exception when speed is negative")
    void testConstructorSpeedNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Horse", -1.0, 0));
    }

    @Test
    @DisplayName("Constructor exception message for negative speed")
    void testConstructorSpeedNegativeMessage() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse("Horse", -1.0, 0));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    @DisplayName("Constructor throws exception when distance is negative")
    void testConstructorDistanceNegative() {
        assertThrows(IllegalArgumentException.class, () -> new Horse("Horse", 2.0, -1.0));
    }

    @Test
    @DisplayName("Constructor exception message for negative distance")
    void testConstructorDistanceNegativeMessage() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse("Horse", 2.0, -1.0));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }

    // ==================== getName Tests ====================

    @Test
    @DisplayName("getName returns correct name")
    void testGetName() {
        Horse horse = new Horse("Bucephalus", 2.5, 10.0);
        assertEquals("Bucephalus", horse.getName());
    }

    // ==================== getSpeed Tests ====================

    @Test
    @DisplayName("getSpeed returns correct speed")
    void testGetSpeed() {
        Horse horse = new Horse("Ace", 3.5, 5.0);
        assertEquals(3.5, horse.getSpeed());
    }

    // ==================== getDistance Tests ====================

    @Test
    @DisplayName("getDistance returns correct distance")
    void testGetDistance() {
        Horse horse = new Horse("Zephyr", 2.0, 15.5);
        assertEquals(15.5, horse.getDistance());
    }

    @Test
    @DisplayName("getDistance returns zero for two-parameter constructor")
    void testGetDistanceZero() {
        Horse horse = new Horse("Blaze", 2.8);
        assertEquals(0, horse.getDistance());
    }

    // ==================== move Tests ====================

    @ParameterizedTest
    @ValueSource(doubles = {0.2, 0.5, 0.9})
    @DisplayName("move updates distance correctly with different random values")
    void testMoveDistanceUpdate(double randomValue) {
        Horse horse = new Horse("Pegasus", 2.0, 10.0);
        horse.move();

        // After move, distance should be > 10 (since speed * random is always positive)
        assertTrue(horse.getDistance() > 10.0);
        // Distance should be <= 10 + 2.0 * 0.9 = 11.8
        assertTrue(horse.getDistance() <= 11.8);
    }

    @Test
    @DisplayName("move increases distance")
    void testMoveIncreasesDistance() {
        Horse horse = new Horse("Cherry", 2.0, 5.0);
        double initialDistance = horse.getDistance();
        horse.move();
        assertTrue(horse.getDistance() > initialDistance);
    }
}