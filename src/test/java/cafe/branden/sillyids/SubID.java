package cafe.branden.sillyids;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class SubIDTest {
    @Test
    void addToEmpty() {
        SubID id = SubID.fromString("");

        char newCharacter = 'a';

        SubID expected = SubID.fromString("a");

        SubID result = id.add(newCharacter);

        assertEquals(expected, result);
    }

    @Test
    void addToNonEmpty() {
        SubID id = SubID.fromString("hello");

        char newCharacter = '!';

        SubID expected = SubID.fromString("hello!");

        SubID result = id.add(newCharacter);

        assertEquals(expected, result);
    }
}
