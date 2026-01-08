import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Hippodrome class tests")
public class HippodromeTest {

    // ==================== Constructor Tests ====================

    @Test
    @DisplayName("Constructor throws exception when horses list is null")
    void testConstructorHorsesNull() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    @DisplayName("Constructor exception message for null horses list")
    void testConstructorHorsesNullMessage() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    @DisplayName("Constructor throws exception when horses list is empty")
    void testConstructorHorsesEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    @DisplayName("Constructor exception message for empty horses list")
    void testConstructorHorsesEmptyMessage() {
        Exception exception = assertThrows(IllegalArgumentException.class,
                () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    // ==================== getHorses Tests ====================

    @Test
    @DisplayName("getHorses returns list with same horses in same order")
    void testGetHorsesOrderAndContent() {
        List<Horse> originalHorses = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            originalHorses.add(new Horse("Horse_" + i, 2.0 + i * 0.1, i));
        }

        Hippodrome hippodrome = new Hippodrome(originalHorses);
        List<Horse> returnedHorses = hippodrome.getHorses();

        assertEquals(30, returnedHorses.size());

        for (int i = 0; i < 30; i++) {
            assertSame(originalHorses.get(i), returnedHorses.get(i));
            assertEquals("Horse_" + i, returnedHorses.get(i).getName());
        }
    }

    @Test
    @DisplayName("getHorses returns unmodifiable list")
    void testGetHorsesUnmodifiable() {
        List<Horse> horses = new ArrayList<>();
        horses.add(new Horse("Test", 2.0));

        Hippodrome hippodrome = new Hippodrome(horses);
        List<Horse> returnedHorses = hippodrome.getHorses();

        assertThrows(UnsupportedOperationException.class,
                () -> returnedHorses.add(new Horse("New", 1.0)));
    }

    // ==================== getWinner Tests ====================

    @Test
    @DisplayName("getWinner returns horse with maximum distance")
    void testGetWinner() {
        List<Horse> horses = new ArrayList<>();
        horses.add(new Horse("Horse1", 2.0, 10.0));
        horses.add(new Horse("Horse2", 2.0, 50.0));
        horses.add(new Horse("Horse3", 2.0, 30.0));

        Hippodrome hippodrome = new Hippodrome(horses);
        Horse winner = hippodrome.getWinner();

        assertEquals("Horse2", winner.getName());
        assertEquals(50.0, winner.getDistance());
    }

    @Test
    @DisplayName("getWinner with single horse")
    void testGetWinnerSingleHorse() {
        List<Horse> horses = new ArrayList<>();
        horses.add(new Horse("OnlyHorse", 2.5, 100.0));

        Hippodrome hippodrome = new Hippodrome(horses);
        Horse winner = hippodrome.getWinner();

        assertEquals("OnlyHorse", winner.getName());
    }

    @Test
    @DisplayName("getWinner when multiple horses have same distance")
    void testGetWinnerSameDistance() {
        List<Horse> horses = new ArrayList<>();
        horses.add(new Horse("Horse1", 2.0, 50.0));
        horses.add(new Horse("Horse2", 2.0, 50.0));

        Hippodrome hippodrome = new Hippodrome(horses);
        Horse winner = hippodrome.getWinner();

        assertEquals(50.0, winner.getDistance());
        assertTrue(winner.getName().equals("Horse1") || winner.getName().equals("Horse2"));
    }
}