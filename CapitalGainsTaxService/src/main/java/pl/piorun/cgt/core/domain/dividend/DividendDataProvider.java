package pl.piorun.cgt.core.domain.dividend;

import java.util.List;

public interface DividendDataProvider {

    List<Dividend> getLastYearDividends(String stockSymbol);
}
