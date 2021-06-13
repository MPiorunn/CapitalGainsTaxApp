package com.capital.gains.tax.app.external.http.iex;

import com.capital.gains.tax.app.external.http.HttpRequestExecutor;
import com.capital.gains.tax.app.external.http.RequestUriBuilder;
import java.net.URI;
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
    public DividendDto[] getLastYearDividendsForStock(String symbol) {

        String requestUrl = IexConfig.BASE_URL + DIVIDEND_URL;
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
