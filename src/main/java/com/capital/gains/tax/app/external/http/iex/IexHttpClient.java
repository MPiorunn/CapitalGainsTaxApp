package com.capital.gains.tax.app.external.http.iex;

public interface IexHttpClient {

    DividendDto[] getLastYearDividendsForStock(String symbol);
}
