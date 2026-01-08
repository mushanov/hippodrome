import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@DisplayName("Horse move() method tests with mocks")
public class HorseMoveTest {

    @Test
    @DisplayName("move calls getRandomDouble with correct parameters")
    void testMoveCallsGetRandomDouble() {
        try (MockedStatic<Horse> mockedHorse = mockStatic(Horse.class)) {
            mockedHorse.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);

            Horse horse = new Horse("Test", 2.0, 0);
            horse.move();

            mockedHorse.verify(() -> Horse.getRandomDouble(0.2, 0.9), times(1));
        }
    }

    @Test
    @DisplayName("move calculates distance correctly with mocked random")
    void testMoveDistanceCalculation() {
        try (MockedStatic<Horse> mockedHorse = mockStatic(Horse.class)) {
            mockedHorse.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);

            Horse horse = new Horse("Test", 2.0, 10.0);
            horse.move();

            // distance = 10.0 + 2.0 * 0.5 = 11.0
            assertEquals(11.0, horse.getDistance());
        }
    }

    @Test
    @DisplayName("move calculates distance correctly with different random values")
    void testMoveDistanceCalculationVariousValues() {
        double[][] testCases = {
                {2.0, 10.0, 0.2, 10.4},      // 10 + 2 * 0.2 = 10.4
                {3.0, 5.0, 0.5, 6.5},        // 5 + 3 * 0.5 = 6.5
                {1.5, 20.0, 0.9, 21.35},     // 20 + 1.5 * 0.9 = 21.35
                {4.0, 0.0, 0.3, 1.2}         // 0 + 4 * 0.3 = 1.2
        };

        for (double[] testCase : testCases) {
            double speed = testCase[0];
            double initialDistance = testCase[1];
            double randomValue = testCase[2];
            double expectedDistance = testCase[3];

            try (MockedStatic<Horse> mockedHorse = mockStatic(Horse.class)) {
                mockedHorse.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(randomValue);

                Horse horse = new Horse("Test", speed, initialDistance);
                horse.move();

                assertEquals(expectedDistance, horse.getDistance(), 0.001);
            }
        }
    }
}