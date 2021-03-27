package com.capital.gains.tax.app.adapters.marketstack.out.web.tickers;

import com.capital.gains.tax.app.adapters.marketstack.out.web.common.MarketStackClient;
import com.capital.gains.tax.app.adapters.marketstack.out.web.tickers.MarketStackTickersResponse.TickerData;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Component
@RequiredArgsConstructor
public class MarketStackTickersClientImpl implements MarketStackTickersClient {

    private final MarketStackClient marketStackClient;
    private static final String TICKERS_ENDPOINT = "/tickers";
    private static final String NEED_MORE_CHARACTERS = "'%s' is not enough. Please provide at least 3 characters for the search";

    @Override
    public List<String> getCompanies(String search) {
        if (search.length() < 3) {
            throw new IllegalArgumentException(String.format(NEED_MORE_CHARACTERS, search));
        }
        MultiValueMap<String, String> params = prepareRequestParams(search);
        MarketStackTickersResponse response = marketStackClient
            .sendRequest(TICKERS_ENDPOINT, MarketStackTickersResponse.class, params);
        return Arrays.stream(response.getData())
            .map(this::buildStockName)
            .collect(Collectors.toList());
    }

    private MultiValueMap<String, String> prepareRequestParams(String search) {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("search", search);
        parameters.add("exchange", "XNAS");
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
