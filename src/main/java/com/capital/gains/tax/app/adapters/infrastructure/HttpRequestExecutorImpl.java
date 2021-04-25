package com.capital.gains.tax.app.adapters.infrastructure;

import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HttpRequestExecutorImpl implements HttpRequestExecutor {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public <T> T execute(String requestUrl, Class<T> clazz) {
        return restTemplate.getForObject(requestUrl, clazz);
    }

}
