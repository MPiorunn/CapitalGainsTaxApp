package com.capital.gains.tax.app.adapters.marketstack.out.web.tickers;

import com.capital.gains.tax.app.adapters.marketstack.out.web.common.MarketStackHttpClient;
import com.capital.gains.tax.app.adapters.marketstack.out.web.tickers.MarketStackTickersResponse.TickerData;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MarketStackTickersServiceImpl implements MarketStackTickersService {

    private final MarketStackHttpClient marketStackHttpClient;

    @Override
    public List<String> getCompanies(String search) {
        MarketStackTickersResponse response = marketStackHttpClient.getCompanies(search);
        return Arrays.stream(response.getData())
            .map(this::buildStockName)
            .collect(Collectors.toList());
    }

    private String buildStockName(TickerData tickerData) {
        return String.format("%s : %s", tickerData.getName(), tickerData.getSymbol());
    }
}
