package cafe.branden.sillyids;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import org.junit.jupiter.api.Test;

class ProductIDTest {

    @Test
    void isSilly11YieldsTrue() {
        ProductID id = new ProductID(11L);

        assertTrue(id.isSilly());
    }

    @Test
    void isSilly100YieldsTrue() {
        ProductID id = new ProductID(100L);

        assertTrue(id.isSilly());
    }

    @Test
    void isSilly123YieldsFalse() {
        ProductID id = new ProductID(123L);

        assertFalse(id.isSilly());
    }

    @Test
    void isSilly9123412349YieldsTrue() {
        ProductID id = new ProductID(9123412349L);

        assertTrue(id.isSilly());
    }

    @Test
    void toStateModifierEmptyTrackingState() {
        ProductID.TrackingState in = new ProductID.Tracking(List.of());

        char adding = '1';

        ProductID.TrackingState expected =
                new ProductID.Tracking(List.of(new Subtracking(new SubID(List.of('1')), new SubID(List.of()))));

        ProductID.TrackingState result = ProductID.toStateModifier(adding).apply(in);

        assertEquals(expected, result);
    }

    @Test
    void toStateModifierNonEmptyTrackingState() {
        char adding = '4';
        ProductID.TrackingState in = new ProductID.Tracking(List.of(
                new Subtracking(new SubID(List.of('1', '2')), SubID.empty()),
                new Subtracking(new SubID(List.of('1', '3')), new SubID(List.of('2')))));

        ProductID.Tracking expected = new ProductID.Tracking(List.of());

        ProductID.TrackingState result = ProductID.toStateModifier(adding).apply(in);

        assertEquals(expected, result);
    }
}
