package pl.piorun.cgt.core.domain.cgt;

import pl.piorun.cgt.core.domain.currency.CurrencyDataProvider;
import pl.piorun.cgt.core.domain.dividend.DividendDataProvider;

public class CapitalGainsTaxFacade {

    private final CapitalGainsTaxCalculator capitalGainsTaxCalculator;

    public CapitalGainsTaxFacade(DividendDataProvider dividendDataProvider, CurrencyDataProvider currencyDataProvider) {
        this.capitalGainsTaxCalculator = new CapitalGainsTaxCalculator(dividendDataProvider, currencyDataProvider);
    }

    public CapitalGainsTax calculateCapitalGainsTax(String stock, Double amount) {
        return capitalGainsTaxCalculator.calculateCapitalGainsTax(stock, amount);
    }
}
