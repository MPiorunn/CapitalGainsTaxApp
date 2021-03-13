package com.capital.gains.tax.app.adapters.out.api.currency;

import java.time.LocalDate;
import org.springframework.web.client.HttpClientErrorException;

public interface NbpClient {

    Double getCurrencyFromDate(LocalDate localDate) throws HttpClientErrorException;
}
