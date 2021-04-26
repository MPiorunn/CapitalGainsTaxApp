package com.capital.gains.tax.app.adapters.iex.out.web;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class IexServiceImpl implements IexService {

    private final IexHttpClient httpClient;
    private final Double WITHHOLDING_TAX = 0.15;
    private final Double PL_WITHHOLDING_TAX = 0.04;

    @Override
    public DividendTaxResponse getTaxForDividendsFromLastYear(String stock, double stocksAmount) {
        Dividend[] dividends = httpClient.getLastYearDividendsForStock(stock);
        double sum = 0;
        for (Dividend dividend : dividends) {
            sum += dividend.getAmount();
        }

        double withholdingTax = roundToTwoDecPlaces(sum * WITHHOLDING_TAX);
        double plWithholdingTax = roundToTwoDecPlaces(sum * PL_WITHHOLDING_TAX);
        return new DividendTaxResponse(
            stock,
            stocksAmount,
            sum,
            withholdingTax,
            sum - withholdingTax,
            plWithholdingTax,
            dividends.length
        );
    }


    private double roundToTwoDecPlaces(double number) {
        return Math.round(100 * number) / 100.00;
    }
}
