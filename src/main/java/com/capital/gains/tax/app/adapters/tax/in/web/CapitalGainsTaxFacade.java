package com.capital.gains.tax.app.adapters.tax.in.web;

import static com.capital.gains.tax.app.commons.MathUtils.roundToTwoDecPlaces;

import com.capital.gains.tax.app.adapters.currency.in.web.CurrencyAtDateResponse;
import com.capital.gains.tax.app.adapters.currency.in.web.CurrencyFacade;
import com.capital.gains.tax.app.adapters.iex.out.web.Dividend;
import com.capital.gains.tax.app.adapters.iex.out.web.DividendTaxResponse;
import com.capital.gains.tax.app.adapters.iex.out.web.IexService;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CapitalGainsTaxFacade {

    private final IexService iexService;
    private final CurrencyFacade currencyFacade;
    @Value("${tax.capital.gains.us}")
    private Double WITHHOLDING_TAX;
    @Value("${tax.capital.gains.pl}")
    private Double PL_WITHHOLDING_TAX;

    public DividendTaxResponse calculateCapitalGainsTax(String stock, Double amount) {
        DividendTaxResponse taxResponse = iexService.getTaxForDividendsFromLastYear(stock, amount);

        double sum = 0;
        for (Dividend dividend : taxResponse.getDividends()) {
            LocalDate paymentDate = dividend.getPaymentDate();
            CurrencyAtDateResponse previousDay = currencyFacade.getPreviousTradingDayPrice(paymentDate);
            dividend.setAmount(roundToTwoDecPlaces(dividend.getAmount() * previousDay.getPrice()));
            dividend.setCurrency("PLN");
            sum += dividend.getAmount();

        }

        double withholdingTax = roundToTwoDecPlaces(sum * WITHHOLDING_TAX);
        double plWithholdingTax = roundToTwoDecPlaces(sum * PL_WITHHOLDING_TAX);

        return DividendTaxResponse.builder()
            .stock(stock)
            .stocksAmount(amount)
            .totalDividend(sum)
            .withholdingTax(withholdingTax)
            .income(sum - withholdingTax)
            .remainingFourPercent(plWithholdingTax)
            .amountOfDividends(taxResponse.getDividends().length)
            .dividends(taxResponse.getDividends())
            .build();
    }
}
