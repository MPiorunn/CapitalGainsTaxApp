package com.capital.gains.tax.app.adapters.infrastructure;

import org.springframework.util.MultiValueMap;

public interface HttpRequestExecutor {

    <T> T execute(String requestUrl, Class<T> clazz, MultiValueMap<String, String> variables);
}
