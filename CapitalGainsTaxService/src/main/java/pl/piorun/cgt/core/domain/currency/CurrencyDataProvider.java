package pl.piorun.cgt.core.domain.currency;

import java.time.LocalDate;

public interface CurrencyDataProvider {

    Double getDollarPriceFromPreviousTradingDay(LocalDate date);
}
