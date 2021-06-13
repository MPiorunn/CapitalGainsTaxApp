package com.capital.gains.tax.app.core.infrastructure.config;

import com.capital.gains.tax.app.core.domain.cgt.CapitalGainsTaxFacade;
import com.capital.gains.tax.app.core.domain.currency.CurrencyDataProvider;
import com.capital.gains.tax.app.core.domain.dividend.DividendDataProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CapitalGainsTaxConfig {

    private final CurrencyDataProvider currencyDataProvider;
    private final DividendDataProvider dividendDataProvider;

    @Bean
    public CapitalGainsTaxFacade capitalGainsTaxFacade() {
        return new CapitalGainsTaxFacade(dividendDataProvider, currencyDataProvider);
    }
}
