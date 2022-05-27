package app.core.domain.dividend;

import java.util.List;

public interface DividendDataProvider {

    List<Dividend> getLastYearDividends(String stockSymbol);
}
