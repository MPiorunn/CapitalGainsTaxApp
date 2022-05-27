package app.core.infrastructure.config;

import app.core.domain.cache.CachedRequestFacade;
import app.core.domain.cgt.CapitalGainsTaxFacade;
import app.core.domain.currency.CurrencyDataProvider;
import app.core.domain.dividend.DividendDataProvider;
import app.core.infrastructure.adapters.outbound.aop.cache.CachedDataProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CapitalGainsTaxConfig {

    private final CurrencyDataProvider currencyDataProvider;
    private final DividendDataProvider dividendDataProvider;
    private final CachedDataProvider cachedDataProvider;

    @Bean
    public CapitalGainsTaxFacade capitalGainsTaxFacade() {
        return new CapitalGainsTaxFacade(dividendDataProvider, currencyDataProvider);
    }

    @Bean
    public CachedRequestFacade cachedRequestFacade() {
        return new CachedRequestFacade(cachedDataProvider);
    }
}
