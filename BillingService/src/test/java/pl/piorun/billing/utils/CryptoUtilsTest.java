package pl.piorun.billing.utils;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

import org.junit.jupiter.api.Test;

public class CryptoUtilsTest {

    @Test
    void shouldReturnEqualOutputsOnTheSameInput() {
        String input = "some txt";
        String input2 = "some txt";
        assertEquals(CryptoUtils.hash(input), CryptoUtils.hash(input2));
    }

    @Test
    void shouldReturnDifferentOutputsOnDifferentInputs() {
        String input = "some txt";
        String input2 = "some txt2";
        assertNotEquals(CryptoUtils.hash(input), CryptoUtils.hash(input2));
    }
}
