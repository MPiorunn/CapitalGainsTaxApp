package app.core.domain.cgt;

import app.core.domain.currency.CurrencyDataProvider;
import app.core.domain.dividend.DividendDataProvider;

public class CapitalGainsTaxFacade {

    private final CapitalGainsTaxCalculator capitalGainsTaxCalculator;

    public CapitalGainsTaxFacade(DividendDataProvider dividendDataProvider, CurrencyDataProvider currencyDataProvider) {
        this.capitalGainsTaxCalculator = new CapitalGainsTaxCalculator(dividendDataProvider, currencyDataProvider);
    }

    public CapitalGainsTax calculateCapitalGainsTax(String stock, Double amount) {
        return capitalGainsTaxCalculator.calculateCapitalGainsTax(stock, amount);
    }
}
