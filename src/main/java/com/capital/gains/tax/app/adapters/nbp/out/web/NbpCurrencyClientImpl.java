package com.capital.gains.tax.app.adapters.nbp.out.web;

import java.time.LocalDate;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
class NbpCurrencyClientImpl implements NbpCurrencyClient {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public Double getCurrencyFromDate(LocalDate localDate) {
        String requestUrl = NbpConfig.URL + "{date}" + NbpConfig.NBP_JSON_FORMAT_PARAM;
        NbpCurrencyDto responseEntity = restTemplate.getForObject(requestUrl, NbpCurrencyDto.class,
            localDate.toString());
        if (responseEntity == null || responseEntity.getRates() == null) {
            throw new IllegalArgumentException("Could not get response from NBP for url " + requestUrl);
        }
        return responseEntity.getRates().get(0).getAsk();
    }
}
