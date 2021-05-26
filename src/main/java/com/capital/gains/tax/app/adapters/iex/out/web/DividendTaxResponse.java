package com.capital.gains.tax.app.adapters.iex.out.web;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter(value = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class DividendTaxResponse {

    private final String stock;
    private final double stocksAmount;
    private final double totalDividend;
    private final double withholdingTax;
    private final double income;
    private final double remainingFourPercent;
    private final int amountOfDividends;
    private final Dividend[] dividends;


}
