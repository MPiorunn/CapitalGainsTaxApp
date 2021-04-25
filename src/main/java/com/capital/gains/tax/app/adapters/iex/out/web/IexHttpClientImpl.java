package com.capital.gains.tax.app.adapters.iex.out.web;

import com.capital.gains.tax.app.adapters.infrastructure.HttpRequestExecutor;
import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class IexHttpClientImpl implements IexHttpClient {

    @Value("${iex.token}")
    private String TOKEN;
    private final HttpRequestExecutor requestExecutor;
    private static final String DIVIDEND_URL = "/stock/{symbol}/dividends/1y";
    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public List<Dividend> getLastYearDividendsForStock(String symbol) {

        String requestUrl = IexConfig.BASE_URL + DIVIDEND_URL;
        URI uri = UriComponentsBuilder.fromHttpUrl(requestUrl)
            .queryParams(tokenParameter())
            .buildAndExpand(replaceStockSymbol(symbol))
            .toUri();
        Dividend[] dividends = restTemplate.getForObject(uri, Dividend[].class);
        if (dividends == null) {
            throw new IllegalArgumentException(
                String.format("No dividends found for company %s during last year", symbol));
        }
        return List.of(dividends);
    }

    private Map<String, String> replaceStockSymbol(String stock) {
        Map<String, String> parameters = new HashMap<>();
        parameters.put("symbol", stock);
        return parameters;
    }

    private MultiValueMap<String, String> tokenParameter() {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("token", TOKEN);
        return parameters;
    }
}
