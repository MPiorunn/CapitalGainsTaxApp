package pl.piorun.cgt.core.domain.cgt;

import pl.piorun.cgt.commons.MathUtils;
import pl.piorun.cgt.core.domain.currency.CurrencyDataProvider;
import pl.piorun.cgt.core.domain.dividend.Dividend;
import pl.piorun.cgt.core.domain.dividend.DividendDataProvider;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class CapitalGainsTaxCalculator {

    private final DividendDataProvider dividendDataProvider;
    private final CurrencyDataProvider currencyDataProvider;

    public CapitalGainsTax calculateCapitalGainsTax(String stock, Double amount) {
        List<Dividend> dividends = dividendDataProvider.getLastYearDividends(stock);

        double sum = 0;
        for (Dividend dividend : dividends) {
            LocalDate paymentDate = dividend.getPaymentDate();
            Double dollarPrice = currencyDataProvider.getDollarPriceFromPreviousTradingDay(paymentDate);
            sum += MathUtils.roundToTwoDecPlaces(dividend.getAmount() * dollarPrice);
        }

        double withholdingTax = 0.15;
        double plWithholdingTax = 0.04;
        double totalWithholdingTax = MathUtils.roundToTwoDecPlaces(sum * withholdingTax);
        double totalPlWithholdingTax = MathUtils.roundToTwoDecPlaces(sum * plWithholdingTax);
        double income = MathUtils.roundToTwoDecPlaces(sum - totalWithholdingTax);
        double incomePl = MathUtils.roundToTwoDecPlaces(sum - totalPlWithholdingTax);
        return CapitalGainsTax.builder()
            .stock(stock)
            .stocksAmount(amount)
            .dividendAmount(dividends.size())
            .totalDividend(sum)
            .withholdingTax(totalWithholdingTax)
            .remainingFourPercent(totalPlWithholdingTax)
            .income(income)
            .incomeInPoland(incomePl)
            .amountOfDividends(dividends.size())
            .build();
    }
}
