package app.commons;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class MathUtils {

    public static double roundToTwoDecPlaces(double number) {
        return Math.round(100 * number) / 100.00;
    }
}
