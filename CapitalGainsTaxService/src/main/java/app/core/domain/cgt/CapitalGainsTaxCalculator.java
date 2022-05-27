package app.core.domain.cgt;

import app.commons.MathUtils;
import app.core.domain.currency.CurrencyDataProvider;
import app.core.domain.dividend.Dividend;
import app.core.domain.dividend.DividendDataProvider;
import java.time.LocalDate;
import java.util.List;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
class CapitalGainsTaxCalculator {

    private final DividendDataProvider dividendDataProvider;
    private final CurrencyDataProvider currencyDataProvider;

    public CapitalGainsTax calculateCapitalGainsTax(String stock, Double amount) {
        /*
            private final Double amount;
    private final LocalDate declaredDate;
    private final LocalDate paymentDate;
    private final LocalDate recordDate;
//         */
//        List<Dividend> dividends = List.of(Dividend.builder()
//            .amount(123.1)
//            .declaredDate(LocalDate.now())
//            .paymentDate(LocalDate.now())
//            .paymentDate(LocalDate.now())
//            .build());
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
