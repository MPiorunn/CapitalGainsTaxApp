package com.capital.gains.tax.app.adapters.nbp.out.web;

import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
class NbpCurrencyServiceImpl implements NbpCurrencyService {

    private final NbpHttpClient httpClient;

    @Override
    public Double getCurrencyAtTradingDay(LocalDate tradingDate) {
        NbpCurrencyDto responseEntity = httpClient.getCurrencyAtTradingDay(tradingDate.toString());
        if (responseEntity == null || responseEntity.getRates() == null) {
            throw new IllegalArgumentException("Could not get response from NBP for trading day " + tradingDate);
        }
        return responseEntity.getRates().get(0).getAsk();
    }
}
