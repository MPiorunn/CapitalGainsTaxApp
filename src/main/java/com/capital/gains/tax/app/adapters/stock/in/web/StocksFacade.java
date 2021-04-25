package com.capital.gains.tax.app.adapters.stock.in.web;

import com.capital.gains.tax.app.adapters.marketstack.out.web.tickers.MarketStackTickersService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StocksFacade {

    private final MarketStackTickersService marketStackTickersService;

    public StocksListResponse getStocksBySearch(String search) {
        List<String> companies = marketStackTickersService.getCompanies(search);
        return new StocksListResponse(companies, companies.size());
    }
}
