package com.capital.gains.tax.app.adapters.marketstack.out.web.eod;

public interface MarketStackEodClient {

    void getStockPriceOnDay(String stockSymbol);
}
