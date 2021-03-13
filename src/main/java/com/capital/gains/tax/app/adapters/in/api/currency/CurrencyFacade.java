package com.capital.gains.tax.app.adapters.in.api.currency;

import com.capital.gains.tax.app.adapters.out.api.currency.NbpClient;
import com.capital.gains.tax.app.commons.DateUtils;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
@RequiredArgsConstructor
@Log4j2
public class CurrencyFacade {

    private final NbpClient nbpClient;

    public CurrencyAtDateResponse getCurrencyAtDate(String date) {
        LocalDate localDate = LocalDate.parse(date, DateTimeFormatter.ISO_LOCAL_DATE);
        Double currencyFromDate;
        try {
            currencyFromDate = nbpClient.getCurrencyFromDate(localDate);
        } catch (HttpClientErrorException e) {
            LocalDate previousFriday = DateUtils.calculatePreviousFriday(localDate);
            log.info("{} is during weekend, returning price for {}", localDate, previousFriday);
            currencyFromDate = nbpClient.getCurrencyFromDate(previousFriday);
        }
        return new CurrencyAtDateResponse(currencyFromDate);
    }

}
