package com.capital.gains.tax.app.adapters.infrastructure;

import java.util.Map;

public interface HttpRequestExecutor {

    <T> T execute(String requestUrl, Class<T> clazz, Map<String, String> variables);
}
