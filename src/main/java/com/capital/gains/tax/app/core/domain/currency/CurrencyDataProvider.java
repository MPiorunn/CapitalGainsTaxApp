package com.capital.gains.tax.app.core.domain.currency;

import java.time.LocalDate;

public interface CurrencyDataProvider {

    Double getDollarPriceFromPreviousTradingDay(LocalDate date);
}
