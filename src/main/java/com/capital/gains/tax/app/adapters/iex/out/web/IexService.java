package com.capital.gains.tax.app.adapters.iex.out.web;

public interface IexService {



    DividendTaxResponse getTaxForDividendsFromLastYear(String stock, double stocksAmount);
}
