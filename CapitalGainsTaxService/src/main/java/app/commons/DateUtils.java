package app.commons;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Month;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DateUtils {

    public LocalDate getPreviousTradingDay(LocalDate localDate) {
        if (willBeIndependenceDay(localDate)) {
            return localDate.minusDays(2);
        }
        var dayOfWeek = localDate.getDayOfWeek();
        switch (dayOfWeek) {
            case MONDAY:
                return localDate.minusDays(3);
            case SUNDAY:
                return localDate.minusDays(2);
            default:
                return localDate.minusDays(1);
        }
    }

    private boolean willBeIndependenceDay(LocalDate localDate) {
        return localDate.getMonth().equals(Month.NOVEMBER) && localDate.getDayOfMonth() == 12;
    }
}
