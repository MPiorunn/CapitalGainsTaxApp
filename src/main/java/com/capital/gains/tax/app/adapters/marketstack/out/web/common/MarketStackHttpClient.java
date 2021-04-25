package com.capital.gains.tax.app.adapters.marketstack.out.web.common;

import com.capital.gains.tax.app.adapters.infrastructure.HttpRequestExecutor;
import com.capital.gains.tax.app.adapters.infrastructure.RequestUriBuilder;
import com.capital.gains.tax.app.adapters.marketstack.out.web.tickers.MarketStackTickersResponse;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MarketStackHttpClient {

    @Value("${marketstack.key}")
    private String ACCESS_KEY;
    private final HttpRequestExecutor httpRequestExecutor;
    private static final String TICKERS_ENDPOINT = "/tickers";
    private static final String NEED_MORE_CHARACTERS = "'%s' is not enough. Please provide at least 3 characters for the search";
    private static final int MINIMUM_CHARACTERS_FOR_SEARCHING = 3;

    public MarketStackTickersResponse getCompanies(String search) {
        if (search.length() < MINIMUM_CHARACTERS_FOR_SEARCHING) {
            throw new IllegalArgumentException(String.format(NEED_MORE_CHARACTERS, search));
        }
        URI uri = RequestUriBuilder.builder()
            .fromUrl(MarketStockConfig.BASE_URL + TICKERS_ENDPOINT)
            .queryParam("search", search)
            .queryParam("exchange", "XNAS")
            .queryParam("access_key", ACCESS_KEY)
            .build();
        return httpRequestExecutor.execute(uri, MarketStackTickersResponse.class);
    }
}
