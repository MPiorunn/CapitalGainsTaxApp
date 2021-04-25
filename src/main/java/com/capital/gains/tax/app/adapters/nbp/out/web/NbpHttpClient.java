package com.capital.gains.tax.app.adapters.nbp.out.web;

import com.capital.gains.tax.app.adapters.infrastructure.HttpClient;
import com.capital.gains.tax.app.adapters.infrastructure.HttpRequestExecutor;
import java.util.Collections;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.util.UriComponentsBuilder;

@Component
@RequiredArgsConstructor
public class NbpHttpClient implements HttpClient {

    private final HttpRequestExecutor httpRequestExecutor;

    public NbpCurrencyDto getCurrencyAtTradingDay(String tradingDay) {
        return sendRequest("/" + tradingDay, NbpCurrencyDto.class, new LinkedMultiValueMap<>());
    }

    public <T> T sendRequest(String requestUrl, Class<T> clazz, MultiValueMap<String, String> variables) {
        String uri = UriComponentsBuilder.fromHttpUrl(NbpConfig.BASE_URL + requestUrl)
            .buildAndExpand(variables).toUriString();
        return httpRequestExecutor.execute(uri, clazz);
    }
}
