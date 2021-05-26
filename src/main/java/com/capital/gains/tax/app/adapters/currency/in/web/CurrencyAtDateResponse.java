package com.capital.gains.tax.app.adapters.currency.in.web;

import java.time.LocalDate;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CurrencyAtDateResponse {

    private final Double price;
    private final LocalDate tradingDay;
    private final LocalDate previousTradingDay;

}
