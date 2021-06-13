package com.capital.gains.tax.app.external.http;

import java.net.URI;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HttpRequestExecutorImpl implements HttpRequestExecutor {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public <T> T execute(URI uri, Class<T> clazz) {
        return restTemplate.getForObject(uri, clazz);
    }

}
