package app.commons;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.DayOfWeek;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;


public class DateUtilsTest {

    @Test
    public void shouldReturnPreviousWeekdayProvided() {
        LocalDate someTuesday = LocalDate.of(2021, 9, 7);
        LocalDate tradingDay = DateUtils.getPreviousTradingDay(someTuesday);

        assertEquals(tradingDay.getDayOfWeek(), DayOfWeek.MONDAY);
    }

    @Test
    public void shouldSkipWeekdaysWhenMondayProvided() {
        LocalDate someMonday = LocalDate.of(2021, 9, 5);
        LocalDate tradingDay = DateUtils.getPreviousTradingDay(someMonday);

        assertEquals(tradingDay.getDayOfWeek(), DayOfWeek.FRIDAY);
    }

    @Test
    public void shouldSkipWeekdaysWhenSundayProvided() {
        LocalDate someMonday = LocalDate.of(2021, 9, 6);
        LocalDate tradingDay = DateUtils.getPreviousTradingDay(someMonday);

        assertEquals(tradingDay.getDayOfWeek(), DayOfWeek.FRIDAY);
    }

    @Test
    public void shouldSkipIndependenceDay() {
        LocalDate someMonday = LocalDate.of(2020, 11, 12);
        LocalDate tradingDay = DateUtils.getPreviousTradingDay(someMonday);

        assertEquals(tradingDay.getDayOfMonth(), 10);
    }
}
