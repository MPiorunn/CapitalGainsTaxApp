package com.capital.gains.tax.app.adapters.iex.out.web;

import static com.capital.gains.tax.app.commons.MathUtils.roundToTwoDecPlaces;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IexServiceImpl implements IexService {

    private final IexHttpClient httpClient;
    @Value("${tax.capital.gains.us}")
    private Double WITHHOLDING_TAX;
    @Value("${tax.capital.gains.pl}")
    private Double PL_WITHHOLDING_TAX;

    @Override
    public DividendTaxResponse getTaxForDividendsFromLastYear(String stock, double stocksAmount) {
        Dividend[] dividends = httpClient.getLastYearDividendsForStock(stock);
        double sum = 0;
        for (Dividend dividend : dividends) {
            sum += dividend.getAmount();
        }

        double withholdingTax = roundToTwoDecPlaces(sum * WITHHOLDING_TAX);
        double plWithholdingTax = roundToTwoDecPlaces(sum * PL_WITHHOLDING_TAX);

        return DividendTaxResponse.builder()
            .stock(stock)
            .stocksAmount(stocksAmount)
            .totalDividend(sum)
            .withholdingTax(withholdingTax)
            .income(sum - withholdingTax)
            .remainingFourPercent(plWithholdingTax)
            .amountOfDividends(dividends.length)
            .dividends(dividends)
            .build();
    }
}
