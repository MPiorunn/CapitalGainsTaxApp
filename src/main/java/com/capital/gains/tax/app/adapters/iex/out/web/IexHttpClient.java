package com.capital.gains.tax.app.adapters.iex.out.web;

public interface IexHttpClient {

    Dividend[] getLastYearDividendsForStock(String symbol);
}
