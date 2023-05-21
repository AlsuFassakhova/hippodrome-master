
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.*;

class HorseTest {
    Horse horse = new Horse("Igo-go", 5.0, 10.0);

    @Test
    @DisplayName("NullNameException")
    void testThrowsExceptionWhenNullInFirstParameter() {
        assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, 1.0));

    }

    @Test
    @DisplayName("NullNameExceptionText")
    void testThrowsTextExceptionWhenNullInFirstParameter() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(null, 1.0));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {" ", "   ", "\n"})
    @DisplayName("WhitespaceNameException")
    void testThrowsExceptionWhenFirstIsWhitespaceCharacter(String argument) {
        assertThrows(IllegalArgumentException.class,
                () -> new Horse(argument, 1.0));
    }

    @ParameterizedTest
    @DisplayName("WhitespaceNameExceptionText")
    @ValueSource(strings = {" ", "   ", "\n"})
    void testThrowsTextExceptionWhenFirstIsWhitespaceCharacter(String argument) {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse(argument, 1.0));
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    @DisplayName("NegativeSpeedException")
    void testThrowsExceptionWhenSecondParameterIsNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> new Horse("Berry", -1.0));
    }

    @Test
    @DisplayName("NegativeSpeedExceptionText")
    void testThrowsTextExceptionWhenSecondParameterIsNegative() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse("Berry", -1.0));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }

    @Test
    @DisplayName("NegativeDistanceException")
    void testThrowsExceptionWhenThirdParameterIsNegative() {
        assertThrows(IllegalArgumentException.class,
                () -> new Horse("Berry", 1.0, -1.0));
    }

    @Test
    @DisplayName("NegativeDistanceExceptionText")
    void testThrowsTextExceptionWhenThirdParameterIsNegative() {
        Throwable exception = assertThrows(IllegalArgumentException.class,
                () -> new Horse("Berry", 1.0, -1.0));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }


    @Test
    @DisplayName("GetNameTest")
    void getNameTest() {
        assertEquals("Igo-go", horse.getName());
    }

    @Test
    @DisplayName("GetSpeedTest")
    void getSpeedTest() {
        assertEquals(5.0, horse.getSpeed());
    }

    @Test
    @DisplayName("GetDistanceTest")
    void getDistanceTest() {
        assertEquals(10.0, horse.getDistance());
    }

    @Test
    @DisplayName("GetRandomDoubleTest")
    void moveCallGetRandomDoubleMethodTest() {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horse.move();
            horseMockedStatic.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }
    }

    @ParameterizedTest
    @CsvSource({"Sunshine, 2.0, 10.0, 0.5, 11.0",
            "Lady, 3.0, 5.0, 0.2, 5.6"})
    @DisplayName("MoveCalculationTest")
    void moveCalculationTest(String name, double speed, double distance, double random, double expected) {
        try (MockedStatic<Horse> horseMockedStatic = Mockito.mockStatic(Horse.class)) {
            horseMockedStatic.when(() -> Horse.getRandomDouble(0.2, 0.9))
                    .thenReturn(random);
            Horse horse1 = new Horse(name, speed, distance);
            horse1.move();
            assertEquals(expected, horse1.getDistance());
        }
    }
}