package com.capital.gains.tax.app.adapters.nbp.out.web;

import java.time.LocalDate;

public interface NbpCurrencyService {

    Double getCurrencyAtTradingDay(LocalDate localDate);
}
