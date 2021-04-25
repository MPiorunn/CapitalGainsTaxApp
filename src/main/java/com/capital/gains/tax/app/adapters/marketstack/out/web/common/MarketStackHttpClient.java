package com.capital.gains.tax.app.adapters.marketstack.out.web.common;

import com.capital.gains.tax.app.adapters.infrastructure.HttpClient;
import com.capital.gains.tax.app.adapters.infrastructure.HttpRequestExecutor;
import com.capital.gains.tax.app.adapters.marketstack.out.web.tickers.MarketStackTickersResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class MarketStackHttpClient implements HttpClient {

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
        return sendRequest(TICKERS_ENDPOINT, MarketStackTickersResponse.class, prepareRequestParams(search));
    }

    private MultiValueMap<String, String> prepareRequestParams(String search) {
        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
        parameters.add("search", search);
        parameters.add("exchange", "XNAS");
        return parameters;
    }

    public <T> T sendRequest(String requestUrl, Class<T> clazz, MultiValueMap<String, String> parameters) {
        parameters.add("access_key", ACCESS_KEY);
        String uri = UriComponentsBuilder.fromHttpUrl(MarketStockConfig.BASE_URL + requestUrl)
            .queryParams(parameters).toUriString();
        return httpRequestExecutor.execute(uri, clazz);
    }
}
