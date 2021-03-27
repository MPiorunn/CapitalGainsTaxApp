package com.capital.gains.tax.app.adapters.marketstack.out.web.tickers;

import com.capital.gains.tax.app.adapters.marketstack.out.web.common.MarketStackRequestExecutor;
import com.capital.gains.tax.app.adapters.marketstack.out.web.common.MarketStockConfig;
import com.capital.gains.tax.app.adapters.marketstack.out.web.tickers.MarketStackTickersResponse.TickerData;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MarketStackTickersClientImpl implements MarketStackTickersClient {

    private final MarketStackRequestExecutor marketStackRequestExecutor;

    @Override
    public List<String> getCompanies(String search) {
        if (search.length() < 2) {
            throw new IllegalArgumentException("Provide at least 2 characters for the search");
        }
        Map<String, String> params = prepareRequestParams(search);
        String requestUrl = MarketStockConfig.TICKERS_URL;
        MarketStackTickersResponse response = marketStackRequestExecutor
            .execute(requestUrl, MarketStackTickersResponse.class, params);
        return Arrays.stream(response.getData())
            .map(this::buildStockName)
            .collect(Collectors.toList());
    }

    private Map<String, String> prepareRequestParams(String search) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("search", search);
        return parameters;
    }

    private String buildStockName(TickerData tickerData) {
        StringBuilder builder = new StringBuilder();
        builder.append(tickerData.getName());
        builder.append(" : ");
        builder.append(tickerData.getSymbol());
        return builder.toString();
    }
}
