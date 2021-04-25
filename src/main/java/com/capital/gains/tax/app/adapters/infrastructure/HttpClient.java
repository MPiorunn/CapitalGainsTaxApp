package com.capital.gains.tax.app.adapters.infrastructure;

import org.springframework.util.MultiValueMap;

public interface HttpClient {

    <T> T sendRequest(String requestUrl, Class<T> clazz, MultiValueMap<String, String> variables);
}
