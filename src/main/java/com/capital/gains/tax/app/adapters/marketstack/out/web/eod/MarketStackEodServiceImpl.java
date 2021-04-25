package com.capital.gains.tax.app.adapters.marketstack.out.web.eod;

import com.capital.gains.tax.app.adapters.marketstack.out.web.common.MarketStackHttpClient;
import java.time.LocalDateTime;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

@Component
@RequiredArgsConstructor
public class MarketStackEodServiceImpl implements MarketStackEodService {

//    private final MarketStackHttpClient marketStackHttpClient; TODO remove this class?
//    public final static String EOD_URL = "/eod";

    @Override
    public void getStockPriceOfDay(String stockSymbol, LocalDateTime tradingDay) {
//        MultiValueMap<String, String> params = prepareRequestParams(stockSymbol);

//        MarketStackResponse response = marketStackHttpClient.sendRequest(EOD_URL, MarketStackResponse.class, params);
        // mapowanie działa, teraz trzeba rozkminić jak trzymać w pamięci request z całego roku, to raczej frontend zrobi , ale powinno być git :)
//        List<LocalDateTime> dates = response.getData().stream()
//            .map(dayOfTrading -> LocalDateTime
//                .parse(dayOfTrading.getDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ssZ")))
//            .collect(Collectors.toList());
//        System.out.println(response);
    }
//
//    private MultiValueMap<String, String> prepareRequestParams(String stockSymbol) {
//        MultiValueMap<String, String> parameters = new LinkedMultiValueMap<>();
//        parameters.add("symbols", stockSymbol);
//        return parameters;
//    }
}
