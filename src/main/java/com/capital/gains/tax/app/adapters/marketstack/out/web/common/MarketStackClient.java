package com.capital.gains.tax.app.adapters.marketstack.out.web.common;

import com.capital.gains.tax.app.adapters.infrastructure.HttpRequestExecutor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;

@Component
@RequiredArgsConstructor
public class MarketStackClient {

    @Value("${marketstack.access.key}")
    private String ACCESS_KEY;
    private final HttpRequestExecutor httpRequestExecutor;
    private final static String MARKET_STACK_URL = "http://api.marketstack.com/v1";


    public <T> T sendRequest(String requestUrl, Class<T> clazz, MultiValueMap<String, String> variables) {
        variables.add("access_key", ACCESS_KEY);
        return httpRequestExecutor.execute(MARKET_STACK_URL + requestUrl, clazz, variables);
    }
}
