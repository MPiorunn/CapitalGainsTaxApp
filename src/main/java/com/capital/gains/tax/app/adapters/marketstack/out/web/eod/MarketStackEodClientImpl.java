package com.capital.gains.tax.app.adapters.marketstack.out.web.eod;

import com.capital.gains.tax.app.adapters.marketstack.out.web.common.MarketStockConfig;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class MarketStackEodClientImpl implements MarketStackEodClient {

    private final RestTemplate restTemplate = new RestTemplate();
    private String s = "Stara arka hehe";

    @Override
    @Cacheable("connectToClient")
    public void getStockPriceOnDay(String stockSymbol) {
        UriComponentsBuilder requestUrl = UriComponentsBuilder
            .fromHttpUrl(MarketStockConfig.BASE_URL)
            // TODO TO do konfigów albo Vault oganrij w koncu
            .queryParam("access_key", "f9aa1b86bd497bc47e1161fe9c404015")
            .queryParam("symbols", stockSymbol);
        MarketStackResponse response = restTemplate.getForObject(requestUrl.toUriString(), MarketStackResponse.class);
        // mapowanie działa, teraz trzeba rozkminić jak trzymać w pamięci request z całego roku, to raczej frontend zrobi , ale powinno być git :)
//        List<LocalDateTime> dates = response.getData().stream()
//            .map(dayOfTrading -> LocalDateTime
//                .parse(dayOfTrading.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ")))
//            .collect(Collectors.toList());
        System.out.println(response);
    }
}
