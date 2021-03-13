package com.capital.gains.tax.app.adapters.out.api.currency;

import java.time.LocalDate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

@Component
class NbpClientImpl implements NbpClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public Double getCurrencyFromDate(LocalDate localDate) throws HttpClientErrorException {
        String requestUrl = NbpUrl.NBP_URL + "{date}" + NbpUrl.NBP_JSON_FORMAT_PARAM;
        NbpCurrencyDto responseEntity = restTemplate.getForObject(requestUrl, NbpCurrencyDto.class,
            localDate.toString());
        if (responseEntity == null || responseEntity.getRates() == null) {
            throw new IllegalArgumentException("Could not get response from NBP for url " + requestUrl);
        }
        return responseEntity.getRates().get(0).getAsk();
    }
}
