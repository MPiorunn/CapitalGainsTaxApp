package com.capital.gains.tax.app.core.infrastructure.adapters.outbound.http.currency;

import com.capital.gains.tax.app.commons.DateUtils;
import com.capital.gains.tax.app.core.domain.currency.CurrencyDataProvider;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CurrencyDataProviderAdapter implements CurrencyDataProvider {

    private final NbpHttpClient nbpHttpClient;

    @Override
    public Double getDollarPriceFromPreviousTradingDay(LocalDate date) {
        LocalDate previousTradingDay = DateUtils.getPreviousTradingDay(date);
        CurrencyDto currencyDto = nbpHttpClient.getCurrencyAtTradingDay(previousTradingDay.toString());
        if (currencyDto == null || currencyDto.getRates() == null) {
            throw new IllegalArgumentException(String.format("Could not get dollar price from day %s", date));
        }
        return currencyDto.getRates().get(0).getAsk();
    }
}
