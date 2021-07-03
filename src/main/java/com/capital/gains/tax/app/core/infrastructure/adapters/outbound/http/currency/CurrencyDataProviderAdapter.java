package com.capital.gains.tax.app.core.infrastructure.adapters.outbound.http.currency;

import com.capital.gains.tax.app.commons.DateUtils;
import com.capital.gains.tax.app.core.domain.currency.CurrencyDataProvider;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Slf4j
@Service
@RequiredArgsConstructor
public class CurrencyDataProviderAdapter implements CurrencyDataProvider {

    private final NbpHttpClient nbpHttpClient;

    @Override
    public Double getDollarPriceFromPreviousTradingDay(LocalDate date) {
        LocalDate previousTradingDay = DateUtils.getPreviousTradingDay(date);
        CurrencyDto currencyDto = getPriceFromPreviousTradingDay(previousTradingDay);
        if (currencyDto == null || currencyDto.getRates() == null) {
            throw new IllegalArgumentException(String.format("Could not get dollar price from day %s", date));
        }
        return currencyDto.getRates().get(0).getAsk();
    }

    private CurrencyDto getPriceFromPreviousTradingDay(LocalDate previousTradingDay) {
        CurrencyDto currencyDto;
        try {
            currencyDto = nbpHttpClient.getCurrencyAtTradingDay(previousTradingDay.toString());
        } catch (HttpClientErrorException e) {
            LocalDate earlierDay = DateUtils.getPreviousTradingDay(previousTradingDay);
            log.error("Got error : {} for date : {} , trying previous trading day : {}", e.getLocalizedMessage(),
                previousTradingDay, earlierDay);
            currencyDto = nbpHttpClient.getCurrencyAtTradingDay(earlierDay.toString());
        }
        return currencyDto;
    }
}
