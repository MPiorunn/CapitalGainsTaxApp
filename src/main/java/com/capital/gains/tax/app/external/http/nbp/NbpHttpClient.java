package com.capital.gains.tax.app.external.http.nbp;

import com.capital.gains.tax.app.external.http.HttpRequestExecutor;
import com.capital.gains.tax.app.external.http.RequestUriBuilder;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NbpHttpClient {

    private final HttpRequestExecutor httpRequestExecutor;

    public NbpCurrencyDto getCurrencyAtTradingDay(String tradingDay) {

        String tradingDayUrl = "/{tradingDay}";
        URI uri = RequestUriBuilder.builder()
            .fromUrl(NbpConfig.BASE_URL + tradingDayUrl)
            .pathVariable("tradingDay", tradingDay)
            .build();
        return httpRequestExecutor.execute(uri, NbpCurrencyDto.class);
    }
}

