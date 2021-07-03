package com.capital.gains.tax.app.core.infrastructure.adapters.outbound.http;

import com.capital.gains.tax.app.core.infrastructure.adapters.outbound.aop.CachedRequest;
import java.net.URI;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class HttpRequestExecutorImpl implements HttpRequestExecutor {

    private final RestTemplate restTemplate = new RestTemplate();

    @Override
    @CachedRequest
    public <T> T execute(URI uri, Class<T> clazz) {
        return restTemplate.getForObject(uri, clazz);
    }
}
