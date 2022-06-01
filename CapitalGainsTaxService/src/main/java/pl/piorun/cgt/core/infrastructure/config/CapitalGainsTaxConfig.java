package pl.piorun.cgt.core.infrastructure.config;

import pl.piorun.cgt.core.domain.cache.CachedRequestFacade;
import pl.piorun.cgt.core.domain.cgt.CapitalGainsTaxFacade;
import pl.piorun.cgt.core.domain.currency.CurrencyDataProvider;
import pl.piorun.cgt.core.domain.dividend.DividendDataProvider;
import pl.piorun.cgt.core.infrastructure.adapters.outbound.aop.cache.CachedDataProvider;
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
