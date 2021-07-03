package com.capital.gains.tax.app.core.infrastructure.adapters.outbound.http.dividend;

import com.capital.gains.tax.app.commons.RequestUriBuilder;
import com.capital.gains.tax.app.core.infrastructure.adapters.outbound.http.HttpRequestExecutor;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IexHttpClient {

    private static final String DIVIDEND_URL = "/stock/{symbol}/dividends/1y";
    private static final String BASE_URL = "https://cloud.iexapis.com/v1";
    private final HttpRequestExecutor requestExecutor;
    @Value("${iex.token}")
    private String TOKEN;

    public DividendDto[] getLastYearDividendsForStock(String symbol) {

        String requestUrl = BASE_URL + DIVIDEND_URL;
        URI uri = RequestUriBuilder.builder()
            .fromUrl(requestUrl)
            .pathVariable("symbol", symbol)
            .queryParam("token", TOKEN)
            .build();
        DividendDto[] dividendDtos = requestExecutor.execute(uri, DividendDto[].class);
        if (dividendDtos == null || dividendDtos.length == 0) {
            throw new IllegalArgumentException(
                String.format("No dividends found for company %s during last year", symbol));
        }
        return dividendDtos;
    }
}
