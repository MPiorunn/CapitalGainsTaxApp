package com.capital.gains.tax.app.adapters.iex.out.web;

import com.capital.gains.tax.app.adapters.infrastructure.HttpRequestExecutor;
import com.capital.gains.tax.app.adapters.infrastructure.RequestUriBuilder;
import java.net.URI;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IexHttpClientImpl implements IexHttpClient {

    @Value("${iex.token}")
    private String TOKEN;
    private final HttpRequestExecutor requestExecutor;
    private static final String DIVIDEND_URL = "/stock/{symbol}/dividends/1y";

    @Override
    public List<Dividend> getLastYearDividendsForStock(String symbol) {

        String requestUrl = IexConfig.BASE_URL + DIVIDEND_URL;
        URI uri = RequestUriBuilder.builder()
            .fromUrl(requestUrl)
            .pathVariable("symbol", symbol)
            .queryParam("token", TOKEN)
            .build();
        Dividend[] dividends = requestExecutor.execute(uri, Dividend[].class);
        if (dividends == null) {
            throw new IllegalArgumentException(
                String.format("No dividends found for company %s during last year", symbol));
        }
        return List.of(dividends);
    }
}
