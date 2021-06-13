package com.capital.gains.tax.app.core.domain.dividend;

import java.util.List;

public interface DividendDataProvider {

    List<Dividend> getLastYearDividends(String stockSymbol);
}
