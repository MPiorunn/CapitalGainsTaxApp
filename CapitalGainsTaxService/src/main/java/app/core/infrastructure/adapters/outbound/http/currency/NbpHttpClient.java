package app.core.infrastructure.adapters.outbound.http.currency;

import app.commons.RequestUriBuilder;
import java.net.URI;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class NbpHttpClient {

    private final static String BASE_URL = "http://api.nbp.pl/api/exchangerates/rates/c/usd/";
    private final RestTemplate restTemplate = new RestTemplate();

    public CurrencyDto getCurrencyAtTradingDay(LocalDate tradingDay) {
        String tradingDayUrl = "/{tradingDay}";
        URI uri = buildURI(tradingDay, tradingDayUrl);
        return queryNbpForDollarPrice(uri);
    }

    private CurrencyDto queryNbpForDollarPrice(URI uri) {
        return restTemplate.getForObject(uri, CurrencyDto.class);
    }

    private URI buildURI(LocalDate tradingDay, String tradingDayUrl) {
        return RequestUriBuilder.builder()
            .fromUrl(BASE_URL + tradingDayUrl)
            .pathVariable("tradingDay", tradingDay.toString())
            .build();
    }
}

