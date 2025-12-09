package cafe.branden;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class DialTest {

    @Test
    void rotateLeftWithNoOverflow() {
        Dial dial = new Dial(50, 100);
        int rotation = 20;
        Dial expected = new Dial(30, 100);

        Dial result = dial.rotateLeft(rotation);

        assertEquals(expected, result);
    }

    @Test
    void rotateLeftWithOverflow() {
        Dial dial = new Dial(50, 100);
        int rotation = 75;
        Dial expected = new Dial(75, 100);

        Dial result = dial.rotateLeft(rotation);

        assertEquals(expected, result);
    }

    @Test
    void rotateLeftWithLargeNumber() {
        Dial dial = Dial.initial();
        int rotation = 190;
        Dial expected = new Dial(60, 100);

        Dial result = dial.rotateLeft(rotation);

        assertEquals(expected, result);
    }

    @Test
    void rotateRightWithNoOverflow() {
        Dial dial = new Dial(50, 100);
        int rotation = 40;
        Dial expected = new Dial(90, 100);

        Dial result = dial.rotateRight(rotation);

        assertEquals(expected, result);
    }

    @Test
    void rotateRightWithOverflow() {
        Dial dial = new Dial(50, 100);
        int rotation = 60;
        Dial expected = new Dial(10, 100);

        Dial result = dial.rotateRight(rotation);

        assertEquals(expected, result);
    }

    @Test
    void rotateLeftCountingZeroes() {
        Dial dial = Dial.initial();
        int rotation = 170;
        Dial.CountingZeroes expected = new Dial.CountingZeroes(new Dial(80, 100), 2);

        Dial.CountingZeroes result = dial.rotateLeftCountingZeroes(rotation);

        assertEquals(expected, result);
    }

    @Test
    void rotateRightCountingZeroes() {
        Dial dial = Dial.initial();
        int rotation = 260;
        Dial.CountingZeroes expected = new Dial.CountingZeroes(new Dial(10, 100), 3);

        Dial.CountingZeroes result = dial.rotateRightCountingZeroes(rotation);

        assertEquals(expected, result);
    }

    @Test
    void rotateLeftCountingZeroesLandOnZero() {
        Dial dial = Dial.initial();
        int rotation = 250;
        Dial.CountingZeroes expected = new Dial.CountingZeroes(new Dial(0, 100), 3);

        Dial.CountingZeroes result = dial.rotateLeftCountingZeroes(rotation);

        assertEquals(expected, result);
    }

    @Test
    void rotateRightCountingZeroesLandOnZero() {
        Dial dial = Dial.initial();
        int rotation = 150;
        Dial.CountingZeroes expected = new Dial.CountingZeroes(new Dial(0, 100), 2);

        Dial.CountingZeroes result = dial.rotateRightCountingZeroes(rotation);

        assertEquals(expected, result);
    }

    @Test
    void rotateRightCountingZeroesAmount1000() {
        Dial dial = Dial.initial();
        int rotation = 1000;
        Dial.CountingZeroes expected = new Dial.CountingZeroes(new Dial(50, 100), 10);

        Dial.CountingZeroes result = dial.rotateRightCountingZeroes(rotation);

        assertEquals(expected, result);
    }

    @Test
    void rotateRightCountingZeroesStartingAtZeroAmount100() {
        Dial dial = new Dial(0, 100);
        int rotation = 100;
        Dial.CountingZeroes expected = new Dial.CountingZeroes(new Dial(0, 100), 1);

        Dial.CountingZeroes result = dial.rotateRightCountingZeroes(rotation);

        assertEquals(expected, result);
    }

    @Test
    void rotateLeftCountingZeroesStartingAtZeroAmount5() {
        Dial dial = new Dial(0, 100);
        int rotation = 5;
        Dial.CountingZeroes expected = new Dial.CountingZeroes(new Dial(95, 100), 0);

        Dial.CountingZeroes result = dial.rotateLeftCountingZeroes(rotation);

        assertEquals(expected, result);
    }
}
