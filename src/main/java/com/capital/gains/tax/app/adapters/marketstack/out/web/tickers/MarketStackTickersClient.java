package com.capital.gains.tax.app.adapters.marketstack.out.web.tickers;

import java.util.List;

public interface MarketStackTickersClient {

    List<String> getCompanies(String search);

}
