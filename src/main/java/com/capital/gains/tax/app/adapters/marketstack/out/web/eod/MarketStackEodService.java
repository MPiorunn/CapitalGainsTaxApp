package com.capital.gains.tax.app.adapters.marketstack.out.web.eod;

import java.time.LocalDateTime;

public interface MarketStackEodService {

    void getStockPriceOfDay(String stockSymbol, LocalDateTime tradingDay);
}
