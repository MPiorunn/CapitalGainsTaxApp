package com.capital.gains.tax.app.core.infrastructure.boundary.outbound.http.currency;

import com.capital.gains.tax.app.external.http.nbp.NbpCurrencyDto;
import com.capital.gains.tax.app.external.http.nbp.NbpHttpClient;
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
        NbpCurrencyDto responseEntity = nbpHttpClient.getCurrencyAtTradingDay(date.toString());
        if (responseEntity == null || responseEntity.getRates() == null) {
            throw new IllegalArgumentException(String.format("Could not get dollar price from day %s", date));
        }
        return responseEntity.getRates().get(0).getAsk();
    }
}
