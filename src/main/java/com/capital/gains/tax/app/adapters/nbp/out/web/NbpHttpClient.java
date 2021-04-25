package com.capital.gains.tax.app.adapters.nbp.out.web;

import com.capital.gains.tax.app.adapters.infrastructure.HttpRequestExecutor;
import com.capital.gains.tax.app.adapters.infrastructure.RequestUriBuilder;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NbpHttpClient {

    private final HttpRequestExecutor httpRequestExecutor;
    private final String TRADING_DAY_URL = "/{tradingDay}";

    public NbpCurrencyDto getCurrencyAtTradingDay(String tradingDay) {

        URI uri = RequestUriBuilder.builder()
            .fromUrl(NbpConfig.BASE_URL + TRADING_DAY_URL)
            .pathVariable("tradingDay", tradingDay)
            .build();
        return httpRequestExecutor.execute(uri, NbpCurrencyDto.class);
    }
}
