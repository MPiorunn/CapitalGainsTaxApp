package com.capital.gains.tax.app.adapters.iex.out.web;

import java.util.List;

public interface IexService {

    List<Dividend> getDividendsFromLastYear(String symbol);
}
