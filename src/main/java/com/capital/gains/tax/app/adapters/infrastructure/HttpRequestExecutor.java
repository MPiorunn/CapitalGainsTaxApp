package com.capital.gains.tax.app.adapters.infrastructure;

public interface HttpRequestExecutor {

    <T> T execute(String requestUrl, Class<T> clazz);
}
