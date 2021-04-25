package com.capital.gains.tax.app.adapters.stock.in.web;

import com.capital.gains.tax.app.adapters.marketstack.out.web.tickers.MarketStackTickersResponse;
import com.capital.gains.tax.app.adapters.marketstack.out.web.tickers.MarketStackTickersResponse.TickerData;
import com.capital.gains.tax.app.adapters.marketstack.out.web.tickers.MarketStackTickersService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StocksFacade {

    private final MarketStackTickersService marketStackTickersService;

    public StocksListResponse getStocksBySearch(String search) {
        MarketStackTickersResponse tickersResponse = marketStackTickersService.getCompanies(search);
        List<String> companies = mapTickersToCompanyNames(tickersResponse.getData());
        return new StocksListResponse(companies, tickersResponse.getPagination());
    }

    private List<String> mapTickersToCompanyNames(List<TickerData> tickerData) {
        return tickerData
            .stream()
            .map(this::buildStockName)
            .collect(Collectors.toList());
    }

    private String buildStockName(TickerData tickerData) {
        return String.format("%s : %s", tickerData.getName(), tickerData.getSymbol());
    }
}
