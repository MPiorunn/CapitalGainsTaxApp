package com.capital.gains.tax.app.adapters.nbp.out.web;

import java.time.LocalDate;

public interface NbpCurrencyClient {

    Double getCurrencyFromDate(LocalDate localDate);
}
