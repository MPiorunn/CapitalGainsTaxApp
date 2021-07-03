package com.capital.gains.tax.app.core.infrastructure.adapters.outbound.http.currency;

import com.capital.gains.tax.app.commons.RequestUriBuilder;
import com.capital.gains.tax.app.core.infrastructure.adapters.outbound.http.HttpRequestExecutor;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NbpHttpClient {

    private final static String BASE_URL = "http://api.nbp.pl/api/exchangerates/rates/c/usd/";
    private final HttpRequestExecutor httpRequestExecutor;

    public CurrencyDto getCurrencyAtTradingDay(String tradingDay) {

        String tradingDayUrl = "/{tradingDay}";
        URI uri = RequestUriBuilder.builder()
            .fromUrl(BASE_URL + tradingDayUrl)
            .pathVariable("tradingDay", tradingDay)
            .build();
        return httpRequestExecutor.execute(uri, CurrencyDto.class);
    }
}

