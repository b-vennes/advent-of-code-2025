package cafe.branden;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}