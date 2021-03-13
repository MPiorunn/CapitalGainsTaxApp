package com.capital.gains.tax.app.commons;

import java.time.DayOfWeek;
import java.time.LocalDate;
import lombok.experimental.UtilityClass;

@UtilityClass
public class DateUtils {


    public LocalDate calculatePreviousFriday(LocalDate localDate) {
        if (DayOfWeek.SUNDAY.equals(localDate.getDayOfWeek())) {
            return localDate.minusDays(2);
        }
        return localDate.minusDays(1);
    }
}
