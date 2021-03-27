package com.capital.gains.tax.app.adapters.stock.in.web;

import com.capital.gains.tax.app.adapters.marketstack.out.web.eod.MarketStackEodClient;
import com.capital.gains.tax.app.adapters.marketstack.out.web.tickers.MarketStackTickersClient;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StockFacade {

    private final MarketStackTickersClient marketStackTickersClient;

    public StocksListResponse getStocksBySearch(String search) {
        List<String> companies = marketStackTickersClient.getCompanies(search);
        return new StocksListResponse(companies, companies.size());
    }
}
