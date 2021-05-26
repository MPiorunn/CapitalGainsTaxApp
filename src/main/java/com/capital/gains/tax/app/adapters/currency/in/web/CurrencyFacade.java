package com.capital.gains.tax.app.adapters.currency.in.web;

import com.capital.gains.tax.app.adapters.nbp.out.web.NbpCurrencyService;
import com.capital.gains.tax.app.commons.DateUtils;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class CurrencyFacade {

    private final NbpCurrencyService nbpCurrencyService;

    public CurrencyAtDateResponse getPreviousTradingDayPrice(LocalDate tradeDay) {
        LocalDate previousTradingDay = DateUtils.getPreviousTradingDay(tradeDay);
        Double priceAtTradingDay = nbpCurrencyService.getCurrencyAtTradingDay(previousTradingDay);
        return new CurrencyAtDateResponse(priceAtTradingDay, tradeDay, previousTradingDay);
    }

    public CurrencyAtDateResponse getPreviousTradingDayPrice(String date) {
        LocalDate tradeDay = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        return getPreviousTradingDayPrice(tradeDay);
    }

}
