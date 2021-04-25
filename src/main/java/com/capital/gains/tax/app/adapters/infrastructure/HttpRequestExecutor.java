package com.capital.gains.tax.app.adapters.infrastructure;

import java.net.URI;

public interface HttpRequestExecutor {

    <T> T execute(URI requestUrl, Class<T> clazz);
}
