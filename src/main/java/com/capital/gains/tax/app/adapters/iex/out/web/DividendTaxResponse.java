package com.capital.gains.tax.app.adapters.iex.out.web;

import lombok.Data;

@Data
public class DividendTaxResponse {

    private final String stock;
    private final double stocksAmount;
    private final double totalDividend;
    private final double withholdingTax;
    private final double income;
    private final double remainingFourPercent;
    private final int amountOfDividends;

}
