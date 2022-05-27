package app.commons;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;


public class MathUtilsTest {

    @Test
    public void shouldRoundUpToTwoDecPlaces() {
        double roundedNumber = MathUtils.roundToTwoDecPlaces(4.3212);

        assertEquals(4.32, roundedNumber);
        assertNotEquals(4.3212, roundedNumber);
    }
}
