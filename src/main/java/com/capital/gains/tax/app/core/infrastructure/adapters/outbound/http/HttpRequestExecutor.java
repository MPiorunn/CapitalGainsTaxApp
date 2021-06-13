package com.capital.gains.tax.app.core.infrastructure.adapters.outbound.http;

import java.net.URI;

public interface HttpRequestExecutor {

    <T> T execute(URI requestUrl, Class<T> clazz);
}
