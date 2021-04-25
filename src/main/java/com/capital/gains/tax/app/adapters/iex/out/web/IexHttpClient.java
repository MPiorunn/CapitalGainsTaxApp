package com.capital.gains.tax.app.adapters.iex.out.web;

import java.util.List;

public interface IexHttpClient {

    List<Dividend> getLastYearDividendsForStock(String symbol);
}
