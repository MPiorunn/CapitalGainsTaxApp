package app.core.domain.cgt;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import app.core.domain.currency.CurrencyDataProvider;
import app.core.domain.dividend.DividendDataProvider;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.Mock;
import org.mockito.Mockito;

public class CapitalGainsTaxCalculatorTest {

    @Mock
    private DividendDataProvider dividendDataProvider;

    @Mock
    private CurrencyDataProvider currencyDataProvider;

    private CapitalGainsTaxCalculator calculator;

    @BeforeEach
    public void setup() {
        dividendDataProvider = Mockito.mock(DividendDataProvider.class);
        currencyDataProvider = Mockito.mock(CurrencyDataProvider.class);
        calculator = new CapitalGainsTaxCalculator(dividendDataProvider, currencyDataProvider);
    }

}
