import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.times;

class HippodromeTest {

    List<Horse> horseList = new ArrayList<>();


    @Test
    @DisplayName("HippodromeConstructorNullTest")
    void hippodromeConstructorTestWhenListNull() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
    }

    @Test
    @DisplayName("HippodromeConstructorNullTextTest")
    void hippodromeConstructorTestWhenListIsNullText() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }

    @Test
    @DisplayName("HippodromeConstructorEmptyTest")
    void hippodromeConstructorTestWhenListIsEmpty() {
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
    }

    @Test
    @DisplayName("HippodromeConstructorEmptyTextTest")
    void hippodromeConstructorTestWhenListIsEmptyText() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }

    @Test
    @DisplayName("getHorsesTest")
    void getHorsesTest() {
        for (int i = 0; i < 30; i++) {
            horseList.add(new Horse(String.valueOf(i), i));
        }
        Hippodrome hippodrome = new Hippodrome(horseList);
        assertEquals(horseList, hippodrome.getHorses());
    }

    @Test
    @DisplayName("hippodromeMoveTest")
    void moveMethodForAllHorsesTest() {
        Horse horseMock = Mockito.mock(Horse.class);
        for (int i = 0; i < 50; i++) {
            horseList.add(horseMock);
        }
        Hippodrome hippodrome = new Hippodrome(horseList);
        hippodrome.move();
        Mockito.verify(horseMock, times(50)).move();
    }

    @Test
    @DisplayName("getWinnerTest")
    void getWinnerReturnCorrectHorse() {
        for (int i = 0; i < 5; i++) {
            horseList.add(new Horse(String.valueOf(i), i, i));
        }
        Hippodrome hippodrome = new Hippodrome(horseList);
        assertEquals(4.0, hippodrome.getWinner().getDistance());
    }
}