package app.core.infrastructure.adapters.outbound.http.dividend;

import app.commons.RequestUriBuilder;
import java.net.URI;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
@RequiredArgsConstructor
public class IexHttpClient {

    private static final String BASE_URL = "https://cloud.iexapis.com/v1";
    private static final String DIVIDEND_URL = "/stock/{symbol}/dividends/1y";
    private final RestTemplate restTemplate = new RestTemplate();

    @Value("${iex.token}")
    private String TOKEN;

    public DividendDto[] getLastYearDividendsForStock(String symbol) {

        URI uri = buildURI(symbol);
        DividendDto[] dividendDtos = queryIEXApiForDividends(uri);
        if (dividendDtos == null || dividendDtos.length == 0) {
            throw new IllegalArgumentException(
                String.format("No dividends found for company %s during last year", symbol));
        }
        return dividendDtos;
    }

    private DividendDto[] queryIEXApiForDividends(URI uri) {
        return restTemplate.getForObject(uri, DividendDto[].class);
    }

    private URI buildURI(String symbol) {
        String requestUrl = BASE_URL + DIVIDEND_URL;
        return RequestUriBuilder.builder()
            .fromUrl(requestUrl)
            .pathVariable("symbol", symbol)
            .queryParam("token", TOKEN)
            .build();
    }

}
