package pl.piorun.cgt.core.domain.cgt;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Setter(value = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class CapitalGainsTax {

    private final String stock;
    private final double stocksAmount;
    private final int dividendAmount;
    private final double totalDividend;
    private final double withholdingTax;
    private final double remainingFourPercent;
    private final double income;
    private final double incomeInPoland;
    private final int amountOfDividends;
}
