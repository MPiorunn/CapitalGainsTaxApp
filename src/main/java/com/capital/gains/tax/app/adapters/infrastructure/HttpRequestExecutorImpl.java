package com.capital.gains.tax.app.adapters.infrastructure;

import org.springframework.stereotype.Component;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class HttpRequestExecutorImpl implements HttpRequestExecutor {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    public <T> T execute(String requestUrl, Class<T> clazz, MultiValueMap<String, String> variables) {
        String uri = UriComponentsBuilder.fromHttpUrl(requestUrl).queryParams(variables).toUriString();
        return restTemplate.getForObject(uri, clazz);
    }

}
