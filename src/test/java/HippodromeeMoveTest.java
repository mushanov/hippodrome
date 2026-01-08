import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

@DisplayName("Hippodrome move() method tests")
public class HippodromeeMoveTest {

    @Test
    @DisplayName("move calls move() on all horses")
    void testMoveCallsMovePonAll() {
        List<Horse> mockHorses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            mockHorses.add(mock(Horse.class));
        }

        Hippodrome hippodrome = new Hippodrome(mockHorses);
        hippodrome.move();

        for (Horse horse : mockHorses) {
            verify(horse, times(1)).move();
        }
    }

    @Test
    @DisplayName("move is called exactly once per horse")
    void testMoveCalledOncePerHorse() {
        List<Horse> mockHorses = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mockHorses.add(mock(Horse.class));
        }

        Hippodrome hippodrome = new Hippodrome(mockHorses);
        hippodrome.move();
        hippodrome.move();  // Call move twice

        for (Horse horse : mockHorses) {
            verify(horse, times(2)).move();
        }
    }

    @Test
    @DisplayName("move with different number of horses")
    void testMoveWithDifferentHorseCount() {
        for (int horseCount = 1; horseCount <= 10; horseCount++) {
            List<Horse> mockHorses = new ArrayList<>();
            for (int i = 0; i < horseCount; i++) {
                mockHorses.add(mock(Horse.class));
            }

            Hippodrome hippodrome = new Hippodrome(mockHorses);
            hippodrome.move();

            for (Horse horse : mockHorses) {
                verify(horse, times(1)).move();
            }
        }
    }
}