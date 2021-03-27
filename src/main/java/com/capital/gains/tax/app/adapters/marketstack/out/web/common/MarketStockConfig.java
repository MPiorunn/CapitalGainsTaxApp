package com.capital.gains.tax.app.adapters.marketstack.out.web.common;

public class MarketStockConfig {

    public final static String BASE_URL = "http://api.marketstack.com/v1";
    public final static String EOD_URL = BASE_URL + "/eod";
    public final static String TICKERS_URL = BASE_URL + "/tickers?search={search}&access_key={access_key}&exchange=XNAS";

}
